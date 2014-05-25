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
import model.menu.Menu;
import model.menu.MenuState;
import utilities.MapHandlerException;
import utilities.MenuMapHandler;
import view.menu.MenuView;

public class MenuController extends BasicGameState implements PropertyChangeListener{
	private Menu menu;
	private MenuMapHandler menuMapHandler;
	private GameContainer container;
	private StateBasedGame game;

	public void handleInput(Input i, int delta){
		if(i.isKeyPressed(Input.KEY_ENTER)){
			game.enterState(0);
		} else if (i.isKeyPressed(Input.KEY_ESCAPE)){
			container.exit();
		}
		menu.update();
	}
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		try{
			menuMapHandler = new MenuMapHandler();
		} catch(MapHandlerException e){
			game.enterState(0);
		}
		menu = new Menu(menuMapHandler.getMenuItemList());
		final MenuView menuView = new MenuView(menu.getCharacterPosition().getX(), menu.getCharacterPosition().getY());
		menu.addListener(this);
		menu.addListener(menuView);
		menu.addListener(menuView.getStartView());
		menu.addListener(menuView.getQuitView());
		menu.addListener(menuView.getAudioView());
		this.container = container;
		this.game = game;


		
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
		if(button == Input.MOUSE_LEFT_BUTTON){
			menu.mousePressed(x,y);
		}
	}
	@Override
	public void mouseReleased(int button, int x, int y){
		if(button == Input.MOUSE_LEFT_BUTTON){
			menu.mouseReleased(x,y);
		}		
	}
	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY){
		menu.mouseMoved(newX, newY);
	}
	public void propertyChange(PropertyChangeEvent evt) {
		final String source = evt.getPropertyName();
		switch(MenuState.valueOf(source)){
			case QUIT_BUTTON_RELEASED:
				container.exit();
				break;
			case START_BUTTON_RELEASED:
				game.enterState(0);
				break;
			case AUDIO_ON_RELEASED:
				container.setMusicOn(false);
				container.setSoundOn(false);
				menu.setAudioOn(false);
				break;
			case AUDIO_OFF_RELEASED:
				container.setMusicOn(true);
				container.setSoundOn(true);
				menu.setAudioOn(true);
				break;
		default:
			break;
		}
	}

}
