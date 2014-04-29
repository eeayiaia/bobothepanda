package factory;

import org.newdawn.slick.SlickException;

import model.Character;
import model.Collision;
import controller.CharacterController;
import utilities.IMapHandler;
import utilities.MapHandler;
import view.CharacterView;
import view.LevelView;
import model.Level;

public class Factory {

	private Character character;
	private CharacterController characterController;
	private CharacterView characterView;
	private Level level;
	private LevelView levelView;
	private IMapHandler mapHandler;
	private Collision collision;
	
	
	public Factory(String levelName) throws SlickException{
		mapHandler = new MapHandler();
		collision = new Collision(mapHandler.getMapObjectList());
		character = new Character(mapHandler.getCharacterStartPosition(), collision);
		characterView = new CharacterView();
		character.addPropertyChangeListener(characterView);
		characterController = new CharacterController(character);
		
		
		level = new Level(mapHandler.getMapObjectList(), character);
		levelView = new LevelView();
		//levelModel.addPropertyChangeListener(levelView);
	}


	public Character getCharacterModel() {
		return character;
	}


	public CharacterController getCharacterController() {
		return characterController;
	}


	public CharacterView getCharacterView() {
		return characterView;
	}


	public Level getLevelModel() {
		return level;
	}


	public IMapHandler getMapHandler() {
		return mapHandler;
	}

}
