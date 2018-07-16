		if (visitor.visit(this, scope)) {
			for (int i = 0, max = this.typeArguments.length; i < max; i++) {
				if (this.typeArguments[i] != null) {
					for (int j = 0, max2 = this.typeArguments[i].length; j < max2; j++) {
						this.typeArguments[i][j].traverse(visitor, scope);
					}
				}
			}
		}
