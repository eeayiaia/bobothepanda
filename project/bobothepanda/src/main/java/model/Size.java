package model;

/*
 * @author Oscar Muhr
 */

public class Size {
	private float width;
	private float height;
	
	public Size(float width, float height) {
		this.width = Math.abs(width);
		this.height = Math.abs(height);
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getArea() {
		return width*height;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		return prime * (prime * result + Float.floatToIntBits(height)) + Float.floatToIntBits(width);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Size other = (Size) obj;
		if (Float.floatToIntBits(height) != Float.floatToIntBits(other.height))
			return false;
		if (Float.floatToIntBits(width) != Float.floatToIntBits(other.width))
			return false;
		return true;
	}
	
}
