package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import model.AbstractMapObject;
import main.MainClass;
import model.Character;
import model.FixedEnemy;
import model.Key;
import model.Level;
import model.MovingEnemy;
import model.Projectile;
import model.ShootingEnemy;
import model.Size;
import utilities.MapHandler;
import utilities.MapHandlerException;
import view.CharacterView;
import view.FixedEnemyView;
import view.LevelView;
import view.KeyView;
import view.MovingEnemyView;
import view.ShootingEnemyView;

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
	private Key key;
	private List <MovingEnemy> movingEnemies;
	private List <FixedEnemy> fixedEnemies;
	private List <ShootingEnemy> shootingEnemies;
	private List <Projectile> projectiles;

	
	
	
	/**
	 * Assigns the value of the firstLevel String variable.
	 * @param firstLevel String label for the first level to be initiated.
	 * @throws SlickException 
	 */
	public GameController() throws SlickException{
		super();
		loadLevel(false);
		projectiles = new ArrayList<Projectile>();
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
		for(MovingEnemy movingEnemy: movingEnemies){
			movingEnemy.render();
		}
		for(FixedEnemy fixedEnemy: fixedEnemies){
			fixedEnemy.render();
		}
		
		
	}

	public void update(GameContainer container, StateBasedGame game, int delta)throws SlickException {
		characterController.handleInput(container.getInput(), delta);
		level.update(delta);
		for(MovingEnemy movingEnemy: movingEnemies){
			movingEnemy.update(delta);
		}
		for(ShootingEnemy shootingEnemy: shootingEnemies) {
			shootingEnemy.update(delta);
		}
		
	
		
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
		
		if("projectile".equals(evt.getPropertyName())) {
			projectiles.add((Projectile)evt.getNewValue());
			mapHandler.getMapObjectList().add((Projectile)evt.getNewValue());
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
		mapHandler = new MapHandler("newLevel" + currentLevelNumber);
		character = new Character(mapHandler.getCharacterStartPosition(), new Size(WIDTH, HEIGHT));
		character.addPropertyChangeListener(new CharacterView());
		level = new Level(mapHandler.getMapObjectList(), character);
		final LevelView levelView = new LevelView();
		addObjectViews(mapHandler.getMapObjectList());
		level.addPropertyChangeListener(levelView);
		character.addPropertyChangeListener(level);
		audioController = new AudioController();
		character.addPropertyChangeListener(audioController);
		characterController = new CharacterController(character);
		level.addPropertyChangeListener(this);
	}


	public void addObjectViews(List <AbstractMapObject> abstractMapObjects) throws SlickException{
		movingEnemies = new ArrayList<MovingEnemy>();
		fixedEnemies = new ArrayList<FixedEnemy>();
		shootingEnemies = new ArrayList<ShootingEnemy>();
		for(AbstractMapObject a: abstractMapObjects){
			if(a.getClass() == Key.class){
				key = (Key) a;
				key.addPropertyChangeListener(new KeyView());
			}else if(a.getClass() == MovingEnemy.class){
				MovingEnemy movingEnemy = (MovingEnemy) a;
				movingEnemy.addPropertyChangeListener(new MovingEnemyView());
				movingEnemies.add(movingEnemy);
			}else if(a.getClass() == FixedEnemy.class){
				FixedEnemy fixedEnemy = (FixedEnemy) a;
				fixedEnemy.addPropertyChangeListener(new FixedEnemyView());
				fixedEnemies.add(fixedEnemy);
			}else if(a.getClass() == ShootingEnemy.class) {
				ShootingEnemy shootingEnemy = (ShootingEnemy) a;
				shootingEnemy.addPropertyChangeListener(new ShootingEnemyView());
				shootingEnemies.add(shootingEnemy);
				shootingEnemy.addPropertyChangeListener(this);
			}
			
		}
	}
	

}
