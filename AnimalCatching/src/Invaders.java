import java.util.Random;

public class Invaders {
	Random rand = new Random();
	EZImage pic;
	int beex, beey;
	double directionXbee, directionYbee;
	
	Invaders(){
		beex = 1100;
		beey = 0;
		pic = EZ.addImage("bee.png", beex, beey);
	}
	public double beeX() { //gives xcenter of bee
		return pic.getXCenter();
	}
	public double beeY() {
		return pic.getYCenter(); //gives ycenter of bee
	}
	void moveBee(double basketX, double basketY, double beeX, double beeY) { //move the bee to basket
		Directions beeinitialDirection = getDirections(basketX, basketY, beeX, beeY);
		double directionXbee = beeinitialDirection.xDirection;
		double directionYbee = beeinitialDirection.yDirection;
		beeX = beeX + directionXbee*5; 
		beeY = beeY + directionYbee*5;
		pic.translateTo(beeX, beeY);
		if (beeY >= 950) {
			pic.translateTo(rand.nextInt(1000), 0);
		}
	}
	
	void move2Bee() { //move bee back to the top
		pic.translateTo(rand.nextInt(1000), 0);
	}
	//get directions for the bee
	public static Directions getDirections(double basketX, double basketY, double beeX, double beeY) { 
		double width = basketX - beeX;			
		double height = basketY - beeY;
		double distance = Math.sqrt((width * width) + (height * height));
		double directionXbee = width/distance;
		double directionYbee = height/distance;
		return new Directions(directionXbee, directionYbee);
	}

}

class Directions { //get x and y direction for bee
	public double xDirection;
	public double yDirection;

	public Directions(double x, double y) {
		xDirection = x;
		yDirection = y;
	}
}
