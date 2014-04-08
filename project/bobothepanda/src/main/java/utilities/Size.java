package utilities;

public class Size {
	public float height;
	public float width;
	
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
