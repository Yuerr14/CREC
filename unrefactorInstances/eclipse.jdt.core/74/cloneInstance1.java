    {
        final int modBits = getDeclarationBinding().getModifiers();
        final List<Modifier> mods = new ArrayList<Modifier>(4);
        if( org.eclipse.jdt.core.dom.Modifier.isAbstract(modBits) )		
        	mods.add(Modifier.ABSTRACT);
        if( org.eclipse.jdt.core.dom.Modifier.isFinal(modBits) ) 		
        	mods.add(Modifier.FINAL);
        if( org.eclipse.jdt.core.dom.Modifier.isNative(modBits) ) 		
        	mods.add(Modifier.NATIVE);
        if( org.eclipse.jdt.core.dom.Modifier.isPrivate(modBits) ) 		
        	mods.add(Modifier.PRIVATE);
        if( org.eclipse.jdt.core.dom.Modifier.isProtected(modBits) ) 
        	mods.add(Modifier.PROTECTED);
        if( org.eclipse.jdt.core.dom.Modifier.isPublic(modBits) ) 	
        	mods.add(Modifier.PUBLIC);
        if( org.eclipse.jdt.core.dom.Modifier.isStatic(modBits) ) 	
        	mods.add(Modifier.STATIC);
        if( org.eclipse.jdt.core.dom.Modifier.isStrictfp(modBits) ) 
        	mods.add(Modifier.STRICTFP);
        if( org.eclipse.jdt.core.dom.Modifier.isSynchronized(modBits) ) 
        	mods.add(Modifier.SYNCHRONIZED);
        if( org.eclipse.jdt.core.dom.Modifier.isTransient(modBits) ) 
        	mods.add(Modifier.TRANSIENT);
        if( org.eclipse.jdt.core.dom.Modifier.isVolatile(modBits) ) 	
        	mods.add(Modifier.VOLATILE);
        return mods;
    }
