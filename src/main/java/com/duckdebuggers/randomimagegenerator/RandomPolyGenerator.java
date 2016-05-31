package com.duckdebuggers.randomimagegenerator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stmsalah1 on 5/16/2016.
 */
public class RandomPolyGenerator extends RandomImageGenerator {

    public Coords[] generatePoints(int width, int height) {
        int number = 15;
        Coords[] points = new Coords[number];
        for (int i = 0; i < number; i++) {
            points[i] = new Coords((int) (Math.random() * width), (int) (Math.random() * height));
        }
        return points;
    }

    @Override
    public Color[][] draw(int width, int height) {

        Color fore = Color.BLACK;
        Color back = Color.WHITE;

        Color[][] grid = new Color[height][width];

        Coords[] points = null;
        boolean valid = false;
        while (!valid) {
            points = generatePoints(width, height);
            valid = true;
            for (int i = 0; i < points.length; i++) {
                for (int j = 0; j < points.length; j++) {
                    if (intersects(points[i], points[(i+1)%points.length], points[j], points[(j+1)%points.length])) {
                        valid = false;
                    }
                }
            }
        }

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (intersects(points[i], points[(i+1)%points.length], points[j], points[(j+1)%points.length])) {
                    valid = false;
                }
            }
        }


        for (int row = 0; row < height; row++) {
            List<Double> intersections = new ArrayList<>();
            for (int line = 0; line < points.length; line++) {
                if (intersects(points[line], points[(line + 1) % points.length], row)) {
                    intersections.add(intersection(points[line], points[(line + 1) % points.length], row));
                }
            }
            for (int col = 0; col < width; col++) {
                int finalCol = col;
                grid[row][col] = intersections.stream().filter(d -> d < finalCol).count() % 2 == 0 ? back : fore;
            }
            intersections.clear();
        }

        return grid;
    }

    private boolean intersects(Coords a, Coords b, Coords c, Coords d) {
        double x, y;
        if (a.x == b.x) {
            if (c.x == d.x) {
                if (a.x == c.x) {
                    return Math.min(a.y, b.y) < Math.max(c.y, d.y) && Math.min(c.y, d.y) < Math.max(a.y, b.y);
                } else {
                    return false;
                }
            } else {
                x = a.x;
                double m2 = (d.y - c.y) / (double) (d.x - c.x);
                double b2 = c.y - c.x * m2;
                y = m2 * x + b2;
            }
        } else {
            double m1, b1;
            m1 = (b.y - a.y) / (double) (b.x - a.x);
            b1 = a.y - a.x * m1;
            if (c.x == d.x) {
                x = c.x;
            } else {
                double m2 = (d.y - c.y) / (double) (d.x - c.x);
                double b2 = c.y - c.x * m2;
                x = (b1 - b2) / (m2 - m1);
            }
            y = m1 * x + b1;
        }
        if ((a.y < y) == (b.y < y))
            return false;
        if ((a.x < x) == (b.x < x))
            return false;
        if ((c.y < y) == (d.y < y))
            return false;
        if ((c.x < x) == (d.x < x))
            return false;
        return true;
    }

    private double intersection(Coords a, Coords b, int row) {
        return a.x + ((b.x - a.x) * (row - a.y) / (double) (b.y - a.y));
    }

    private boolean intersects(Coords a, Coords b, int row) {
        return (a.y < row) != (b.y < row);
    }

    private static class Coords {
        final int x;
        final int y;

        @Override
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }

        private Coords(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
