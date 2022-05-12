package cinema;

import java.util.Objects;

public class Seat {
    private int row;
    private int column;
    private int price;

    Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
    }
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (this == null || obj.getClass() != this.getClass())
            return false;
        Seat seat = (Seat) obj;
        return this.row == ((Seat) obj).row && this.column == ((Seat) obj).column && this.price == ((Seat) obj).price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, price);
    }

}
