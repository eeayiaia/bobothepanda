package controller;

import factory.Factory;
import main.MainClass;
import model.Character;
import model.Level;
import utilities.MapHandler;


import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameController extends BasicGameState {
	private Level level;
	private	Character character;
	private String currentLevel;
	private CharacterController characterController;
	private Factory factory;
	private MapHandler mapHandler;
	private GameContainer container;
	
	
	/**
	 * Assigns the value of the firstLevel String variable.
	 * @param firstLevel String label for the first level to be initiated.
	 */
	public GameController(String currentLevel){
		this.currentLevel = currentLevel;
	}
	
	public void init(GameContainer container, StateBasedGame game)throws SlickException {
		this.container = container; 
		factory  = new Factory(currentLevel);
		characterController	 = factory.getCharacterController(); 
		level = factory.getLevelModel();
		character = factory.getCharacterModel();
		mapHandler = (MapHandler) factory.getMapHandler();
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)throws SlickException {
		g.scale(MainClass.SCALE, MainClass.SCALE);
		mapHandler.renderMap();
		character.update();
	}

	public void update(GameContainer container, StateBasedGame game, int delta)throws SlickException {
		characterController.handleInput(container.getInput(), delta);
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void keyPressed(int key, char code){
		if(key == Input.KEY_ESCAPE){
			container.exit();
		}
	}
}
