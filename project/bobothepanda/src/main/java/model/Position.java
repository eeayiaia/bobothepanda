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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		return true;
	}
	
}
