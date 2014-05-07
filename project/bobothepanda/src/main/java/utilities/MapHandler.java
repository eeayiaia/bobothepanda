package utilities;

/**
 * @author Oscar Muhr
 */

import java.util.ArrayList;
import java.util.List;

import model.Door;
import model.Key;
import model.AbstractMapObject;
import model.ObjectType;
import model.Position;
import model.Size;
import model.StaticLethalObject;
import model.Terrain;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class MapHandler implements IMapHandler {
	
	private TiledMap map;
	private final static String MAP_LOCATION = "data/levels/";
	private final static String TILESET_LOCATION = "data/img";
	private List<AbstractMapObject> objectList;
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
	@SuppressWarnings("PMD.SystemPrintln")
	public MapHandler(String levelName){
		try{
			loadLevel(levelName);
		
		}catch(SlickException e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Loads the specified level.
	 */
	public final void loadLevel(String levelName) throws SlickException{
		map = new TiledMap(MAP_LOCATION + levelName + ".tmx", TILESET_LOCATION);
		objectList = new ArrayList<AbstractMapObject>();
		createObjectList();
	}
	
	/**
	 * @return A list of objects located on the map
	 */
	public List<AbstractMapObject> getMapObjectList() {
		return objectList;
	}
	
	/**
	 * @return The starting position of the character.
	 */
	public Position getCharacterStartPosition() {
		return characterStartPosition;
	}
	
	/**
	 * Assigns an object type based on input string.
	 * @param type Type of object, String value
	 * @return ObjectType enum.
	 */
	private AbstractMapObject createNewMapObject (String type, Position position, Size size) {
		if("Terrain".equals(type)) {
			return new Terrain(position, size);
		} else if("Lethal".equals(type)) {
			return new StaticLethalObject(position, size);
		} else if("Key".equals(type)) {
			return new Key(position, size);
		} else if("Door".equals(type)){
			return new Door(position, size);
		} else {
			return null;
		}
	}
	
	/**
	 * Creates a list of all the MapObjects.
	 */
	@SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
	private void createObjectList() {
		final int objects = map.getObjectCount(0);
		
		for(int i = 0; i < objects; i++) {
			final Position position = new Position((float)map.getObjectX(0,i), (float)map.getObjectY(0,i));
			String type = map.getObjectType(0,i);
			Size size;
			if(type.equals("Startpos")) {
				characterStartPosition = position;
			} else {
				size = new Size((float)map.getObjectWidth(0,i), (float)map.getObjectHeight(0,i));
				objectList.add(createNewMapObject(type, position, size));
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
