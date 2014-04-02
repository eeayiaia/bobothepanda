package jar;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SimpleSlickGame extends BasicGame {

	private Image boboImage;
	
	public SimpleSlickGame(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		//boboImage = new Image("Textures/Bobo-03.png");
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		//g.drawString("Howdy!", 10, 10);
		boboImage.draw(320, 240);
	}

	public static void main(String[] args) {
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("BoBo The Panda"));
			appgc.setDisplayMode(640, 480, false);
			
			appgc.setShowFPS(true);
			
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
}