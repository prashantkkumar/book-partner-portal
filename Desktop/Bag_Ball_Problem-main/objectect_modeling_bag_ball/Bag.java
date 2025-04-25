package objectect_modeling_bag_ball;

import java.util.ArrayList;
import java.util.List;

public class Bag {
    private final List<Ball> balls = new ArrayList<>();
    private final int CAPACITY = 12;

    private int currCapacity = 0;
    private int rCount = 0;
    private int gCount = 0;
    private int yCount = 0;

    public boolean addB(Ball ball) {
        if (currCapacity >= CAPACITY) {
            System.out.println("Bag is full");
            return false;
        }

        Color color = ball.getColor();

        if (color.equals(Color.RED)) {
            if (rCount + 1 > gCount) {
                System.out.println("red count cannot be greater than green");
                return false;
            } else {
                rCount++;
                balls.add(ball);
                currCapacity++;
                return true;
            }
        } else if (color.equals(Color.GREEN)) {
            gCount++;
            balls.add(ball);
            currCapacity++;
            return true;
        } else if (color.equals(Color.YELLOW)) {
            if (yCount + 1 > CAPACITY * 0.4) {
                System.out.println(" yellow cannot exceeds 40% of capacity");
                return false;
            } else {
                yCount++;
                balls.add(ball);
                currCapacity++;
                return true;
            }
        } else {
          
            balls.add(ball);
            currCapacity++;
            return true;
        }
        
    }
    public int getRed() {
    	return rCount;
    }
    public int getGreen() {
    	return gCount;
    }
    public int getYellow() {
    	return yCount;
      
    }
    
}
