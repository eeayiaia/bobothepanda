package factory;

import org.newdawn.slick.SlickException;

import model.CharacterModel;
import model.Collision;
import controller.CharacterController;
import utilities.IMapHandler;
import utilities.MapHandler;
import view.CharacterView;
import view.LevelView;
import model.LevelModel;

public class Factory {

	private CharacterModel characterModel;
	private CharacterController characterController;
	private CharacterView characterView;
	private LevelModel levelModel;
	private LevelView levelView;
	private IMapHandler mapHandler;
	private Collision collision;
	
	
	public Factory(String levelName) throws SlickException{
		mapHandler = new MapHandler();
		collision = new Collision(mapHandler.getMapObjectList());
		characterModel = new CharacterModel(mapHandler.getCharacterStartPosition(), collision);
		characterView = new CharacterView();
		characterModel.addPropertyChangeListener(characterView);
		characterController = new CharacterController(characterModel);
		
		
		levelModel = new LevelModel(mapHandler.getMapObjectList(), characterModel);
		levelView = new LevelView();
		//levelModel.addPropertyChangeListener(levelView);
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
