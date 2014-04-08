package main;

import game.state.LevelState; // will be deleted later
import factory.Factory;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainClass extends StateBasedGame{
	
	/**
	 * Set the window width
	 */
	public static final int WINDOW_WIDTH = 640;
	
	/**
	 * Set the window height and aspect ratio, with regards to width
	 */
	public static final int WINDOW_HEIGHT = WINDOW_WIDTH;
	
	/**
	 * Enable fullscreen
	 */
	public static final boolean FULLSCREEN = false;
	
	public static final float SCALE = (float) (((double)WINDOW_WIDTH/640));
	public static final String GAME_NAME = "Bobo the panda";
	
	public MainClass(){
		super(GAME_NAME);
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		
		//new Factory("level_0"); // this will start the game
		addState(new LevelState("level_0"));
		this.enterState(0);
	}
	
	public static void main(String[] args) throws SlickException{
		AppGameContainer app = new AppGameContainer(new MainClass());
		
		app.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, FULLSCREEN);
		
		app.setTargetFrameRate(60);
		
		app.start();
	}
}
