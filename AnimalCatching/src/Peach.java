
public class Peach extends Fruits{
	
	EZImage perfect;
	int perfx,perfy;
	Peach(){	
		picture = EZ.addImage("peach.png", posx, 0);
	}
	Peach(int fruitcount) { //initialize the perfect fruit
		perfx = rand.nextInt(1000);
		perfy = -50;
		perfect = EZ.addImage("perfpeach.png", perfx, perfy);
	}
	
	public double perfX() { //gives xcenter of perfect fruit
		return perfect.getXCenter();
	}
	public double perfY() {
		return perfect.getYCenter(); //gives ycenter of perfect fruit
	}

	void perfMove(double perfx, double perfy) { //perfect fruits movement
		
		if (perfy ==0) {
			perfx--;
			perfy+=2;
			perfect.translateTo(perfx, perfy);
		}
		else if (perfx <= 1000) {
			perfx--;
			perfy++;
			perfect.translateTo(perfx, perfy);
		}
		if (perfx == 0) {
			perfx = 1000;
			perfy++;
			perfect.translateTo(perfx, perfy);
		}
		else if (perfy == 1000) {
			perfy = 0;
			perfect.translateTo(perfx, perfy);
			
		}
	}
}
