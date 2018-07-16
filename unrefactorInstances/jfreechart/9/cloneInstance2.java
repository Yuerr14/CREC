            for (int i = 0; i < constructors.length; i++) {

                final Class[] args = constructors[i].getParameterTypes();

                if (     args.length == 8
                &&  args[args.length-1].equals(AffineTransform.class)) {

                    return (Paint) constructors[i].newInstance(new Object[] {
                        (Point2D) invokeZeroArgumentMethod(paint, "getCenterPoint"),
                        (Float) invokeZeroArgumentMethod(paint, "getRadius"),
                        (Point2D) invokeZeroArgumentMethod(paint, "getFocusPoint"),
                        (float[]) invokeZeroArgumentMethod(paint, "getFractions"),
                        paintColours,
                        (Object) invokeZeroArgumentMethod(paint, "getCycleMethod"),
                        (Object) invokeZeroArgumentMethod(paint, "getColorSpace"),
                        (AffineTransform) invokeZeroArgumentMethod(paint,
                            "getTransform") });
                }
            }
