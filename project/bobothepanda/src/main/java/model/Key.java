package model;

/**
 * 
 * @author Oscar Muhr
 *
 */

@SuppressWarnings("PMD")
public class Key extends AbstractCollectibleObject {

	public Key(Position position, Size size) {
		super(position, size);
		// TODO Auto-generated constructor stub
	}

	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
