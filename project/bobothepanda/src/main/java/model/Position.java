package model;

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
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}
