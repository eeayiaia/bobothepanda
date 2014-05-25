package model;



/**
 * 
 * @author Oscar Muhr
 *
 */

public abstract class AbstractCollectibleObject extends AbstractMapObject{

	public AbstractCollectibleObject(Position position, Size size) {
		super(position, size);
		// TODO Auto-generated constructor stub
	}

	abstract public void accept(IVisitor visitor);

}
