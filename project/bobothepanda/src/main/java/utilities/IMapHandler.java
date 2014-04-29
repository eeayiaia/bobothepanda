package utilities;

/*
 * @author Oscar Muhr
 */

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import model.IMapObject;
import model.Position;

public interface IMapHandler {
	
	public ArrayList<IMapObject> getMapObjectList();
	
	public Position getCharacterStartPosition();
	
	public void loadLevel(String levelName) throws SlickException;
}
