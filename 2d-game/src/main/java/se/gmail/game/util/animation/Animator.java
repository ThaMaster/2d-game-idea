package se.gmail.game.util.animation;

import java.awt.Graphics2D;
import java.util.HashMap;

public class Animator {

    private HashMap<String, Animation> animations;
    private Animation currentAnimation;
    private int frameWidth = 0;
    private int frameHeight = 0;

    // private int scaling = 1;

    private boolean flipped = false;

    public Animator() {
        this.animations = new HashMap<>();
    }

    public void addAnimation(Animation animation) {
        animations.put(animation.getName(), animation);
        if(currentAnimation == null) {
            currentAnimation = animation;
            frameWidth = currentAnimation.getFrameWidth();
            frameHeight = currentAnimation.getFrameHeight();
        }
    }

    public Animation getCurrentAnimation() {
        return this.currentAnimation;
    }

    public void setAnimation(String name) {
        Animation animation = animations.get(name);
        if(animation != null && animation != currentAnimation) {
            currentAnimation.reset();
            currentAnimation = animation;
            frameWidth = currentAnimation.getFrameWidth();
            frameHeight = currentAnimation.getFrameHeight();
        }
    }

    public void update() {
        if(currentAnimation != null) {
            currentAnimation.update();
        }
    }

    public void draw(Graphics2D g2d, int x, int y) {
        if(currentAnimation != null) {
            if(flipped) {
                g2d.drawImage(currentAnimation.getCurrentFrame(), frameWidth+x, y, -frameWidth, frameHeight, null);
            } else {
                g2d.drawImage(currentAnimation.getCurrentFrame(), x, y, frameWidth, frameHeight, null);
            }
        }
    }

    public void setFlipped(boolean b) {
        this.flipped = b;
    }
}
