package com.epam.collections.lines_processor;

import com.epam.collections.lines_processor.figures.Line;
import com.epam.collections.lines_processor.figures.Point;
import com.epam.collections.lines_processor.model.LinesProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class LinesProcessorTestCase {

    @Test
    public void testLineCreation() {
        Point point1 = new Point(2, 3);
        Point point2 = new Point(5, 10);
        Line line = new Line(point1, point2);
        assertEquals(String.format("Coefficient k calculated wrongly for line (%s, %s)", point1, point2),
                2.33, line.getK(), 0);
        assertEquals(String.format("Coefficient b calculated wrongly for line (%s, %s)", point1, point2),
                -1.67, line.getB(), 0);
    }

    @Test
    public void testLineCreationWithKEqualsToZero() {
        Point point1 = new Point(2, 3);
        Point point2 = new Point(5, 3);
        Line line = new Line(point1, point2);
        assertEquals(String.format("Coefficient k calculated wrongly for line (%s, %s)", point1, point2),
                0, line.getK(), 0);
        assertEquals(String.format("Coefficient b calculated wrongly for line (%s, %s)", point1, point2),
                3, line.getB(), 0);
    }

    @Test
    public void testLineCreationWithBEqualsToZero() {
        Point point1 = new Point(3, 2);
        Point point2 = new Point(3, 5);
        Line line = new Line(point1, point2);
        assertTrue(String.format("Coefficient k calculated wrongly for line (%s, %s)", point1, point2), Double.isInfinite(line.getK()));
        assertTrue(String.format("Coefficient b calculated wrongly for line (%s, %s)", point1, point2), Double.isInfinite(line.getB()));
    }

    @Test
    public void testLineCreationShouldNotChangeBecauseOfSwappedPoints() {
        Point point1 = new Point(2, 3);
        Point point2 = new Point(5, 10);
        Line line1 = new Line(point1, point2);
        Line line2 = new Line(point2, point1);
        assertEquals("Line(A, B) should be equals to Line(B, A)", line2, line1);
    }

    @Test
    public void testMapPointsToLines() {
        LinesProcessor processor = new LinesProcessor();
        Point pointA = new Point(1, 1);
        Point pointB = new Point(4, 6);
        Point pointC = new Point(7, -2);
        List<Point> points = Arrays.asList(pointA, pointB, pointC);
        List<Line> lines = processor.mapPointsToLines(points);
        assertEquals(String.format("Amount of lines in result of method mapPointsToLines should be equals to (n ^ 2) - n. Input: %s", points),
                6, lines.size());
        assertThat(String.format("Wrong result of method mapPointsToLines, input is %s", points), lines, containsInAnyOrder(
                new Line(pointA, pointB), new Line(pointB, pointA), new Line(pointA, pointC), new Line(pointC, pointA),
                new Line(pointB, pointC), new Line(pointC, pointB)
        ));
    }

    @Test
    public void testReduceLines() {
        LinesProcessor processor = new LinesProcessor();
        Point pointA = new Point(1, 1);
        Point pointB = new Point(2, 2);
        Point pointC = new Point(3, 3);
        List<Line> lines = Arrays.asList(new Line(pointA, pointB), new Line(pointB, pointC));
        List<Line> result = processor.reduceLines(lines);
        Line expectedLine = new Line(pointA, pointB);
        Set<Point> expectedPoints = expectedLine.getPoints();
        expectedPoints.add(pointC);
        expectedLine.setPoints(expectedPoints);
        List<Line> expected = Collections.singletonList(expectedLine);
        assertEquals(String.format("Wrong amount of lines as result of method reduceLines, input is %s", lines), 1, result.size());
        assertThat(String.format("Wrong amount of points in result line of method reduceLines, input is %s", lines),
                expected.get(0).getPoints(), containsInAnyOrder(result.get(0).getPoints().toArray()));
    }

}
