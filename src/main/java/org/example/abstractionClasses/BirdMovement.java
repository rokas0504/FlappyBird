package org.example.abstractionClasses;

import org.example.Constants;

public class BirdMovement {
    public int birdVelocity = 0, birdAcceleration = 8, birdI = 1;
    public int birdPosition = Constants.BIRD_STARTING_HEIGHT + birdVelocity;

    public void updateBirdPhysics() {
        birdAcceleration += birdI;
        birdVelocity += birdAcceleration;
        birdPosition = Constants.BIRD_STARTING_HEIGHT + birdVelocity;
    }
    public int getBirdPosition() {
        return birdPosition;
    }
    public void setBirdAcceleration(){
        birdAcceleration = -12;
    }
    public void resetBird(){
        birdVelocity = 0;
        birdAcceleration = 0;
        birdPosition = Constants.BIRD_STARTING_HEIGHT + birdVelocity;
    }
}
