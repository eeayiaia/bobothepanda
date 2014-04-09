package game.state;

import factory.Factory;
import game.level.Level;
import main.MainClass;
import model.CharacterModel;
import model.LevelModel;
import controller.CharacterController;
import utilities.Position;
import view.CharacterView;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class LevelState extends BasicGameState {
	private Level level;
	private LevelModel levelModel;
	private	CharacterModel characterModel;
	private String firstLevel;
	private CharacterController characterController;
	private Factory factory;
	private float x = 128, y=448;

	public LevelState(String firstLevel){
		this.firstLevel = firstLevel;
	}
	
	public void init(GameContainer container, StateBasedGame game)throws SlickException {
		factory  = new Factory(firstLevel);
		characterController	 = factory.getCharacterController(); 
		levelModel = factory.getLevelModel();
		characterModel = factory.getCharacterModel();
		characterModel.initBobo();
		//level = new Level(firstLevel);
		//level.addCharacter(characterController);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)throws SlickException {
		g.scale(MainClass.SCALE, MainClass.SCALE);
		level.render();
		//bobo.render();
	}

	public void update(GameContainer container, StateBasedGame game, int delta)throws SlickException {
		characterController.handleInput(container.getInput(), delta);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void keyPressed(int key, char code){
		if(key == Input.KEY_ESCAPE){
			System.exit(0);
		}
	}
}
