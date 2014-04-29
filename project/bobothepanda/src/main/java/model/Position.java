package model;

/*
 * @author Oscar Muhr
 */

public class Position {
	private float x;
	private float y;
	
	public Position(float x, float y){
		if(x >= 0 && y >= 0){
			this.x = x;
			this.y = y;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	public void setX(float x) {
		if (x < 0){
			throw new IllegalArgumentException();
		}
		this.x = x;
	}

	public void setY(float y) {
		if(y < 0){
			throw new IllegalArgumentException();
		}
		this.y = y;
	}
	
}
