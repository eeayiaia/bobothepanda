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
import view.ProjectileView;
import view.ShootingEnemyView;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;


public class GameController extends BasicGameState implements PropertyChangeListener{
	
	private	Character character;
	private CharacterController characterController;
	private MapHandler mapHandler;
	private GameContainer container;
	private Level level;
	private int currentLevelNumber;
	private final static float WIDTH = 18;
	private final static float HEIGHT = 30;
	private Key key;
	private List <MovingEnemy> movingEnemies;
	private List <FixedEnemy> fixedEnemies;
	private List <ShootingEnemy> shootingEnemies;
	private final List <Projectile> projectiles;
	private boolean noMoreLevel;
	private StateBasedGame game;
	//private Iterator<Projectile> iterator;
	
	
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
		this.game = game;
		
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)throws SlickException {
		g.scale(MainClass.SCALE, MainClass.SCALE);
		mapHandler.renderMap();
		character.update();
		if(key != null){
			key.update();
		}
		for(final MovingEnemy movingEnemy: movingEnemies){
			movingEnemy.render();
		}
		for(final FixedEnemy fixedEnemy: fixedEnemies){
			fixedEnemy.render();
		}
		for(final ShootingEnemy shootingEnemy: shootingEnemies){
			shootingEnemy.render();
		}
		
		for(final Projectile projectile: projectiles){
			projectile.render();
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta)throws SlickException {
		characterController.handleInput(container.getInput(), delta);
		level.update();
		for(final MovingEnemy movingEnemy: movingEnemies){
			movingEnemy.update(delta);
		}
		for(final ShootingEnemy shootingEnemy: shootingEnemies) {
			shootingEnemy.update(delta);
		}
		
		for(final Projectile projectile: projectiles){
			projectile.update(delta);
		}		
	}

	@Override
	public int getID() {
		return 0;
	}
	
	@Override
	public void keyPressed(int key, char code){
		if(key == Input.KEY_ESCAPE){
			container.exit();
		}
	}
	
	public enum Events{
		LOAD_LEVEL,
		RELOAD_LEVEL,
		ADD_PROJECTILE,
	}
	
	public void propertyChange(PropertyChangeEvent evt){
		
		if("LOAD_LEVEL".equals(evt.getPropertyName())){
			try {
				loadLevel(false);
			} catch (SlickException e) {
				game.enterState(1);
			}
			
		}else if("RELOAD_LEVEL".equals(evt.getPropertyName())){
			try {
				loadLevel(true);
			} catch (SlickException e) {
				game.enterState(1);
			}
			
			
		}else if("ADD_PROJECTILE".equals(evt.getPropertyName())){
			try{
				final Projectile projectile = (Projectile)evt.getNewValue();
				projectile.addPropertyChangeListener(new ProjectileView());
				projectile.addPropertyChangeListener(this);
				projectiles.add(projectile);
				mapHandler.getMapObjectList().add(projectile);
			}catch(SlickException e){
				Log.error(e);
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
		try{
			mapHandler = new MapHandler("newLevel" + currentLevelNumber);
			character = new Character(mapHandler.getCharacterStartPosition(), new Size(WIDTH, HEIGHT));
			character.addPropertyChangeListener(new CharacterView());
			level = new Level(mapHandler.getMapObjectList(), character);
			final LevelView levelView = new LevelView();
			addObjectViews(mapHandler.getMapObjectList());
			level.addPropertyChangeListener(levelView);
			character.addPropertyChangeListener(level);
			character.addPropertyChangeListener(new AudioController());
			characterController = new CharacterController(character);
			level.addPropertyChangeListener(this);
			if(projectiles != null){
				projectiles.clear();
			}
		}catch(MapHandlerException e){
			currentLevelNumber = 0;
			game.enterState(1);			
		}
		
		
	}

	@SuppressFBWarnings
	@SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
	public void addObjectViews(List <AbstractMapObject> abstractMapObjects) throws SlickException{
		movingEnemies = new ArrayList<MovingEnemy>();
		fixedEnemies = new ArrayList<FixedEnemy>();
		shootingEnemies = new ArrayList<ShootingEnemy>();
		for(final AbstractMapObject a: abstractMapObjects){
			if(a.getClass() == Key.class){
				key = (Key) a;
				key.addPropertyChangeListener(new KeyView());
			}else if(a.getClass() == MovingEnemy.class){
				final MovingEnemy movingEnemy = (MovingEnemy) a;
				movingEnemy.addPropertyChangeListener(new MovingEnemyView());
				movingEnemies.add(movingEnemy);
			}else if(a.getClass() == FixedEnemy.class){
				final FixedEnemy fixedEnemy = (FixedEnemy) a;
				fixedEnemy.addPropertyChangeListener(new FixedEnemyView());
				fixedEnemies.add(fixedEnemy);
			}else if(a.getClass() == ShootingEnemy.class) {
				final ShootingEnemy shootingEnemy = (ShootingEnemy) a;
				shootingEnemy.addPropertyChangeListener(new ShootingEnemyView());
				shootingEnemies.add(shootingEnemy);
				shootingEnemy.addPropertyChangeListener(this);
			}
			
		}
	}
	

}
