package se.gmail.game.util.animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {

    String name;

    private ArrayList<BufferedImage> frames;

    private int frameIndex;
    private int frameCount;
    private int frameDelay;
    private int frameTimer;

    public Animation(String name, ArrayList<BufferedImage> frames, int frameDelay) {
        this.name = name;
        this.frames = frames;
        this.frameDelay = frameDelay;
        this.frameCount = frames.size();
        this.frameIndex = 0;
        this.frameTimer = 0;
    }

    public void update() {
        frameTimer++;
        if(frameTimer >= frameDelay) {
            frameTimer = 0;
            frameIndex = (frameIndex + 1) % frameCount;
        }
    }

    public BufferedImage getCurrentFrame() {
        return this.frames.get(frameIndex);
    }

    public void reset() {
        frameIndex = 0;
        frameTimer = 0;
    }

    public int getFrameWidth() {
        return this.getCurrentFrame().getWidth();
    }

    public int getFrameHeight() {
        return this.getCurrentFrame().getHeight();
    }

    public String getName() {
        return this.name;
    }
}
