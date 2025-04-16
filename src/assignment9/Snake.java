package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	
	public Snake() {
		segments = new LinkedList<>();
		// Initialize with one segment at the center of the screen
		segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));
		deltaX = 0;
		deltaY = 0;
	}
	
	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
if (segments.isEmpty()) return;
		
		// Store previous positions for body segments
		double prevX = segments.getFirst().getX();
		double prevY = segments.getFirst().getY();
		
		// Move head first
		BodySegment head = segments.getFirst();
		head.setX(head.getX() + deltaX);
		head.setY(head.getY() + deltaY);
		
		// Move each subsequent segment to previous segment's position
		for (int i = 1; i < segments.size(); i++) {
			BodySegment current = segments.get(i);
			double tempX = current.getX();
			double tempY = current.getY();
			current.setX(prevX);
			current.setY(prevY);
			prevX = tempX;
			prevY = tempY;	}
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for (BodySegment segment : segments) {
			segment.draw();
		}
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		if (segments.isEmpty()) return false;
		
		BodySegment head = segments.getFirst();
		double distance = Math.sqrt(Math.pow(head.getX() - f.getX(), 2) + 
								   Math.pow(head.getY() - f.getY(), 2));
		
		if (distance < (SEGMENT_SIZE/2 + Food.FOOD_SIZE/2)) {
			// Add new segment at the end (position doesn't matter as move() will correct it)
			segments.add(new BodySegment(-1, -1, SEGMENT_SIZE));
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
	    if (segments.isEmpty()) return false;
	    
	    BodySegment head = segments.getFirst();
	    double headX = head.getX();
	    double headY = head.getY();
	    double radius = SEGMENT_SIZE/2;
	    
	    // Check if head is outside screen boundaries (accounting for segment size)
	    return (headX - radius > 0) && 
	           (headX + radius < 1) && 
	           (headY - radius > 0) && 
	           (headY + radius < 1);
	}
	// Helper methods needed for BodySegment access
	public double getHeadX() {
		return segments.getFirst().getX();
	}
	
	public double getHeadY() {
		return segments.getFirst().getY();
}}
