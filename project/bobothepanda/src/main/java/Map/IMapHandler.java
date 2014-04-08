package Map;

/*
 * @author Oscar Muhr
 */

import java.util.ArrayList;

import model.IMapObject;
import utilities.Position;

public interface IMapHandler {
	
	public ArrayList<IMapObject> getMapObjectList();
	
	public Position getCharacterStartPosition();
}
