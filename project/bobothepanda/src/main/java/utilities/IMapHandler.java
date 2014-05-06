package utilities;

/*
 * @author Oscar Muhr
 */

import java.util.List;

import org.newdawn.slick.SlickException;

import model.IMapObject;
import model.Position;

public interface IMapHandler {
	
	List<IMapObject> getMapObjectList();
	
	Position getCharacterStartPosition();
	
	void loadLevel(String levelName) throws SlickException;
}
