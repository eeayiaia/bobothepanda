package model.menu;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import model.Position;
import model.Size;

public class AudioButton extends AbstractMenuButton {
	private final PropertyChangeSupport pcs;
	private MenuState state;
	private boolean audioOn;
	
	public AudioButton(Position pos, Size size, String type){
		super(pos,size,type);
		pcs = new PropertyChangeSupport(this);
		state = MenuState.AUIDO_ON_DEFAULT;
		audioOn = true;
	}
	
	public void setAudioOn(boolean isOn){
		audioOn = isOn;
	}
	
	public boolean cursorLegal(int x, int y){
		if(this.getHitbox().contains(new Point(x,y))){
			return true;
		}
		return false;
	}

	@Override
	public void addListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);

	}
	
	public void setDefaultState(){
		if(audioOn){
			state = MenuState.AUIDO_ON_DEFAULT;
		} else {
			state = MenuState.AUDIO_OFF_DEFAULT;
		}
	}

	@Override
	public void mouseHovered(int x, int y) {
		if(cursorLegal(x,y)){
			if(audioOn){
				state = MenuState.AUDIO_ON_HOVER;
			} else {
				state = MenuState.AUDIO_OFF_HOVER;
			}
		} else {
			setDefaultState();
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if(cursorLegal(x,y)){
			if(audioOn){
				state = MenuState.AUDIO_ON_PRESSED;
			} else {
				state = MenuState.AUDIO_OFF_PRESSED;
			}
		} else {
			setDefaultState();
		}

	}

	@Override
	public void mouseReleased(int x, int y) {
		if(cursorLegal(x,y)){
			if(audioOn){
				state = MenuState.AUDIO_ON_RELEASED;
			} else {
				state = MenuState.AUDIO_OFF_RELEASED;
			}
		} else {
			setDefaultState();
		}

	}

	@Override
	public void update() {
		pcs.firePropertyChange(state.toString(), this.getPosition(), this.getType());
	}

}
