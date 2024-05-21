package se.gmail.game.util;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Animator {

    private HashMap<String, ArrayList<BufferedImage>> animationSprites;

    private String currentAnimationName = "";
    private ArrayList<BufferedImage> currentAnimation;
    private BufferedImage currentSprite;
    private int currentFrame;

    private int updateCount;
    private int animationSpeed;

    public Animator() {
        animationSprites = new HashMap<>();
        currentAnimation = new ArrayList<>();

        addAnimation("idle", "/playerSprites/idle");
        addAnimation("run", "/playerSprites/run");

        setDefaultValues();
    }

    public void setDefaultValues() {
        changeAnimation("run");
        animationSpeed = 10;
        updateCount = 0;
    }

    public void addAnimation(String name, String path) {
        ArrayList<BufferedImage> newAnimation = ImageLoader.loadWholeDirectory(path);
        if(newAnimation != null) {
            animationSprites.put(name, newAnimation);
        }
    }

    public ArrayList<BufferedImage> getSprites(String animationName) {
        if(!animationSprites.containsKey(animationName)) {
            return null;
        } 
        return animationSprites.get(animationName);
    }

    public void setAnimationSpeed(int newSpeed) {
        this.animationSpeed = newSpeed;
    }

    public String getCurrentAnimation() {
        return this.currentAnimationName;
    }

    public void changeAnimation(String animationName) {
        if(!currentAnimationName.equals(animationName)) {
            this.currentAnimationName = animationName;
            this.currentAnimation = animationSprites.get(animationName);
            this.currentSprite = currentAnimation.get(0);
            this.currentFrame = 0;
        }
    }

    public BufferedImage getCurrentSprite() {
        return this.currentSprite;
    }

    public void update() {
        updateCount++;
        if(updateCount > animationSpeed) {
            if((currentFrame + 1) != currentAnimation.size()) {
                currentFrame++;
            } else {
                currentFrame = 0;
            }
            currentSprite = currentAnimation.get(currentFrame);
            updateCount = 0;
        }
    }
}
