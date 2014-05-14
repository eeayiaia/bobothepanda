package utilities;

/*
 * @author Oscar Muhr
 */

import java.util.List;

import org.newdawn.slick.SlickException;

import model.Terrain;
import model.Key;
import model.LethalEnemy;
import model.MovingLethalEnemy;
import model.Position;

public interface IMapHandler {
	
	List<Terrain> getBlockingObjectList();
	
	List<LethalEnemy> getStaticEnemyList();

	List<MovingLethalEnemy> getMovingEnemyList();
	
	Key getKey();
	
	Position getCharacterStartPosition();
	
	void loadLevel(String levelName) throws SlickException;
}
