package game.state;

import game.level.Level;
import main.MainClass;
import model.CharacterModel;
import model.Position;
import controller.CharacterController;
import view.CharacterView;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class LevelState extends BasicGameState {
	private Level level;
	private String firstLevel;
	private CharacterController bobo;
	private float x = 128, y=416;

	public LevelState(String firstLevel){
		this.firstLevel = firstLevel;
	}
	
	public void init(GameContainer container, StateBasedGame game)throws SlickException {
		bobo = new CharacterController(new CharacterModel(new Position(x,y)), new CharacterView());
		level = new Level(firstLevel);
		level.addCharacter(bobo);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)throws SlickException {
		g.scale(MainClass.SCALE, MainClass.SCALE);
		level.render();
	}

	public void update(GameContainer container, StateBasedGame game, int delta)throws SlickException {
		bobo.handleInput(container.getInput(), delta);
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
