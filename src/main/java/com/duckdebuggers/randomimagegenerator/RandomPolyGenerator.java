package com.duckdebuggers.randomimagegenerator;

import java.awt.*;

/**
 * Created by stmsalah1 on 5/16/2016.
 */
public class RandomPolyGenerator extends RandomImageGenerator {
    public RandomPolyGenerator(int width, int height) {
        super();
    }

    @Override
    public Color[][] draw(int width, int height) {
        // I honestly don't know how I would do this with just colored pixels...
        Color[][] grid = new Color[height][width];
        return grid;
    }
}
