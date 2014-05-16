package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.MainClass;
import model.Position;
import model.menu.Menu;
import model.menu.MenuState;
import utilities.MenuMapHandler;
import view.MenuView;

public class MenuController extends BasicGameState implements PropertyChangeListener{
	private Menu menu;
	private MenuMapHandler menuMapHandler;
	private GameContainer container;
	private StateBasedGame game;
	
	public MenuController(){
		super();

	}
	public void handleInput(Input i, int delta){
		final Position cursorPos = new Position((float)i.getAbsoluteMouseX(),(float)i.getAbsoluteMouseY());
		if(i.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			menu.mouseClicked(cursorPos);
		}
		menu.setMenuState(cursorPos);
	}
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		menuMapHandler = new MenuMapHandler();
		menu = new Menu(menuMapHandler.getMenuItemList());
		menu.addListener(this);
		menu.addListener(new MenuView());
		menu.startMenu();
		this.container = container;
		this.game = game;
		
	//	final GameContainer conta = container;
		//TODO what should this method do?

		
	}
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.scale(MainClass.SCALE, MainClass.SCALE);
		menuMapHandler.renderMap();
		menu.update();
		
		
	}
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		handleInput(container.getInput(),delta);
		
	}
	@Override
	public int getID() {
		return 1;
	}
	@Override
	public void mousePressed(int button, int x, int y){
		
	}
	@Override
	public void mouseReleased(int button, int x, int y){
		if(button == Input.MOUSE_LEFT_BUTTON){
			menu.mouseReleased(new Position(x,y));
		}		
	}
	public void propertyChange(PropertyChangeEvent evt) {
		String source = evt.getPropertyName();
		switch(MenuState.valueOf(source)){
			case QUIT_BUTTON_CLICKED:
				container.exit();
				break;
			case START_BUTTON_CLICKED:
				game.enterState(0);
				break;
		default:
			break;
		}

	}

}
