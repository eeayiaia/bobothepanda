package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import main.MainClass;
import model.Character;
import model.Collision;
import model.Level;
import utilities.MapHandler;
import view.CharacterView;
import view.LevelView;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameController extends BasicGameState implements PropertyChangeListener{
	
	private	Character character;
	//private String currentLevel;
	private CharacterController characterController;
	private MapHandler mapHandler;
	private GameContainer container;
	//private LevelView levelView;
	private int currentLevelNumber;
	
	
	/**
	 * Assigns the value of the firstLevel String variable.
	 * @param firstLevel String label for the first level to be initiated.
	 */
	public GameController(){
		currentLevelNumber = 1;
	}
	
	public void init(GameContainer container, StateBasedGame game)throws SlickException {
		this.container = container;
		mapHandler = new MapHandler("level" + currentLevelNumber);
		Collision collision = new Collision(mapHandler.getMapObjectList());
		
		character = new Character(mapHandler.getCharacterStartPosition(), collision);
		character.addPropertyChangeListener(new CharacterView());
		Level level = new Level(mapHandler.getMapObjectList(), character);
		character.addPropertyChangeListener(level);
		characterController = new CharacterController(character);
		
		level.addPropertyChangeListener(this);
		System.out.println("game.init");
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
		if("loadNewLevel".equals(evt.getPropertyName())){
			System.out.println("in GameController");
			//loadNextLevel();
		}
	}

	public void loadNextLevel() {
		currentLevelNumber++;
		//mapHandler = new MapHandler("level" + currentLevelNumber);
	}
}
