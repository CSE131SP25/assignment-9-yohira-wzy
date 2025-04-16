package assignment9;

import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;

public class Game {
    private Snake snake;
    private Food food;
    private int score; // NEW: Score tracking
    private static final int POINTS_PER_FOOD = 10; // NEW: Points per food
	
    public Game() {
        StdDraw.enableDoubleBuffering();
        snake = new Snake();
        food = new Food();
        score = 0; // NEW: Initialize score
    }
    
    public void play() {
        while (snake.isInbounds()) {
            int dir = getKeypress();
            System.out.println("Keypress: " + dir);
            
            if (dir != -1) {
                snake.changeDirection(dir);
            }
            
            snake.move();
            
            if (snake.eatFood(food)) {
                food = new Food();
                score += POINTS_PER_FOOD; // NEW: Increase score
            }
            
            updateDrawing();
            StdDraw.pause(100);
        }
        
        // Game over sequence
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.text(0.5, 0.5, "GAME OVER - Score: " + score); // NEW: Show score
        StdDraw.show();
        StdDraw.pause(2000);
    }
    
    private int getKeypress() {
        if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
            return 1;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
            return 2;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
            return 3;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            return 4;
        } else {
            return -1;
        }
    }
    
    private void updateDrawing() {
        StdDraw.clear();
        
        // Draw game elements
        snake.draw();
        food.draw();
        
        // NEW: Score display
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.textLeft(0.05, 0.95, "Score: " + score);
        
        StdDraw.show();
    }
    
    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}