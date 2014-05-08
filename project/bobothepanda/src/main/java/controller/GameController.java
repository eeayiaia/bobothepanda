package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import main.MainClass;
import model.Character;
import model.Collision;
import model.Level;
import utilities.MapHandler;
import utilities.MapHandlerException;
import view.CharacterView;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameController extends BasicGameState implements PropertyChangeListener{
	
	private	Character character;
	private CharacterController characterController;
	private MapHandler mapHandler;
	private GameContainer container;
	//private LevelView levelView;
	private int currentLevelNumber;
	
	
	/**
	 * Assigns the value of the firstLevel String variable.
	 * @param firstLevel String label for the first level to be initiated.
	 * @throws SlickException 
	 */
	public GameController() throws SlickException{
		super();
		loadLevel(false);
	}
	
	public void init(GameContainer container, StateBasedGame game) throws SlickException{
		this.container = container;
		
		/*
		mapHandler = new MapHandler("level" + currentLevelNumber);
		final Collision collision = new Collision(mapHandler.getMapObjectList());
		
		character = new Character(mapHandler.getCharacterStartPosition(), collision);
		character.addPropertyChangeListener(new CharacterView());
		final Level level = new Level(mapHandler.getMapObjectList(), character);
		character.addPropertyChangeListener(level);
		characterController = new CharacterController(character);
		
		level.addPropertyChangeListener(this);
		*/
		
		
		//levelView = new LevelView();
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
	
	public void propertyChange(PropertyChangeEvent evt) {
		if("loadLevel".equals(evt.getPropertyName())){
			try {
				loadLevel(false);
			} catch (SlickException e) {
				throw new MapHandlerException();
			}
		}
		
		if("reloadLevel".equals(evt.getPropertyName())){
			try {
				loadLevel(true);
			} catch (SlickException e) {
				throw new MapHandlerException();
			}
		}
	}
	
	/**
	 * Loads a new level or reloads the current level
	 * @param loadCurrentLevel true: loads current level, false: loads next level.
	 * @throws SlickException
	 */
	public final void loadLevel(boolean loadCurrentLevel) throws SlickException{
		if(!loadCurrentLevel){
			currentLevelNumber++;
		}
	
		mapHandler = new MapHandler("level" + currentLevelNumber);
		final Collision collision = new Collision(mapHandler.getMapObjectList());
		character = new Character(mapHandler.getCharacterStartPosition(), collision);
		character.addPropertyChangeListener(new CharacterView());
		final Level level = new Level(mapHandler.getMapObjectList(), character);
		character.addPropertyChangeListener(level);
		characterController = new CharacterController(character);
		
		level.addPropertyChangeListener(this);
	}
}
