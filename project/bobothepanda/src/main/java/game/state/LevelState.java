package game.state;

import game.level.Level;
import game.Game;
import game.character.Character;
import game.controller.PlayerController;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LevelState extends BasicGameState {
	private Level level;
	private String firstLevel;
	private Character bobo;
	private float x = 128, y=416;
	private PlayerController controller;

	public LevelState(String firstLevel){
		this.firstLevel = firstLevel;
	}
	
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		level = new Level(firstLevel);
		bobo = new Character(x, y);
		level.addCharacter(bobo);
		controller = new PlayerController(bobo);
		
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.scale(Game.SCALE, Game.SCALE);
		level.render();
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		controller.handleInput(container.getInput(), delta);
		
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
