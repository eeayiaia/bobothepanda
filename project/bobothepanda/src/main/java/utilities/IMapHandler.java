package utilities;

/*
 * @author Oscar Muhr
 */

import java.util.ArrayList;

import model.IMapObject;
import model.Position;

public interface IMapHandler {
	
	public ArrayList<IMapObject> getMapObjectList();
	
	public Position getCharacterStartPosition();
}
