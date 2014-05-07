package utilities;

/*
 * @author Oscar Muhr
 */

import java.util.List;

import org.newdawn.slick.SlickException;

import model.MapObject;
import model.Position;

public interface IMapHandler {
	
	List<MapObject> getMapObjectList();
	
	Position getCharacterStartPosition();
	
	void loadLevel(String levelName) throws SlickException;
}
