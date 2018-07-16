            for (int i = 0; i < constructors.length; i++) {

                final Class[] args = constructors[i].getParameterTypes();

                if (args.length == 7
                        &&  args[args.length-1].equals(AffineTransform.class)) {

                    return (Paint) constructors[i].newInstance(new Object[] {
                        (Point2D) invokeZeroArgumentMethod(paint, "getStartPoint"),
                        (Point2D) invokeZeroArgumentMethod(paint, "getEndPoint"),
                        (float[]) invokeZeroArgumentMethod(paint, "getFractions"),
                        paintColours,
                        (Object) invokeZeroArgumentMethod(paint, "getCycleMethod"),
                        (Object) invokeZeroArgumentMethod(paint, "getColorSpace"),
                        (AffineTransform) invokeZeroArgumentMethod(paint,
                            "getTransform") });
                    }
                }
