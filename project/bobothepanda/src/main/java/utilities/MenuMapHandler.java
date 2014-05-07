package utilities;

import java.util.List;

import model.IMapObject;
import model.Position;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class MenuMapHandler implements IMapHandler {
	
	private TiledMap map;
	private final static String MAP_LOCATION = "data/menu";
	private final static String TILESET_LOCATION = "data/img";
	private List<IMapObject> objectList;
	private Position characterPosition;
	
	public MenuMapHandler(){
		this("gameMenu");
	}
	@SuppressWarnings("PMD.SystemPrintln")
	public MenuMapHandler(String menuName){
		try{
			loadLevel(menuName);
		} catch (SlickException e){
			System.out.println(e.getMessage());
		}
	}


	public List<IMapObject> getMapObjectList() {
		// TODO Auto-generated method stub
		return null;
	}

	public Position getCharacterStartPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	public final void loadLevel(String levelName) throws SlickException {
		map = new TiledMap(MAP_LOCATION + levelName + ".tmx", TILESET_LOCATION);
		


	}

}
