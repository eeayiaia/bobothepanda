package model.menu;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import model.Position;

public class Menu {
	private final List<AbstractMenuItem> menuItems;
	private final PropertyChangeSupport pcs;
	
	public Menu(List <AbstractMenuItem> menuItems){
		this.menuItems = menuItems;
		pcs = new PropertyChangeSupport(this);
	}
	public List <AbstractMenuItem> getMenuItems(){
		return menuItems;
	}
	@SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
	public void mouseClicked(Position position){
		for(final AbstractMenuItem butt:menuItems){
			if("model.menu.MenuButton".equals(butt.getClass().getName())){
				final Point point = new Point((int)Math.round(position.getX()), (int)Math.round(position.getY()));
				final MenuButton menuButt = (MenuButton) butt;
				if(menuButt.getHitbox().contains(point)){
					pcs.firePropertyChange(menuButt.getName(), true, false);
				}
			}		
		}
	}
	public void addListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
}
