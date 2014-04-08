package Map;

/*
 * @author Oscar Muhr
 */

import java.util.ArrayList;

import model.IMapObject;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import utilities.Position;

public class MapHandler implements IMapHandler {
	
	private TiledMap map;
	private String mapLocation = "data/levels/";
	private String tilesetLocation = "data/img";
	private ArrayList<IMapObject> objectList;
	private Position CharacterStartPosition;
	
	/*
	 * Test Constructor
	 * Loads the map as tmx file. 
	 */
	public MapHandler() throws SlickException {
		map = new TiledMap(mapLocation + "TestLevel.tmx","data/img");
	}
	
	/*
	 * Loads a specified level from a .tmx file
	 * @param levelName the name of the level 
	 */
	public MapHandler(String levelName) throws SlickException {
		map = new TiledMap(mapLocation + levelName + ".tmx", tilesetLocation);
		objectList = new ArrayList<IMapObject>();
	}
	
	/*
	 * @return A list of objects located on the map
	 */
	public ArrayList<IMapObject> getMapObjectList() {
		int objects = map.getObjectCount(0);
		
		for(int i = 0; i < objects; i++) {
			objectList.add(createObject(i));
		}
		
		
		
		// TODO Auto-generated method stub
		return null;
	}
	public Position getCharacterStartPosition() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private IMapObject createObject(int objectNumber) {
		
		
		return null;
	}
	
	
}
