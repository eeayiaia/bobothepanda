package model;

/**
 * 
 * @author Oscar Muhr
 *
 */

public interface IVisitable {
	
	void accept(IVisitor visitor);
	
}
