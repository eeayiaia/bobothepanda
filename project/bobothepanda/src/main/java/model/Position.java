package model;

public class Position {
	private double x;
	private double y;
	
	public Position(double x, double y){
		if(x >= 0 && y >= 0){
			this.x = x;
			this.y = y;
		} else {
			throw new IllegalArgumentException();
		}

	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
}
