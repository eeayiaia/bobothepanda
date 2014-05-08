package controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import model.Position;
import model.menu.Menu;
import utilities.MenuMapHandler;

public class MenuController extends BasicGameState{
	private Menu menu;
	
	public MenuController(Menu menu){
		this.menu = menu;
	}
	public void handleInput(Input i, int delta){
		final Position cursorPos = new Position(i.getAbsoluteMouseX(),i.getAbsoluteMouseY());
		if(i.isMousePressed(delta)){
			menu.mouseClicked(cursorPos);
		}
	}
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
