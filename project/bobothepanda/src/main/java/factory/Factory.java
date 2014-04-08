package factory;

import org.newdawn.slick.SlickException;

import Map.IMapHandler;
import Map.MapHandler;
import model.CharacterModel;
import controller.CharacterController;
import view.CharacterView;
import model.LevelModel;

public class Factory {

	private CharacterModel playerModel;
	private CharacterController playerController;
	private CharacterView playerView;
	private LevelModel level;
	private IMapHandler map;
	
	
	public Factory(String levelName) throws SlickException{
		map = new MapHandler();
		playerModel = new CharacterModel(map.getCharacterStartPosition());
		playerView = new CharacterView();
		playerModel.addPropertyChangeListener(playerView);
		playerController = new CharacterController(playerModel);
		level = new LevelModel(map.getMapObjectList(), playerModel);
	}
}
