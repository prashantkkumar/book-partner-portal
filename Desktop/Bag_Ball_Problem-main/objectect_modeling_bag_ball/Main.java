package objectect_modeling_bag_ball;

public class Main {
	public static void main(String[]args) {
	  Bag bag = new Bag();
	  
	  bag.addB(new Ball(Color.GREEN));
	  bag.addB(new Ball(Color.YELLOW));
	  bag.addB(new Ball(Color.YELLOW));
	  bag.addB(new Ball(Color.YELLOW));
	  bag.addB(new Ball(Color.YELLOW));
	  bag.addB(new Ball(Color.RED)); 
	  bag.addB(new Ball(Color.GREEN));
	  bag.addB(new Ball(Color.GREEN));
	  bag.addB(new Ball(Color.GREEN));
	  bag.addB(new Ball(Color.RED));
	  
	  System.out.println("Green "+bag.getGreen());
	  System.out.println("Red "+bag.getRed());
	  System.out.println("Yellow "+bag.getYellow());
	  	
	}
}
