package factory;

import org.newdawn.slick.SlickException;

import Map.IMapHandler;
import Map.MapHandler;
import model.CharacterModel;
import controller.CharacterController;
import view.CharacterView;
import model.LevelModel;

public class Factory {

	private CharacterModel characterModel;
	private CharacterController characterController;
	private CharacterView characterView;
	private LevelModel levelModel;
	private IMapHandler map;
	
	
	public Factory(String levelName) throws SlickException{
		map = new MapHandler();
		characterModel = new CharacterModel(map.getCharacterStartPosition());
		characterView = new CharacterView();
		characterModel.addPropertyChangeListener(characterView);
		characterController = new CharacterController(characterModel);
		levelModel = new LevelModel(map.getMapObjectList(), characterModel);
	}


	public CharacterModel getCharacterModel() {
		return characterModel;
	}


	public CharacterController getCharacterController() {
		return characterController;
	}


	public CharacterView getCharacterView() {
		return characterView;
	}


	public LevelModel getLevelModel() {
		return levelModel;
	}


	public IMapHandler getMap() {
		return map;
	}

}
