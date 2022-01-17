package com.example.sudokusolver;

import java.util.ArrayList;

public class SudokuSolver {

    int[][] board;
    ArrayList<ArrayList<Object>> emptyBoxIndex;

    private static int selected_row;
    private static int selected_column;

    SudokuSolver() {
        selected_row = -1;
        selected_column = -1;

        board = new int[9][9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                board[r][c] = 0;
            }
        }

        emptyBoxIndex = new ArrayList<>();
    }

    private void getEmptyBoxIndexes () {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c< 9; c++) {
                if (this.board[r][c] == 0) {
                    this.emptyBoxIndex.add(new ArrayList<>());
                    this.emptyBoxIndex.get(this.emptyBoxIndex.size() - 1).add(r);
                    this.emptyBoxIndex.get(this.emptyBoxIndex.size() - 1).add(c);
                }
            }
        }
    }

    public void setNumberPos(int num) {
        if (this.selected_row != -1 && this.selected_column != -1) {
            if (this.board[this.selected_row - 1][this.selected_column - 1] == num) {
                this.board[this.selected_row - 1][this.selected_column - 1] = 0;
            } else {
                this.board[this.selected_row - 1][this.selected_column - 1] = num;
            }
        }
    }

    public int[][] getBoard() {
        return this.board;
    }

    public ArrayList<ArrayList<Object>> getEmptyBoxIndex() {
        return emptyBoxIndex;
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
