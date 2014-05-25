package model;

/*
 * @author Oscar Muhr
 */

public class Size {
	private final float width;
	private final float height;
	
	public Size(float width, float height) {
		this.width = Math.abs(width);
		this.height = Math.abs(height);
	}
	
	public Size(Size size) {
		if(size == null){
			throw new NullPointerException(this.getClass().toString() + "  :Size must be initialized");
		}else{
			this.width = size.getWidth();
			this.height = size.getHeight();
		}
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
		final int result = 1;
		return prime * (prime * result + Float.floatToIntBits(height)) + Float.floatToIntBits(width);
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
		final Size other = (Size) obj;
		if (Float.floatToIntBits(height) != Float.floatToIntBits(other.height)){
			return false;
		}
		if (Float.floatToIntBits(width) != Float.floatToIntBits(other.width)){
			return false;
		}
		return true;
	}
	
}
