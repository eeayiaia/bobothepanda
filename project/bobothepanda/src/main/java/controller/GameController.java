package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import model.AbstractMapObject;
import main.MainClass;
import model.Character;
import model.Key;
import model.Level;
import model.MovingEnemy;
import model.Size;
import utilities.MapHandler;
import utilities.MapHandlerException;
import view.CharacterView;
import view.LevelView;
import view.KeyView;
import view.MovingEnemyView;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameController extends BasicGameState implements PropertyChangeListener{
	
	private	Character character;
	private CharacterController characterController;
	private MapHandler mapHandler;
	private GameContainer container;
	private Level level;
	private int currentLevelNumber;
	private final float WIDTH = 18;
	private final float HEIGHT = 30;
	private AudioController audioController;
	private KeyView keyView;
	private Key key;
	private MovingEnemy movingEnemy;
	private MovingEnemyView movingEnemyView;
	
	
	
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
		level.render();
		if(key != null){
			key.update();
		}
		movingEnemy.render();
		
	}

	public void update(GameContainer container, StateBasedGame game, int delta)throws SlickException {
		characterController.handleInput(container.getInput(), delta);
		level.update(delta);
		movingEnemy.update(delta);
	
		
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
	@edu.umd.cs.findbugs.annotations.SuppressFBWarnings()
	public void propertyChange(PropertyChangeEvent evt) {
		if("loadLevel".equals(evt.getPropertyName())){
			try {
				loadLevel(false);
			} catch (SlickException e) {
				throw new MapHandlerException(e);
			}
		}
		
		if("reloadLevel".equals(evt.getPropertyName())){
			try {
				loadLevel(true);
			} catch (SlickException e) {
				throw new MapHandlerException(e);
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
		character = new Character(mapHandler.getCharacterStartPosition(), new Size(WIDTH, HEIGHT));
		character.addPropertyChangeListener(new CharacterView());
		level = new Level(mapHandler.getMapObjectList(), character);
		final LevelView levelView = new LevelView();
		keyView = new KeyView();
		movingEnemyView = new MovingEnemyView();
		addObjectViews(mapHandler.getMapObjectList());
		level.addPropertyChangeListener(levelView);
		character.addPropertyChangeListener(level);
		audioController = new AudioController();
		character.addPropertyChangeListener(audioController);
		characterController = new CharacterController(character);
		level.addPropertyChangeListener(this);
	}


	public void addObjectViews(List <AbstractMapObject> abstractMapObjects){
		for(AbstractMapObject a: abstractMapObjects){
			if(a.getClass() == Key.class){
				key = (Key) a;
				key.addPropertyChangeListener(keyView);
			}
			if(a.getClass() == MovingEnemy.class){
				movingEnemy = (MovingEnemy) a;
				movingEnemy.addPropertyChangeListener(movingEnemyView);
			}
		}
	}
}
