import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
/* Anh wrote the Fruit and Basket classes and graphics. She used inheritance by	*  
 * extending the Fruit class into 3 subclasses: Apple, Orange, Peach 			*
 * Jin wrote the Invaders and the Main class. In the Main class, she used 2D	*
 * arrays to keep track of the lives the player has. 							*/
 
public class Main {
	//Initialize sounds
	static EZSound theme;
	static EZSound endsound;
	static EZSound drop;

	public static void main(String[] args) {
		EZ.initialize(1000,1000);							//initialize the screen 1000 by 1000
		boolean state = true;								//initialize variable state to true
		state = welcome(state);								//set state to equal the return state from welcome
		if(state = true) {									//if the state of welcome is true
			play();											//start the game
		}
	}

	public static void play() { //start game
		int fruitcount = 0;
		EZImage background = EZ.addImage("background.png", 500, 500);
		//2D array of hearts 
		EZImage[][] hearts = new EZImage[2][3];
		for (int row = 0; row < 2; row++) { 
			for (int c = 0;c<3;c++) {
				hearts[row][c] = EZ.addImage("heart.png", 950 - 65*c, 100+60*row);
			}
		}
		int score = 0;
		int lives = 6;
		drop = EZ.addSound("drop.wav");
		EZText scoretxt = EZ.addText(100, 50, "Score: "+score, new Color(50,50,100),50);
		EZText fruittxt = EZ.addText(100, 100, "Score: "+fruitcount, new Color(50,50,100),50);
		//-------make fruits n basket-----------------//
		Basket b = new Basket();
		Apple a[] = new Apple[3];
		Orange o[] = new Orange[3];
		Peach p[] = new Peach[3];
		Apple perfa = new Apple(fruitcount);
		Orange perfo = new Orange(fruitcount);
		Peach perfp = new Peach(fruitcount);

		//-------make fruits--------------------------//
		for(int i = 0; i < 3; i++) {
			a[i] = new Apple();
			o[i] = new Orange();
			p[i] = new Peach();
		}
		//make the bee
		Invaders bee = new Invaders();
		//Start game while still alive
		while(lives > 0) { 
			b.move(); //move basket
			for(int i = 0; i < 3; i++) { //move fruits 
				a[i].init();
				o[i].init();
				p[i].init();
			}
			for (int i = 0; i < 3; i++) { //check if fruits are in basket
				if (a[i].picture.isPointInElement(b.x, b.y)) {
					a[i].setPos();
					score += a[i].getpoints();
					fruitcount++;
					drop.play();
				}
				else if (o[i].picture.isPointInElement(b.x, b.y)) {
					o[i].setPos();
					score += o[i].getpoints();
					fruitcount++;
					drop.play();
				}
				else if (p[i].picture.isPointInElement(b.x, b.y)) {
					p[i].setPos();
					score+=p[i].getpoints();
					fruitcount++;
					drop.play();
				}
			}

			if (fruitcount >= 5) //golden fruits
				perfa.perfMove(perfa.perfX(),perfa.perfY());
			if (fruitcount >= 10)
				perfo.perfMove(perfo.perfX(),perfo.perfY());
			if (fruitcount >= 15)
				perfp.perfMove(perfp.perfX(),perfp.perfY());

			if(fruitcount >= 3) { //let the bee come
				bee.moveBee(b.basketX(), b.basketY(), bee.beeX(), bee.beeY());
			}

			if(bee.pic.isPointInElement(b.x, b.y)) { //check if bee touches basket
				bee.move2Bee();
				lives--;
				if(lives >= 3) //remove lives 
					EZ.removeEZElement(hearts[1][lives-3]);
				else if (lives <=2)
					EZ.removeEZElement(hearts[0][2 - lives]);
			}
			if (perfa.perfect.isPointInElement(b.x,b.y)) {
				EZ.removeEZElement(perfa.perfect);
				score += 10;
			}
			if (perfo.perfect.isPointInElement(b.x,b.y)) {
				EZ.removeEZElement(perfo.perfect);
				score += 10;
			}
			if (perfp.perfect.isPointInElement(b.x,b.y)) {
				EZ.removeEZElement(perfp.perfect);
				score += 10;
			}
			scoretxt.setMsg("Score: " + score); //update score 
			fruittxt.setMsg("Fruit: " + fruitcount); //update fruits collected
			EZ.refreshScreen();

		}
		if (lives == 0) { //end game if no lives left
			end(fruitcount, score); //bring up the end screen
		}
	}
	//initialize the welcome screen 
	public static boolean welcome(boolean state) {
		theme = EZ.addSound("theme.wav");										
		theme.play();
		Color c = new Color(233, 192, 51);
		EZImage welcome = EZ.addImage("welcome.jpg", 500,500);	
		EZImage bubble = EZ.addImage("bubble.png", 500, 800);
		EZText fruit = EZ.addText(750, 140, "Fruit", c, 50);
		EZText catching = EZ.addText(750, 180, "Catching ", c, 50);
		EZText text = EZ.addText(490, 750, "Greetings Mayor! Today is a good day for fruit catching!\n", Color.WHITE, 35);
		EZText text1 = EZ.addText(500, 800, "Use the a key to move left and d to move right.", Color.WHITE, 35);
		EZText text2 = EZ.addText(500, 850, "Bee sure to keep watch of the bee!", Color.WHITE, 35);
		EZText text3 = EZ.addText(500, 900, "Got it? Then press space to start!", Color.WHITE, 35);
		fruit.setFont("FinkHeavy.ttf"); //font used for title
		catching.setFont("FinkHeavy.ttf");
		text.setFont("FinkHeavy.ttf"); 
		text1.setFont("FinkHeavy.ttf");
		text2.setFont("FinkHeavy.ttf");
		text3.setFont("FinkHeavy.ttf");
		while(state == true) {								
			if(EZInteraction.isKeyDown(KeyEvent.VK_SPACE)) {		
				theme.stop();										
				state = false;											
			}
			else {													
				System.out.println("TRUE");							
				state = true; 
			}
		}
		return true;
	}
	//initialize the end screen
	public static void end(int fruitcount, int score) {
		EZImage end = EZ.addImage("endcard.png", 500,500);				
		EZText next = EZ.addText(500, 820, "Good job Mayor! You collected " + fruitcount + " fruits!", Color.WHITE, 45);
		EZText scoretxt = EZ.addText(500, 900, "You scored " + score + " points!", Color.WHITE, 45);
		next.setFont("FinkHeavy.ttf");
		scoretxt.setFont("FinkHeavy.ttf");
		EZSound endsound = EZ.addSound("5PM.wav");						
		endsound.play();
	}

}