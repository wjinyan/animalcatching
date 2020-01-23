
public class Basket {
	public EZImage basketpic = EZ.addImage("basket.png", 512, 950);
	int x;
	int y = 950;
	void move() { //move basket left and right
		if (EZInteraction.isKeyDown('d')) {
			if (x < 1024) {
				x += 20;
			}
		}
		else if (EZInteraction.isKeyDown('a')) {
			if (x > 0) {
				x -= 20;
			}
		}
		basketpic.translateTo(x, y);
	}
	
	public double basketX() { //get xcenter for basket
		return basketpic.getXCenter();
	}
	public double basketY() { //get ycenter for basket
		return basketpic.getYCenter();
	}
}
