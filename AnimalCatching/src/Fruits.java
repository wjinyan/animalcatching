import java.util.Random;

public class Fruits {
	EZImage picture;
	Random rand = new Random();
	int posx = rand.nextInt(1000);
	int posy;
	int spd = rand.nextInt(9) + 2; 
	
	void init() {  //set the fruit 
		picture.translateTo(posx, posy);
		posy+=spd; //add random speed to falling fruit
		if(posy > 1100) {
			setPos(); 
		}
	}
	 void setPos() { //send back to top
			posx = rand.nextInt(1000);
			posy = 0; 
			spd = rand.nextInt(9) + 2; 
			picture.translateTo(posx, posy);
	}
	 
	 int getpoints() { //give points based on fruit speeds
		 int points = 0;
		 switch (spd){
		 case 2: points = 1;break;
		 case 3: points = 2;break;
		 case 4: points = 3;break;
		 case 5: points = 4;break;
		 case 6: points = 5;break;
		 case 7: points = 6;break;
		 case 8: points = 7;break;
		 case 9: points = 8;break;
		 case 10:points = 9;break;
		 case 11: points = 10;
			}
		 return points;
	 }
}

