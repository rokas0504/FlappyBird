package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class GraphicsHandler {
    private Image birdFrame1, birdFrame2, birdFrame3;
    private int frameIndex = 0;
    private boolean isFlapping = false;
    private Timer flapTimer;
    private Rectangle birdHitbox;

    public GraphicsHandler() {
        try {
            birdFrame1 = ImageIO.read(getClass().getResource("/PNG/bird1.png"));
            birdFrame2 = ImageIO.read(getClass().getResource("/PNG/bird2.png"));
            birdFrame3 = ImageIO.read(getClass().getResource("/PNG/bird3.png"));
            birdHitbox = new Rectangle(250, 250, Constants.HITBOX_WIDTH, Constants.HITBOX_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawWall(Graphics g, int x, int y, int w, int h, int gapPosition) {

        g.setColor(Color.RED);
        g.fillRect(x, y, w, gapPosition);
        g.fillRect(x, gapPosition + 150, w, h);

    }

    public void drawBird(Graphics g, int x, int birdPosition, int width, int height) {
        birdHitbox.setLocation(x, birdPosition);
        Image birdImage = null;
        int birdWidth = width;
        int birdHeight = height;

        if (isFlapping) {
            switch (frameIndex) {
                case 0:
                    birdImage = birdFrame1;
                    birdHeight = 60;
                    break;
                case 1:
                    birdImage = birdFrame2;
                    birdHeight = 100;
                    break;
                case 2:
                    birdImage = birdFrame3;
                    birdHeight = 100;
                    break;
                case 3:
                    birdImage = birdFrame2;
                    birdHeight = 100;
                    break;
                case 4:
                    birdImage = birdFrame1;
                    birdHeight = 60;
                    break;
            }
            g.drawImage(birdImage, x, birdPosition, birdWidth, birdHeight, null);
        } else {
            g.drawImage(birdFrame1, x, birdPosition, birdWidth, birdHeight, null);
        }
    }

    public void triggerFlap() {
        if (isFlapping) return;

        isFlapping = true;
        frameIndex = 0;

        if (flapTimer != null) {
            flapTimer.cancel();
        }

        flapTimer = new Timer();
        flapTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateFlapFrame();
            }
        }, 0, Constants.FLAP_CYCLE_DURATION);
    }
    private void updateFlapFrame() {
        frameIndex++;
        if (frameIndex >= Constants.FLAP_FRAMES) {
            isFlapping = false;
            flapTimer.cancel();
        }
    }
    public Rectangle getBirdHitbox() {
        return birdHitbox;
    }

    public void drawScore(Graphics g, int score, int x, int y){
        g.setColor(Color.YELLOW);
        g.drawString("Score: " + score, x, y);
    }
}
