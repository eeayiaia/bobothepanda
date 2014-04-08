package model;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import utilities.Position;

public class LevelModel implements ILevel {
	TiledMap map;
	CharacterModel Bobo;
	Position startPosition = new Position(128, 416);
	
	public LevelModel(String file, String folder) throws SlickException{
		map = new TiledMap(file, folder);
		Bobo = 
	}
	
	
}
