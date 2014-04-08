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
	}
	
	/*
	 * @return A list of objects located on the map
	 */
	public ArrayList<IMapObject> getMapObjectList() {
		
		
		
		
		// TODO Auto-generated method stub
		return null;
	}
	public Position getCharacterStartPosition() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
