            if (count > 0) {
                if (count % 2 == 1) {
                    if (count > 1) {
                        Number value = (Number) values.get((count - 1) / 2);
                        result = value.doubleValue();
                    }
                    else {
                        Number value = (Number) values.get(0);
                        result = value.doubleValue();
                    }
                }
                else {
                    Number value1 = (Number) values.get(count / 2 - 1);
                    Number value2 = (Number) values.get(count / 2);
                    result = (value1.doubleValue() + value2.doubleValue())
                             / 2.0;
                }
            }
