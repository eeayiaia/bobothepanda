package utilities;

/*
 * @author Oscar Muhr
 */

import java.util.List;

import org.newdawn.slick.SlickException;

import model.Terrain;
import model.Key;
import model.FixedEnemy;
import model.MovingEnemyRENAME;
import model.Position;

public interface IMapHandler {
	
	List<Terrain> getBlockingObjectList();
	
	List<FixedEnemy> getStaticEnemyList();

	List<MovingEnemyRENAME> getMovingEnemyList();
	
	Key getKey();
	
	Position getCharacterStartPosition();
	
	void loadLevel(String levelName) throws SlickException;
}
