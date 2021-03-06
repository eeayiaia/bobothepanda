package utilities;

/**
 * @author Oscar Muhr
 */

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.AbstractMapObject;
import model.MovingEnemy;
import model.ShootingEnemy;
import model.Terrain;
import model.Door;
import model.Key;
import model.FixedEnemy;
import model.Position;
import model.Size;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class MapHandler implements IMapHandler {
	
	private TiledMap map;
	private final static String MAP_LOCATION = "data/levels/";
	private final static String TILESET_LOCATION = "data/img";
	private List <AbstractMapObject> objects;
	private Position characterStartPosition;
	
	/**
	 * Test Constructor
	 * Loads the map from tmx file. 
	 */
	public MapHandler() throws MapHandlerException{
		this("level1");
	}
	
	/**
	 * Loads a specified level from a .tmx file
	 * @param levelName the name of the level
	 */
	@edu.umd.cs.findbugs.annotations.SuppressFBWarnings()
	public MapHandler(String levelName) throws MapHandlerException{
		try{
			loadLevel(levelName);
		
		}catch(SlickException e){
			throw new MapHandlerException(e);
		}
	}
	
	/**
	 * Loads the specified level.
	 */
	public final void loadLevel(String levelName) throws SlickException, MapHandlerException{
		final File file = new File(MAP_LOCATION + levelName + ".tmx");
		if(!file.exists()){
			throw new MapHandlerException();
		}
		map = new TiledMap(MAP_LOCATION + levelName + ".tmx", TILESET_LOCATION);
		objects = new ArrayList<AbstractMapObject>();
		createObjectLists();
	}
	
	/**
	 * @return A list of the fixed objects located on the map
	 */
	public List<AbstractMapObject> getMapObjectList() {
		return objects;
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
	@SuppressWarnings({"PMD.AvoidInstantiatingObjectsInLoops", "PMD.DataflowAnomalyAnalysis"})//Behaves as expected
	private void createObjectLists() {
		final int objectAmount = map.getObjectCount(0);
		
		for(int i = 0; i < objectAmount; i++) {
			final Position position = new Position((float)map.getObjectX(0,i), (float)map.getObjectY(0,i));
			final String type = map.getObjectType(0,i);
			final Size size = new Size((float)map.getObjectWidth(0,i), (float)map.getObjectHeight(0,i));
			if("Terrain".equals(type)) {
				objects.add(new Terrain(position, size));
			} else if("Lethal".equals(type)) {
				objects.add(new FixedEnemy(position, size));
			} else if("Door".equals(type)) {
				objects.add(new Door(position, size));
			} else if("Key".equals(type)) {
				objects.add(new Key(position, size));
			} else if("Startpos".equals(type)) {
				characterStartPosition = position;
				characterStartPosition.setY(position.getY()-10);
			} else if("MovingEnemy".equals(type)) {
				objects.add(new MovingEnemy(position, size));
			} else if("ShootingEnemy".equals(type)) {
				objects.add(new ShootingEnemy(position, size));
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
