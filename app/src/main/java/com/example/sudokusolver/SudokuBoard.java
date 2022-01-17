package com.example.sudokusolver;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class SudokuBoard extends View {
    private final int boardColor;
    private final int cellFillColor;
    private final int cellHighlightColor;

    private final Paint boardColorPaint = new Paint();
    private final Paint cellFillColorPaint = new Paint();
    private final Paint cellHighlightColorPaint = new Paint();
    private int cellSize;

    private final SudokuSolver solver = new SudokuSolver();

    public SudokuBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SudokuBoard,
                0, 0);

        try {
            boardColor = a.getInteger(R.styleable.SudokuBoard_boardColor, 0);
            cellFillColor = a.getInteger(R.styleable.SudokuBoard_cellFillColor, 0);
            cellHighlightColor = a.getInteger(R.styleable.SudokuBoard_cellHighlightColor, 0);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);

        int dimension = Math.min(this.getMeasuredWidth(), this.getMeasuredHeight());
        cellSize = dimension / 9;

        setMeasuredDimension(dimension, dimension);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // canvas is the total area that view occupied on screen
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(16);
        boardColorPaint.setColor(boardColor);
        boardColorPaint.setAntiAlias(true);

        cellFillColorPaint.setStyle(Paint.Style.FILL);
        cellFillColorPaint.setColor(cellFillColor);
        cellFillColorPaint.setAntiAlias(true);

        cellHighlightColorPaint.setStyle(Paint.Style.FILL);
        cellHighlightColorPaint.setColor(cellHighlightColor);
        cellHighlightColorPaint.setAntiAlias(true);

        colorCell(canvas, solver.getSelectedRow(), solver.getSelectedColumn());
        canvas.drawRect(0, 0, getWidth(), getHeight(), boardColorPaint);
        drawBoard(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean isValid;

        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            solver.setSelectedRow((int) Math.ceil(y/cellSize));
            solver.setSelectedColumn((int) Math.ceil(x/cellSize));
            isValid = true;
        } else {
            isValid = false;
        }
        return isValid;
    }

    private void colorCell(Canvas canvas, int row, int column) {
        if (solver.getSelectedRow() != -1 && solver.getSelectedColumn() != -1) {
            canvas.drawRect((column - 1) * cellSize, 0, column * cellSize,
                    cellSize * 9, cellHighlightColorPaint);

            canvas.drawRect(0, (row - 1) * cellSize, cellSize * 9,
                    row * cellSize, cellHighlightColorPaint);

            canvas.drawRect((column - 1) * cellSize, (row - 1) * cellSize,
                    column * cellSize, row * cellSize, cellHighlightColorPaint);
        }
        invalidate();
    }

    private void drawThickLine() {
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(10);
        boardColorPaint.setColor(boardColor);
    }

    private void drawThinLine() {
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(4);
        boardColorPaint.setColor(boardColor);
    }

    private void drawBoard(Canvas canvas) {
        for (int c = 0; c < 10; c++) {
            if (c % 3 == 0) {
                drawThickLine();
            } else {
                drawThinLine();
            }
            canvas.drawLine(cellSize * c, 0,
                    cellSize * c, getWidth(), boardColorPaint);
        }

        for (int r = 0; r < 10; r++) {
            if (r % 3 == 0) {
                drawThickLine();
            } else {
                drawThinLine();
            }
            canvas.drawLine(0, cellSize * r,
                    getWidth(), cellSize * r, boardColorPaint);
        }
    }
}
