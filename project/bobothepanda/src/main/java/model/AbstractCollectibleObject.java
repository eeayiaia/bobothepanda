package model;

public abstract class AbstractCollectibleObject extends AbstractFixedObject implements IVisitable {

	public AbstractCollectibleObject(Position position, Size size) {
		super(position, size);
		// TODO Auto-generated constructor stub
	}

	public void accept(IVisitor visitor) {
		// TODO Auto-generated method stub

	}

}
