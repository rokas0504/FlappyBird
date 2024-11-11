//package org.example;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.io.IOException;
//import java.awt.Rectangle;
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class GraphicsHandler {
//    private Image birdFrame1, birdFrame2, birdFrame3;
//    private int frameIndex = 0;
//    private boolean isFlapping = false;
//    private Timer flapTimer;
//    private Rectangle birdHitbox;
//
//    public GraphicsHandler() {
//        try {
//            birdFrame1 = ImageIO.read(getClass().getResource("/PNG/bird1.png"));
//            birdFrame2 = ImageIO.read(getClass().getResource("/PNG/bird2.png"));
//            birdFrame2 = ImageIO.read(getClass().getResource("/PNG/bird3.png"));
//            birdHitbox = new Rectangle(250, 250, 50, 50);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void drawWall(Graphics g, int x, int y, int w, int h, int gp) {
//        g.setColor(Color.RED);
//        g.fillRect(x, y, w, h);
//
//        g.setColor(Color.BLACK);
//        g.fillRect(x, gp, w, 150);
//    }
//
//    public void drawBird(Graphics g, int x, int bp, int w, int h) {
//        birdHitbox.setLocation(x, bp);
//        Image birdImage = birdFrame1;
//        if(isFlapping) {
//            switch (frameIndex) {
//                case 0:
//                    birdImage = birdFrame1;
//                    break;
//                case 1:
//                    birdImage = birdFrame2;
//                    break;
//                case 2:
//                    birdImage = birdFrame3;
//                    break;
//                case 3:
//                    birdImage = birdFrame2;
//                    break;
//                case 4:
//                    birdImage = birdFrame1;
//                    break;
//            }
//            g.drawImage(birdImage, x, bp, w, h, null);
//        } else {
//            g.drawImage(birdFrame1, x, bp, w, h, null);
//        }
//    }
//
//    public void triggerFlap(){
//
//        if (!isFlapping) {
//            isFlapping = true;
//            frameIndex = 0;
//
//            if (flapTimer != null) {
//                flapTimer.cancel();
//            }
//
//            flapTimer = new Timer();
//            flapTimer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    frameIndex++;
//
//                    if (frameIndex > 4) {
//                        frameIndex = 0;
//                        isFlapping = false;
//                        flapTimer.cancel();
//                    }
//                }
//            }, 0, 100);
//        }
//    }
//
//    public Rectangle getBirdHitbox() {
//        return birdHitbox;
//    }
//}
