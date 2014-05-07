package utilities;
/**
 * @author Elvira Jonsson
 */

import java.util.ArrayList;
import java.util.List;

import model.AbstractMenuItem;
import model.IMapObject;
import model.MenuButton;
import model.Position;
import model.Size;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class MenuMapHandler {
	
	private TiledMap map;
	private final static String MAP_LOCATION = "data/menu";
	private final static String TILESET_LOCATION = "data/img";
	private List<AbstractMenuItem> menuItemList;
	private Position characterPosition;
	
	public MenuMapHandler(){
		this("gameMenu");
	}
	@SuppressWarnings("PMD.SystemPrintln")
	public MenuMapHandler(String menuName){
		try{
			map = new TiledMap(MAP_LOCATION + menuName + ".tmx", TILESET_LOCATION);
		} catch (SlickException e){
			System.out.println(e.getMessage());
		}
		menuItemList = new ArrayList<AbstractMenuItem>();
		createList();
		
	}
	
	public void createList(){
		final int objectNbr = map.getObjectCount(0);
		for(int i = 0; i < objectNbr; i++){
			final String type = map.getObjectType(0, i);
			final Position tmpPos = new Position((float)map.getObjectX(0, i), (float)map.getObjectY(0, i));
			final Size tmpSize = new Size(map.getObjectWidth(0, i), map.getObjectHeight(0, i));
			if(type.equals("Start") || type.equals("Quit")){
				menuItemList.add(new MenuButton(tmpPos, tmpSize, type));
			}
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
}
