package utilities;
/**
 * @author Elvira Jonsson
 */

import java.util.ArrayList;
import java.util.List;

import model.Position;
import model.Size;
import model.menu.AbstractMenuItem;
import model.menu.MenuButton;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class MenuMapHandler {
	
	private TiledMap map;
	private final static String MAP_LOCATION = "data/menu/";
	private final static String TILESET_LOCATION = "data/img";
	private List<AbstractMenuItem> menuItemList;
	private Position characterPosition;
	
	public MenuMapHandler(){
		this("gameMenu");
	}
	@edu.umd.cs.findbugs.annotations.SuppressFBWarnings()
	public MenuMapHandler(String menuName){
		try{
			map = new TiledMap(MAP_LOCATION + menuName + ".tmx", TILESET_LOCATION);
		} catch (SlickException e){
			throw new MapHandlerException(e);
		}
		menuItemList = new ArrayList<AbstractMenuItem>();
		createList();
		
	}
	@SuppressWarnings("PMD")
	private void createList(){
		final int objectNbr = map.getObjectCount(0);
		for(int i = 0; i < objectNbr; i++){
			final String type = map.getObjectType(0, i);
			final Position tmpPos = new Position((float)map.getObjectX(0, i), (float)map.getObjectY(0, i));
			final Size tmpSize = new Size(map.getObjectWidth(0, i), map.getObjectHeight(0, i));
			if("Start".equals(type) || "Quit".equals(type)){
				menuItemList.add(new MenuButton(tmpPos, tmpSize, type));
			} else {
				characterPosition = tmpPos;
			}
		}
	}

	public List<AbstractMenuItem> getMenuItemList() {
		return menuItemList;
	}

	public Position getCharacterPosition() {
		return characterPosition;
	}
	public void renderMap(){
		map.render(0, 0);
	}
}
