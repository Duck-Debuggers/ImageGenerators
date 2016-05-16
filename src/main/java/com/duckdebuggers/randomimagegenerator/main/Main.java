package com.duckdebuggers.randomimagegenerator.main;

import com.duckdebuggers.randomimagegenerator.RandomImageGenerator;
import com.duckdebuggers.randomimagegenerator.RandomPixelGenerator;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by stmsalah1 on 4/6/2016.
 */
public class Main {
    // Configurable Constants //
    private static final String TITLE = "Random Image Generation (Press F5 to refresh)";
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private static RandomImageGenerator pixelGenerator = new RandomPixelGenerator(WIDTH, HEIGHT);
    private static KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent keyEvent) {
            super.keyReleased(keyEvent);

            //System.out.println(keyEvent.getKeyCode());
            if(keyEvent.getKeyCode() == 116) {
                pixelGenerator.paint(pixelGenerator.getGraphics());
            }
        }
    };

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setTitle(TITLE);
        frame.setSize(WIDTH, HEIGHT);
        frame.addKeyListener(keyAdapter);
        pixelGenerator.addKeyListener(keyAdapter);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                System.exit(0);
            }
        });
        frame.setFocusable(true);
        pixelGenerator.setFocusable(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(pixelGenerator);
        frame.setVisible(true);
    }
}
