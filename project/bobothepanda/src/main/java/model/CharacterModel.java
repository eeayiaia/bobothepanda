package model;

import java.beans.PropertyChangeSupport;

public class CharacterModel {
	
	private double velocity;
	private Position position;
	private boolean isDead;
	private PropertyChangeSupport pcs;
	
	public CharacterModel(Position position){
		this.position = position;
		isDead = false;
		pcs = new PropertyChangeSupport(this);
	}
	public void moveLeft(double delta){
		position.setX(position.getX() - 0.3f*delta);
	}
	public void moveRight(int delta){
		position.setX(position.getX() + 0.3f*delta);

	}

}
