package utilities;

/*
 * @author Oscar Muhr
 */

import java.util.ArrayList;

import model.IMapObject;
import model.MapObject;
import model.ObjectType;
import model.Position;
import model.Size;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class MapHandler implements IMapHandler {
	
	private TiledMap map;
	private String mapLocation = "data/levels/";
	private String tilesetLocation = "data/img";
	private ArrayList<IMapObject> objectList;
	private Position characterStartPosition;
	
	/*
	 * Test Constructor
	 * Loads the map from tmx file. 
	 */
	public MapHandler() throws SlickException {
		this("TestLevel");
	}
	
	/*
	 * Loads a specified level from a .tmx file
	 * @param levelName the name of the level
	 */
	public MapHandler(String levelName) throws SlickException {
		map = new TiledMap(mapLocation + levelName + ".tmx", tilesetLocation);
		objectList = new ArrayList<IMapObject>();
		createObjectList();
	}
	
	/*
	 * @return A list of objects located on the map
	 */
	public ArrayList<IMapObject> getMapObjectList() {
		return objectList;
	}
	public Position getCharacterStartPosition() {
		return characterStartPosition;
	}
	
	private ObjectType checkObjectType(String type) {
		if(type.equals("Terrain")) {
			return ObjectType.TERRAIN;
		} else if(type.equals("Lethal")) {
			return ObjectType.LETHAL;
		} else if(type.equals("Key")) {
			return ObjectType.KEY;
		} else {
			return null;
		}
	}
	
	private void createObjectList() {
		int objects = map.getObjectCount(0);
		
		for(int i = 0; i < objects; i++) {
			Position position = new Position((float)map.getObjectX(0,i), (float)map.getObjectY(0,i));
			ObjectType type = checkObjectType(map.getObjectType(0,i));
			Size size;
			if(type == null) {
				characterStartPosition = position;
			} else {
				size = new Size((float)map.getObjectWidth(0,i), (float)map.getObjectHeight(0,i));
				objectList.add(new MapObject(position, size, type));
			}
		}
	}
	
	public void renderMap(){
		map.render(0, 0);
	}
	
}