package Map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import utilities.Position;

public class MapHandler {
	
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
	public MapHandler() throws SlickException {
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
