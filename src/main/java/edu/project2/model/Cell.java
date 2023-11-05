package edu.project2.model;

public class Cell {
    private final int row;
    private final int column;
    private Type type;

    public Cell(int row, int column, Type type) {
        this.row = row;
        this.column = column;
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        WALL, PASSAGE
    }
}
