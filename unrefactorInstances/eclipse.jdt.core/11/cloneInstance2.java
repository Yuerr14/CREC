	private TypeBinding internalResolveType(Scope scope) {

		this.constant = NotAConstant;
		if (this.receiver == null) {
			this.receiverType = scope.enclosingSourceType();
		} else if (scope.kind == Scope.CLASS_SCOPE) {
			this.receiverType = this.receiver.resolveType((ClassScope) scope);
		} else {
			this.receiverType = this.receiver.resolveType((BlockScope)scope);
		}
		if (this.receiverType == null) {
			return null;
		}

		Binding fieldBinding = (this.receiver != null && this.receiver.isThis())
			? scope.classScope().getBinding(this.token, this.bits & RestrictiveFlagMASK, this, true /*resolve*/)
			: scope.getField(this.receiverType, this.token, this);
		if (!fieldBinding.isValidBinding()) {
			// implicit lookup may discover issues due to static/constructor contexts. javadoc must be resilient
			switch (fieldBinding.problemId()) {
				case ProblemReasons.NonStaticReferenceInConstructorInvocation:
				case ProblemReasons.NonStaticReferenceInStaticContext:
				case ProblemReasons.InheritedNameHidesEnclosingName : 
					FieldBinding closestMatch = ((ProblemFieldBinding)fieldBinding).closestMatch;
					if (closestMatch != null) {
						fieldBinding = closestMatch; // ignore problem if can reach target field through it
					}
			}
		}			
		if (!fieldBinding.isValidBinding() || !(fieldBinding instanceof FieldBinding)) {
			if (this.receiverType instanceof ReferenceBinding) {
				ReferenceBinding refBinding = (ReferenceBinding) this.receiverType;
				MethodBinding[] methodBindings = refBinding.getMethods(this.token);
				if (methodBindings == null) {
					scope.problemReporter().javadocInvalidField(this.sourceStart, this.sourceEnd, fieldBinding, this.receiverType, scope.getDeclarationModifiers());
				} else {
					switch (methodBindings.length) {
						case 0:
							scope.problemReporter().javadocInvalidField(this.sourceStart, this.sourceEnd, fieldBinding, this.receiverType, scope.getDeclarationModifiers());
							break;
						case 1:
							this.methodBinding = methodBindings[0];
							break;
						default:
							this.methodBinding = methodBindings[0];
							scope.problemReporter().javadocAmbiguousMethodReference(this.sourceStart, this.sourceEnd, fieldBinding, scope.getDeclarationModifiers());
							break;
					}
				}
			}
			return null;
		}
		this.binding = (FieldBinding) fieldBinding;

		if (isFieldUseDeprecated(this.binding, scope, (this.bits & IsStrictlyAssignedMASK) != 0)) {
			scope.problemReporter().javadocDeprecatedField(this.binding, this, scope.getDeclarationModifiers());
		}
		return this.resolvedType = this.binding.type;
	}
