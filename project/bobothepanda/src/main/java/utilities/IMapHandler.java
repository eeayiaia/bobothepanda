package utilities;

/*
 * @author Oscar Muhr
 */

import java.util.List;

import org.newdawn.slick.SlickException;

import model.AbstractMapObject;
import model.Position;

public interface IMapHandler {
	
	List<AbstractMapObject> getMapObjectList();
	
	Position getCharacterStartPosition();
	
	void loadLevel(String levelName) throws SlickException;
}
