package model;

/*
 * @author Oscar Muhr
 */

public class Position {

	private float x;
	private float y;
	
	public Position(float x, float y) {
		setX(x);
		setY(y);
	}
	
	public Position(Position position){
		if(position == null){
			throw new NullPointerException("Position needs to be initialized");
		}else{
			setX(position.getX());
			setY(position.getY());
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
			this.x = 0f;
		} else {
			this.x = x;

		}
	}

	public void setY(float y) {
		if(y < 0){
			this.y = 0f;
		}else {
			this.y = y;

		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		final int result = 1;
		return prime * (prime * result + Float.floatToIntBits(x)) + Float.floatToIntBits(y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		final Position other = (Position) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x)){
			return false;
		}
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y)){
			return false;
		}
		return true;
	}
	
}
