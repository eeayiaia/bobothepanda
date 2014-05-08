package controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.MainClass;
import model.Position;
import model.menu.Menu;
import utilities.MenuMapHandler;

public class MenuController extends BasicGameState{
	private Menu menu;
	private MenuMapHandler menuMapHandler;
	private GameContainer container;
	
	public MenuController(){
		super();
		menuMapHandler = new MenuMapHandler();
		menu = new Menu(menuMapHandler.getMenuItemList());
	}
	public void handleInput(Input i, int delta){
		final Position cursorPos = new Position((float)i.getAbsoluteMouseX(),(float)i.getAbsoluteMouseY());
		if(i.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			menu.mouseClicked(cursorPos);
		}
	}
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.container = container;

		
	}
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.scale(MainClass.SCALE, MainClass.SCALE);
		menuMapHandler.renderMap();		
	}
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		handleInput(container.getInput(),delta);
		
	}
	@Override
	public int getID() {
		return 1;
	}

}
