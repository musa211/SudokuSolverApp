package com.example.sudokusolver;

public class SudokuSolver {

    private static int selected_row;
    private static int selected_column;

    SudokuSolver() {
        selected_row = -1;
        selected_column = -1;
    }

    public int getSelectedRow() {
        return selected_row;
    }

    public void setSelectedRow(int row) {
        selected_row = row;
    }

    public int getSelectedColumn() {
        return selected_column;
    }

    public void setSelectedColumn(int column) {
        selected_column = column;
    }
}
