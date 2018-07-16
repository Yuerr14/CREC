            else {
                double yleft = (y0 + y1) / 2.0 + stackLeft[0];
                float transYLeft = (float) rangeAxis.valueToJava2D(yleft,
                        dataArea, edge1);
                left.moveTo((float) xx1, transY1);
                left.lineTo((float) xx1, transStack1);
                left.lineTo((float) xxLeft, transStackLeft);
                left.lineTo((float) xxLeft, transYLeft);
                left.closePath();
            }
