package com.epam.collections.lines_processor.view;

import com.epam.collections.lines_processor.figures.Line;

import java.util.List;

public class LineView {
    private static final String GENERAL_LINE = "Line: y = %.2fx + %.2f %s%n";
    private static final String VERTICAL_LINE = "Line: x = %.2f %s%n";
    private static final String LINE_THROUGH_ORIGIN = "Line: y = %.2f + x %s%n";
    private static final String HORIZONTAL_LINE = "Line: y = %.2f %s%n";

    public void printLines(List<Line> straightLine) {
        for (Line line : straightLine) {
            printLineDetail(line);
        }
    }

    private void printLineDetail(Line line) {
        if (line.getK() == Double.POSITIVE_INFINITY || line.getB() == 0) {
            if (line.getK() == Double.POSITIVE_INFINITY) {
                 System.out.printf(VERTICAL_LINE, line.getB(), line.getPoints());
            }
            if (line.getB() == 0) {
                System.out.printf(LINE_THROUGH_ORIGIN, line.getK(),line.getPoints());
            }
        } else {
            if (line.getK() == 0) {
                System.out.printf(HORIZONTAL_LINE, line.getB(),line.getPoints());
            } else {
                System.out.printf(GENERAL_LINE,line.getK(), line.getB(),line.getPoints());
            }
        }
    }
}