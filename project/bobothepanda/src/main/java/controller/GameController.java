package controller;

import factory.Factory;
import main.MainClass;
import model.CharacterModel;
import model.LevelModel;
import utilities.MapHandler;


import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameController extends BasicGameState {
	private LevelModel levelModel;
	private	CharacterModel characterModel;
	private String firstLevel;
	private CharacterController characterController;
	private Factory factory;
	private MapHandler mapHandler;
	private float x = 128, y=448;
	
	/**
	 * Assigns the value of the firstLevel String variable.
	 * @param firstLevel String label for the first level to be initiated.
	 */
	public GameController(String firstLevel){
		this.firstLevel = firstLevel;
	}
	
	public void init(GameContainer container, StateBasedGame game)throws SlickException {
		factory  = new Factory(firstLevel);
		characterController	 = factory.getCharacterController(); 
		levelModel = factory.getLevelModel();
		characterModel = factory.getCharacterModel();
		mapHandler = (MapHandler) factory.getMapHandler();
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)throws SlickException {
		g.scale(MainClass.SCALE, MainClass.SCALE);
		mapHandler.renderMap();
	}

	public void update(GameContainer container, StateBasedGame game, int delta)throws SlickException {
		characterController.handleInput(container.getInput(), delta);
		characterModel.update(delta);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void keyPressed(int key, char code){
		if(key == Input.KEY_ESCAPE){
			System.exit(0);
		}
	}
}
