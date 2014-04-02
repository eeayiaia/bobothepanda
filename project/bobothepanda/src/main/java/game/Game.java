package game;

import game.state.LevelState;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame{
	
	//Set the window width
	public static final int WINDOW_WIDTH = 640;
	
	//Set the window height and aspect ratio, with regards to width
	public static final int WINDOW_HEIGHT = WINDOW_WIDTH;
	
	
	public static final boolean FULLSCREEN = false;
	
	public static final float SCALE = (float) (((double)WINDOW_WIDTH/640));
	public static final String GAME_NAME = "Bobo the panda";
	
	public Game(){
		super(GAME_NAME);
	}
	
	
	public void initStatesList(GameContainer gc) throws SlickException{
		
		addState(new LevelState("level_0"));
		this.enterState(0);
	}
	
	public static void main(String[] args) throws SlickException{
		AppGameContainer app = new AppGameContainer(new Game());
		
		app.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, FULLSCREEN);
		
		app.setTargetFrameRate(60);
		
		app.start();
	}
}
