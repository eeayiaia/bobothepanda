package model;

/**
 * 
 * @author Oscar Muhr
 *
 */

public class Terrain extends AbstractFixedObject {

	public Terrain(Position position, Size size) {
		super(position, size);
		// TODO Auto-generated constructor stub
	}

	public void accept(IVisitor visitor) {
		visitor.visit(this);	
	}
}
