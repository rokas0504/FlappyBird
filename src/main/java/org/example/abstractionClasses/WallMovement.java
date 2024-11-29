package org.example.abstractionClasses;

import org.example.Constants;
import org.example.GraphicsHandler;

import java.awt.*;

public class WallMovement {
    private GraphicsHandler graphicsHandler = new GraphicsHandler();
    public int [] wallX = {Constants.WIDTH, Constants.WIDTH + Constants.WIDTH / 2, };
    public int [] gapPlace ={(int)(Math.random() * (Constants.HEIGHT - 150)), (int)(Math.random() * (Constants.HEIGHT - 100))};

    public void moveWalls() {
        for (int i = 0; i < Constants.WALL_COUNT; i++) {
            wallX[i] -= Constants.WALL_VELOCITY;
        }
    }
    public void drawWalls(Graphics g){
        for (int i = 0; i < Constants.WALL_COUNT; i++) {
            graphicsHandler.drawWall(g, wallX[i], 0, Constants.WALL_WIDTH, Constants.HEIGHT, gapPlace[i]);
        }
    }
    public void resetWalls(){
        for(int i = 0; i < Constants.WALL_COUNT; i++){
            wallX[0] = Constants.WIDTH;
            wallX[1] = Constants.WIDTH + Constants.WIDTH / 2;
            gapPlace[0] = (int)(Math.random() * (Constants.HEIGHT - 150));
            gapPlace[1] = (int)(Math.random() * (Constants.HEIGHT - 100));
        }
    }

    public Rectangle wallReactTop(int i){
       return new Rectangle(wallX[i], 0, Constants.WALL_WIDTH, gapPlace[i]);
    }

    public Rectangle wallReactBottom(int i){
        return new Rectangle(wallX[i], gapPlace[i] + 150, Constants.WALL_WIDTH, Constants.HEIGHT);
    }

    public boolean isWallPassed(int i){
        if(250 > (wallX[i] + Constants.WALL_WIDTH)) return true;
        return false;
    }

    public boolean isNoMoreWalls(int i){
        if (wallX[i] + Constants.WALL_WIDTH <= 0) return true;
        return false;
    }

    public void resetWallsPosition(int i){
        wallX[i] = Constants.WIDTH;
        gapPlace[i] = (int) (Math.random() * (Constants.HEIGHT - 150));
    }
}
