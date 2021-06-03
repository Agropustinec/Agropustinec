package com.epam.collections.lines_processor.model;

import com.epam.collections.lines_processor.figures.Line;
import com.epam.collections.lines_processor.figures.Point;

import java.util.*;

/**
 * Class for working with inventory
 */
public class LinesProcessor {
    private static final String REGEX_TO_SPLIT_FOR_COORDINATES = "[;]";

    public Set<Point> createPointsSet(Iterable<String> setWithPointsInfo) {
        Set<Point> setOfPoints = new HashSet<>();
        for (String pointInfo : setWithPointsInfo) {
            setOfPoints.add(createPoint(pointInfo));
        }
        return setOfPoints;
    }

    public Point createPoint(String stringToSplit) {
        Point point;
        String[] splitStrings = stringToSplit.split(REGEX_TO_SPLIT_FOR_COORDINATES);
        point = new Point(Integer.parseInt(splitStrings[0]), Integer.parseInt(splitStrings[1]));
        return point;
    }

    /**
     * Generate all available lines by list of points (with duplicates). Result size should be equals to (n ^ 2) - n
     *
     * @param points Points to generate lines
     * @return List of all available lines
     */
    public List<Line> mapPointsToLines(Iterable<Point> points) {
        List<Line> lines = new ArrayList<>();
        for (Point firstPoint : points) {
            for (Point secondPoint : points) {
                addLinesIfExists(lines, firstPoint, secondPoint);
            }
        }
        return lines;
    }
    private void addLinesIfExists(List<Line> listToadd, Point firstPoint, Point secondPoint){
        if (!firstPoint.equals(secondPoint)){
            listToadd.add(new Line(firstPoint, secondPoint));
        }
    }

    /**
     * Reduce amount of lines - unite same lines build by different points in one line
     *
     * @param lines List of lines to be reduced
     * @return List of lines without duplicates
     */
    public List<Line> reduceLines(Iterable<Line> lines) {
        Set<Line> reducedLines = new HashSet<>();
        for (Line firstLine : lines) {
            lineEqualityCheck(lines, firstLine);
            reducedLines.add(firstLine);
        }
        return new ArrayList<>(reducedLines);
    }

    private void lineEqualityCheck(Iterable<Line> lines, Line firstLine) {
        for (Line secondLine : lines) {
            if (firstLine.getPoints().equals(secondLine.getPoints())) {
                continue;
            }
            firstLine.compress(secondLine);
        }
    }
}