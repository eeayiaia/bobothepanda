package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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

public class MenuController extends BasicGameState implements PropertyChangeListener{
	private final Menu menu;
	private final MenuMapHandler menuMapHandler;
	
	public MenuController(){
		super();
		menuMapHandler = new MenuMapHandler();
		menu = new Menu(menuMapHandler.getMenuItemList());
		menu.addListener(this);
	}
	public void handleInput(Input i, int delta){
		final Position cursorPos = new Position((float)i.getAbsoluteMouseX(),(float)i.getAbsoluteMouseY());
		if(i.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			menu.mouseClicked(cursorPos);
		}
		menu.update(cursorPos);
	}
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		menu.setCharacterPosition(menuMapHandler.getCharacterPosition());
		menu.startMenu();
	//	final GameContainer conta = container;
		//TODO what should this method do?

		
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
	public void propertyChange(PropertyChangeEvent evt) {
		//TODO
		
	}

}
