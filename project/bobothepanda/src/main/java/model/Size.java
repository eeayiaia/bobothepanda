package model;

/*
 * @author Oscar Muhr
 */

public class Size {
	public float width;
	public float height;
	
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
}
