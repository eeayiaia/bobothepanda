package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Position;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class CharacterView implements PropertyChangeListener{

	protected Image spriteRight;
	private Image spriteLeft;
	private Animation movingRightAnimation;
	private Animation movingLeftAnimation;
		
	/**
	 * Assigns sprites and animations.
	 * @throws SlickException
	 */
	public CharacterView() throws SlickException{
		Image [] boboRightAnimation = {new Image("/data/Bobo-01.png"), new Image("/data/BoboRightLeg-01.png"),
                new Image("/data/Bobo-01.png"), new Image("/data/BoboLeftLeg-01.png")};
		Image [] boboLeftAnimation = {new Image("/data/Bobo-01.png").getFlippedCopy(true, false), new Image("/data/BoboRightLeg-01.png").getFlippedCopy(true, false),
	            new Image("/data/Bobo-01.png").getFlippedCopy(true, false), new Image("/data/BoboLeftLeg-01.png").getFlippedCopy(true, false)};
		
		spriteRight = new Image("/data/Bobo-01.png");
		spriteLeft = new Image("/data/Bobo-01.png").getFlippedCopy(true, false);
		movingRightAnimation = new Animation(boboRightAnimation, 125);
		movingLeftAnimation = new Animation(boboLeftAnimation, 125);
		
	}
	
	/**
	 * Draws the "SpriteRight"-sprite
	 * @param x x-coordinate as a float value
	 * @param y y-coordinate as a float value
	 */
	private void drawSpriteRight(float x, float y){
		spriteRight.draw(x,y);
	}
	
	/**
	 * Draws the "SpriteLeft"-sprite
	 * @param x x-coordinate as a float value
	 * @param y y-coordinate as a float value
	 */
	private void drawSpriteLeft(float x, float y){
		spriteLeft.draw(x,y);
	}
	
	/**
	 * Draws the MovingRightAnimation
	 * @param x x-coordinate as a float value
	 * @param y y-coordinate as a float value
	 */
	private void drawMovingRightAnimation(float x, float y){
		movingRightAnimation.draw(x,y);
	}
	
	/**
	 * Draws the MovingLeftAnimation
	 * @param x x-coordinate as a float value
	 * @param y y-coordinate as a float value
	 */
	private void drawMovingLeftAnimation(float x, float y){
		movingLeftAnimation.draw(x,y);
	}
	public enum Facing{
		MOVING_LEFT,
		MOVING_RIGHT,
		RIGHT,
		LEFT,
		JUMPING
	}
	/**
	 * Renders the character differently depending on what state
	 * the character is in.
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		
		Position pos = (Position) evt.getNewValue();
		float x = pos.getX();
		float y = pos.getY();
		
		switch (Facing.valueOf(evt.getPropertyName())) {
		case MOVING_LEFT:
			drawMovingLeftAnimation(x,y);
			break;
		
		case MOVING_RIGHT:
			drawMovingRightAnimation(x,y);
			break;
		
		case RIGHT: 
			drawSpriteRight(x,y);
			break;
			
		case LEFT:
			drawSpriteLeft(x, y);
			break;
			
		case JUMPING:
			break;
			
		default:
			break;
		}
	}
}
