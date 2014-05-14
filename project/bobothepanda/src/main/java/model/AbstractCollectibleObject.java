package model;

/**
 * 
 * @author Oscar Muhr
 *
 */

public abstract class AbstractCollectibleObject extends AbstractMapObject implements IVisitable {

	public AbstractCollectibleObject(Position position, Size size) {
		super(position, size);
		// TODO Auto-generated constructor stub
	}

	public void accept(IVisitor visitor) {
		// TODO Auto-generated method stub

	}

}
