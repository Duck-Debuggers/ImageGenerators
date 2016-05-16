package com.duckdebuggers.randomimagegenerator.main;

import com.duckdebuggers.randomimagegenerator.RandomImageGenerator;
import com.duckdebuggers.randomimagegenerator.RandomPixelGenerator;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by stmsalah1 on 4/6/2016.
 */
public class Main extends Applet {

    public class CanvasPanel extends JPanel {
        public BufferedImage canvas = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            ((Graphics2D)graphics).drawImage(canvas, null, null);
        }

        public Dimension getPreferredSize() {
            return new Dimension(canvas.getWidth(), canvas.getHeight());
        }
    }

    @Override
    public void init() {
        CanvasPanel drawPanel = new CanvasPanel();
        BufferedImage canvas = drawPanel.canvas;

        RandomImageGenerator generator = new RandomPixelGenerator();
        Color[][] image = generator.draw(300,300);

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                canvas.setRGB(i, j, image[i][j].getRGB());
            }
        }

        add(drawPanel);
    }
}
