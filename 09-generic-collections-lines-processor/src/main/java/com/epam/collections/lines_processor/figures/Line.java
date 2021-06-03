package com.epam.collections.lines_processor.figures;

import org.apache.commons.math3.util.Precision;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class that stores lines data, represents by equation y = kx + b
 */
public class Line {
    private static final int NEGATIVE_ZERO = -0;
    Set<Point> points = new HashSet<>();
    private double k;
    private double b;

    public Line(Point point1, Point point2) {
        points.add(point1);
        points.add(point2);
        calculateCoefficients(point1, point2);
    }

    private void calculateCoefficients(Point point1, Point point2) {
        setK((double) (point1.getY() - point2.getY()) / (point1.getX() - point2.getX()));
        if (k == NEGATIVE_ZERO) {
            k = 0;
        }
        if (Double.isInfinite(k)) {
            k = Double.POSITIVE_INFINITY;
        }
        setB(point2.getY() - k * point2.getX());
        this.b = Precision.round(this.b, 2);
        this.k = Precision.round(this.k, 2);
    }

    public void setK(double k) {
        this.k = k;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getK() {
        return k;
    }

    public double getB() {
        return b;
    }

    public void setPoints(Set<Point> points) {
        this.points = points;
    }

    public Set<Point> getPoints() {
        return points;
    }

    public void compress(Line other) {
        if (Double.compare(other.getK(), getK()) == 0 && Double.compare(other.getB(), getB()) == 0) {
            this.points.addAll(other.points);
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Line line = (Line) o;
        return Double.compare(line.getK(), getK()) == 0 &&
                Double.compare(line.getB(), getB()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }

    @Override
    public String toString() {
        return "Line{" +
                ", points=" + points +
                '}';
    }
}