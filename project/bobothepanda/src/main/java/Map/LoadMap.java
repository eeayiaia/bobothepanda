package Map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import position.Position;

public class LoadMap {
	
	/*
	 * The different collisions that can occur.
	 */
	public enum collisionType {
		TERRAIN, LETHAL_HITBOX, KEY, NONE
	}
	
	TiledMap map;
	/*
	 * Loads the map as tmx file. 
	 */
	public LoadMap() throws SlickException {
		map = new TiledMap("data/levels/TestLevel.tmx","data/img");
	}
	
	/*
	 * Determines what kind of collision has occured,
	 * if no collision occured, return NONE
	 */
	public collisionType getCollision(Position pos) {
		
		
		return collisionType.NONE;
	}
}
