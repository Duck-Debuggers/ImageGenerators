package com.duckdebuggers.randomimagegenerator;

import java.awt.*;

public class RandomPixelGenerator extends RandomImageGenerator {

    public RandomPixelGenerator(int width, int height) {
        super(width, height);
    }

    @Override
    public Color[][] draw(int width, int height) {
        Color[][] grid = new Color[height][width];
        for (Color[] row : grid) {
            for (int i = 0; i < row.length; i++) {
                row[i] = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
            }
        }
        return grid;
    }
}
