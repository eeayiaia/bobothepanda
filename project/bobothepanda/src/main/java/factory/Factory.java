package factory;

import Map.IMapHandler;
import Map.MapHandler;
import model.CharacterModel;
import model.LevelModel;

public class Factory {

	private CharacterModel playerCharacter;
	private LevelModel level;
	private IMapHandler map;
	
	
	public Factory(String levelName){
		map = new MapHandler();
		playerCharacter = new CharacterModel(map.getCharacterStartPosition());
		level = new LevelModel(map.getMapObjectList(), playerCharacter);
	}
}
