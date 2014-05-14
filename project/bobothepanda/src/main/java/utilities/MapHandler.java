package utilities;

/**
 * @author Oscar Muhr
 */

import java.util.ArrayList;
import java.util.List;

import model.Terrain;
import model.Door;
import model.IMapObject;
import model.Key;
import model.AbstractFixedObject;
import model.FixedEnemy;
import model.MovingEnemy;
import model.Position;
import model.Size;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class MapHandler implements IMapHandler {
	
	private TiledMap map;
	private final static String MAP_LOCATION = "data/levels/";
	private final static String TILESET_LOCATION = "data/img";
	private List <Terrain> blockingObjects;
	private List <FixedEnemy> staticEnemies;
	private List <MovingEnemy> movingEnemies;
	private Key key;
	private Position characterStartPosition;
	
	/**
	 * Test Constructor
	 * Loads the map from tmx file. 
	 */
	public MapHandler() {
		this("level1");
	}
	
	/**
	 * Loads a specified level from a .tmx file
	 * @param levelName the name of the level
	 */
	@edu.umd.cs.findbugs.annotations.SuppressFBWarnings()
	public MapHandler(String levelName){
		try{
			loadLevel(levelName);
		
		}catch(SlickException e){
			throw new MapHandlerException(e);
		}
	}
	
	/**
	 * Loads the specified level.
	 */
	public final void loadLevel(String levelName) throws SlickException{
		map = new TiledMap(MAP_LOCATION + levelName + ".tmx", TILESET_LOCATION);
		blockingObjects = new ArrayList<Terrain>();
		createObjectLists();
	}
	
	/**
	 * @return A list of the blocking objects located on the map
	 */
	public List<Terrain> getBlockingObjectList() {
		return blockingObjects;
	}
	
	/**
	 * @return A list of the static lethal enemies on the map
	 */
	public List<FixedEnemy> getStaticEnemyList() {
		return staticEnemies;
	}
	
	/**
	 * 
	 * @return A list of the moving enemies on the map
	 */
	public List<MovingEnemy> getMovingEnemyList() {
		return movingEnemies;
	}
	
	/**
	 * 
	 * @return The key on the map
	 */
	public Key getKey() {
		return key;
	}
	
	
	/**
	 * @return The starting position of the character.
	 */
	public Position getCharacterStartPosition() {
		return characterStartPosition;
	}
	
	/**
	 * Creates a list of all the MapObjects.
	 */
	@SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
	private void createObjectLists() {
		final int objects = map.getObjectCount(0);
		
		for(int i = 0; i < objects; i++) {
			final Position position = new Position((float)map.getObjectX(0,i), (float)map.getObjectY(0,i));
			final String type = map.getObjectType(0,i);
			Size size;
			if("Startpos".equals(type)) {
				characterStartPosition = position;
			} else {
				size = new Size((float)map.getObjectWidth(0,i), (float)map.getObjectHeight(0,i));
				if("Terrain".equals(type)) {
					blockingObjects.add(new Terrain(position, size));
				} else if("Lethal".equals(type)) {
					staticEnemies.add(new FixedEnemy(position, size));
				} else if("Door".equals(type)) {
					blockingObjects.add(new Door(position, size));
				} else if("Key".equals(type)) {
					key = new Key(position, size);
				}
			}
		}
	}
	
	/**
	 * Renders the current map.
	 */
	public void renderMap(){
		map.render(0, 0);
	}
}
