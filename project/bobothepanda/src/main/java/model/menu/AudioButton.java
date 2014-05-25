package model.menu;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import model.Position;
import model.Size;

public class AudioButton extends AbstractMenuButton {
	private PropertyChangeSupport pcs;
	
	public AudioButton(Position pos, Size size, String type){
		super(pos,size,type);
		pcs = new PropertyChangeSupport(this);
	}

	@Override
	public void addListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseHovered(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
