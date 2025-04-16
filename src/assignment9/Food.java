package assignment9;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class Food {

	public static final double FOOD_SIZE = 0.02;
	private double x, y;
	
	/**
	 * Creates a new Food at a random location
	 */
	public Food() {
		this.x = Math.random();  // Random x between 0.0 and 1.0 (StdDraw coordinate system)
		this.y = Math.random();  // Random y between 0.0 and 1.0
	}
	
	/**
	 * Draws the Food
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.RED);  // Food is red by default
		StdDraw.filledCircle(x, y, FOOD_SIZE/2);  // Draw as circle with radius FOOD_SIZE/2
	}
	// Additional helpful getter methods
		public double getX() {
			return x;
		}
		
		public double getY() {
			return y;
		}	
}
