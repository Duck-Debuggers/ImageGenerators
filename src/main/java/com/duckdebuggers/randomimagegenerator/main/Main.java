package com.duckdebuggers.randomimagegenerator.main;

import com.duckdebuggers.randomimagegenerator.RandomImageGenerator;
import com.duckdebuggers.randomimagegenerator.RandomPixelGenerator;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * Created by stmsalah1 on 4/6/2016.
 */
public class Main extends Applet {
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

	    RandomImageGenerator generator = new RandomPixelGenerator(400,400);
	    Color[][] image = generator.draw(300,300);

	    for (int i = 0; i < image.length; i++) {
		for (int j = 0; j < image[i].length; j++) {
		    canvas.setRGB(i, j, image[i][j].getRGB());
		}
	    }

	    add(drawPanel);
	}
}
