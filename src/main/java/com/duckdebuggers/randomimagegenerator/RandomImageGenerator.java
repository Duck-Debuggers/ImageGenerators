package com.duckdebuggers.randomimagegenerator;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class RandomImageGenerator extends Canvas {
    private BufferedImage canvas;

    private int width;
    private int height;
    private Color[][] colorGrid;

    public RandomImageGenerator(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        this.canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    public void paint(Graphics g) {
        super.paint(g);
        this.colorGrid = draw(this.width, this.height);
        for(int x = 0; x < colorGrid.length; x++) {
            for(int y = 0; y < colorGrid[x].length; y++) {
                canvas.setRGB(x, y, colorGrid[x][y].getRGB());
            }
        }
        ((Graphics2D)g).drawImage(canvas, null, null);
    }

    public abstract Color[][] draw(int width, int height);
}
