package com.epam.collections.lines_processor.controller;

import com.epam.collections.lines_processor.figures.Line;
import com.epam.collections.lines_processor.figures.Point;
import com.epam.collections.lines_processor.model.FileReader;
import com.epam.collections.lines_processor.view.LineView;
import com.epam.collections.lines_processor.model.LinesProcessor;

import java.util.List;
import java.util.Set;

public class LineController {
    private final LinesProcessor linesProcessor;
    private final LineView lineView;
    public LineController(LinesProcessor linesProcessor, LineView lineView) {
        this.linesProcessor = linesProcessor;
        this.lineView = lineView;
    }

    public void run(String src) {
        FileReader fileReader = new FileReader();
        Set<Point> points = linesProcessor.createPointsSet(fileReader.createSetStringFromFile(fileReader.readFile(src)));
        List<Line> lines = linesProcessor.mapPointsToLines(points);
        List<Line> straightLine = linesProcessor.reduceLines(lines);
        lineView.printLines(straightLine);
    }
}
