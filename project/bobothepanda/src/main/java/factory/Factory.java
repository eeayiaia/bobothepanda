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
	private IMapHandler mapHandler;
	
	
	public Factory(String levelName) throws SlickException{
		mapHandler = new MapHandler();
		characterModel = new CharacterModel(mapHandler.getCharacterStartPosition());
		characterView = new CharacterView();
		characterModel.addPropertyChangeListener(characterView);
		characterController = new CharacterController(characterModel);
		levelModel = new LevelModel(mapHandler.getMapObjectList(), characterModel);
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


	public IMapHandler getMapHandler() {
		return mapHandler;
	}

}
