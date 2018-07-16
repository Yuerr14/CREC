        for (int i = 0; i < itemLabelList.size(); i++) {
            Object[] record = (Object[]) itemLabelList.get(i);
            int series = ((Integer) record[0]).intValue();
            Rectangle2D bar = (Rectangle2D) record[1];
            boolean neg = ((Boolean) record[2]).booleanValue();
            CategoryItemLabelGenerator generator
                    = getItemLabelGenerator(series, column);
            if (generator != null && isItemLabelVisible(series, column)) {
                drawItemLabel(g2, dataset, series, column, plot, generator,
                        bar, neg);
            }

        }
