package model;

/*
 * @author Oscar Muhr
 */

public class Size {
	public float width;
	public float height;
	
	public Size(float width, float height) {
		this.width = width;
		this.height = height;
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
