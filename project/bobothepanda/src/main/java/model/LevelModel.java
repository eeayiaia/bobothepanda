package model;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import utilities.Position;

public class LevelModel implements ILevel {
	TiledMap map;
	CharacterModel playerCharacter;
	
	public LevelModel(ArrayList<IMapObject> objectList, CharacterModel playerCharacter) throws SlickException{
		this.playerCharacter = playerCharacter;
	}
	
	
}
