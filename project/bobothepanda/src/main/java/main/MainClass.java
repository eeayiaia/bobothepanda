package main;
    
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import controller.GameController;
import controller.MenuController;

public class MainClass extends StateBasedGame{
	
	/**
	 * Set the window width
	 */
	public static final int WINDOW_WIDTH = 1280;
	
	/**
	 * Set the window height and aspect ratio, with regards to width
	 */
	public static final int WINDOW_HEIGHT = 720;
	
	/**
	 * Enable fullscreen
	 */	
	public static final boolean FULLSCREEN = false;
	
	public static final float SCALE = (float) ((double)WINDOW_WIDTH/1280);
	public static final String GAME_NAME = "Bobo the panda";
	
	public MainClass(){
		super(GAME_NAME);
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		addState(new GameController());
		addState(new MenuController());
		this.enterState(1);
	}
	
	public static void main(String[] args) throws SlickException{
		final AppGameContainer app = new AppGameContainer(new MainClass());
		
		app.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, FULLSCREEN);
		
		app.setTargetFrameRate(60);
		
		app.start();
	}
}
