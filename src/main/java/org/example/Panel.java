package org.example;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel implements KeyListener, ActionListener {
    private GraphicsHandler graphicsHandler = new GraphicsHandler();
    final int WIDTH = 800, HEIGHT = 600;
    final int wallVELOCITY = 8;
    final int wallWIDTH = 75;
    int birdHeight = HEIGHT/2;
    int birdVelocity = 0, birdAcceleration = 8, birdI = 1;
    int [] wallX = {WIDTH, WIDTH + WIDTH / 2, };
    int [] gapPlace ={(int)(Math.random() * (HEIGHT - 150)), (int)(Math.random() * (HEIGHT - 100))};
    boolean gameOver = false;
    Timer time =  new Timer(40, this);
    int score = 0;
    boolean[] wallPassed = {false, false};
    int birdPosition = birdHeight + birdVelocity;


    public Panel(){
        setSize(WIDTH,HEIGHT);
        setFocusable(true);
        addKeyListener(this);

        setBackground(Color.BLACK);
    }

    public void actionPerformed(ActionEvent e){
        birdAcceleration += birdI;
        birdVelocity += birdAcceleration;
        wallX[0] -= wallVELOCITY;
        wallX[1] -= wallVELOCITY;
        birdPosition = birdHeight + birdVelocity;

        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if(!gameOver){

            for(int i = 0; i < 2; i++){
                graphicsHandler.drawWall(g, wallX[i], 0, wallWIDTH, HEIGHT, gapPlace[i]);
            }
            logic();
            graphicsHandler.drawBird(g,250, birdPosition, 50, 60);
            g.setColor(Color.YELLOW);
            g.drawString("SCORE:" + score, WIDTH - 120, 10);
        } else {
            g.setColor(Color.YELLOW);
            g.drawString("SCORE:" + score, WIDTH - 400, HEIGHT - 300);
        }

    }



    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == e.VK_SPACE){
            graphicsHandler.triggerFlap();
            birdAcceleration = -12;
        }

        if(code == e.VK_SPACE){
            time.start();
        }

        if(code == e.VK_R){
            time.stop();
            birdHeight = HEIGHT/2;
            birdVelocity = 0;
            birdAcceleration = 8;
            wallX[0] = WIDTH;
            wallX[1] = WIDTH + WIDTH / 2;
            gapPlace[0] = (int)(Math.random() * (HEIGHT - 150));
            gapPlace[1] = (int)(Math.random() * (HEIGHT - 100));
            gameOver = false;
            score = 0;
            birdPosition = birdHeight + birdVelocity;
            repaint();
        }
    }

    private void logic(){
        Rectangle birdHitbox = graphicsHandler.getBirdHitbox();
        for(int i = 0; i < 2; i++){
            Rectangle wallRectTop = new Rectangle(wallX[i], 0, wallWIDTH, gapPlace[i]);
            Rectangle wallRectBottom = new Rectangle(wallX[i], gapPlace[i] + 150, wallWIDTH, HEIGHT);

            //Collision
            if(birdHitbox.intersects(wallRectTop) || birdHitbox.intersects(wallRectBottom)){
                gameOver = true;
            }
        }
        //Game window border collision
        if(birdHitbox.y <= -30 || birdHitbox.y + birdHitbox.height >= HEIGHT + 30){
            gameOver = true;
        }

        // Check if the bird passed a wall
        for (int i = 0; i < 2; i++) {
            if (250 > (wallX[i] + wallWIDTH) && !wallPassed[i]) {
                score++;
                wallPassed[i] = true;
            }
        }

        // Reset wall positions if they go off-screen
        for (int i = 0; i < 2; i++) {
            if (wallX[i] + wallWIDTH <= 0) {
                wallX[i] = WIDTH;
                gapPlace[i] = (int) (Math.random() * (HEIGHT - 150));
                wallPassed[i] = false;
            }
        }

    }

    public void keyReleased(KeyEvent e) {

    }
}
