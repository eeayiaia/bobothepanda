package utilities;

/*
 * @author Oscar Muhr
 */

import java.util.List;

import org.newdawn.slick.SlickException;

import model.Terrain;
import model.Key;
import model.FixedEnemy;
import model.MovingEnemy;
import model.Position;

public interface IMapHandler {
	
	List<Terrain> getBlockingObjectList();
	
	List<FixedEnemy> getStaticEnemyList();

	List<MovingEnemy> getMovingEnemyList();
	
	Key getKey();
	
	Position getCharacterStartPosition();
	
	void loadLevel(String levelName) throws SlickException;
}
