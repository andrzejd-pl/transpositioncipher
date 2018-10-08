package pl.andrzejd;

import java.util.ArrayList;

public class Key {
    private int x;
    private ArrayList<Integer> columns;

    private Key(int x, ArrayList<Integer> columns) {
        this.x = x;
        this.columns = columns;
    }

    public int getX() {
        return x;
    }

    public ArrayList<Integer> getColumns() {
        return columns;
    }

    public static class Builder {
        private int x = 0;
        private ArrayList<Integer> columns;

        public Builder() {
            columns = new ArrayList<>();
        }

        public Builder setX(int x) {
            this.x = x;

            return this;
        }

        public Builder setColumn(int column) {
            this.columns.add(column);

            return this;
        }

        public Key build() throws Exception {
            if (x == 0) {
                throw new Exception("Not exist dimension x");
            }

            if (columns.size() < x) {
                throw new Exception("Bad amount of columns");
            }

            for (Integer column : columns) {
                if (column < 0 || column > x) {
                    throw new Exception("Bad column " + column);
                }
            }

            return new Key(x, columns);
        }
    }
}
