package com.duckdebuggers.randomimagegenerator;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class RandomImageGenerator {

    public abstract Color[][] draw(int width, int height);
    
}
