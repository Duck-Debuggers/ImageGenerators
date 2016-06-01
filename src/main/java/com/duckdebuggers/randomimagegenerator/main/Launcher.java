package com.duckdebuggers.randomimagegenerator.main;

import com.duckdebuggers.randomimagegenerator.RandomPixelGenerator;
import com.duckdebuggers.randomimagegenerator.RandomPolyGenerator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by stmsalah1 on 5/31/2016.
 */
public class Launcher {
    // Configurable Constants //
    private static final String TITLE = "Random Image Generation";
    private static final int WIDTH = 250;
    private static final int HEIGHT = 250;

    public static void main(String[] args) {
        Label heading = new Label("Random Image Generators");
        Label instruction = new Label("Generator instructions:");
        Label instruction2 = new Label("Press F5 to regenerate");
        Label instruction3 = new Label("Press backspace to return to this menu");
        heading.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        instruction.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));

        Button pixelBtn = new Button("Random Pixel Generation");
        Button polyBtn = new Button("Random Polygon Generation");

        Frame frame = new Frame();
        frame.setTitle(TITLE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                System.exit(0);
            }
        });

        pixelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Main(new RandomPixelGenerator());
                frame.setVisible(false);
            }
        });

        polyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Main(new RandomPolyGenerator());
                frame.setVisible(false);
            }
        });

        frame.add(heading);
        frame.add(pixelBtn);
        frame.add(polyBtn);
        frame.add(instruction);
        frame.add(instruction2);
        frame.add(instruction3);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
