package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Position;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class CharacterView implements PropertyChangeListener{

	private final Image spriteRight;
	private final Image spriteLeft;
	private final Animation movingRightAnimation;
	private final Animation movingLeftAnimation;
	private final static String SPRITE_RIGHT_IMAGE = "/data/Bobo-01.png";
		
	/**
	 * Assigns sprites and animations.
	 * @throws SlickException
	 */
	public CharacterView() throws SlickException{
		final Image [] boboRightAnimation = {new Image(SPRITE_RIGHT_IMAGE), new Image("/data/BoboRightLeg-01.png"),
                new Image(SPRITE_RIGHT_IMAGE), new Image("/data/BoboLeftLeg-01.png")};
		final Image [] boboLeftAnimation = {new Image(SPRITE_RIGHT_IMAGE).getFlippedCopy(true, false), new Image("/data/BoboRightLeg-01.png").getFlippedCopy(true, false),
	            new Image(SPRITE_RIGHT_IMAGE).getFlippedCopy(true, false), new Image("/data/BoboLeftLeg-01.png").getFlippedCopy(true, false)};
		
		spriteRight = new Image(SPRITE_RIGHT_IMAGE);
		spriteLeft = new Image(SPRITE_RIGHT_IMAGE).getFlippedCopy(true, false);
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
	@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
	public void propertyChange(PropertyChangeEvent evt) {
		
		final Position pos = (Position) evt.getNewValue();
		
		if(pos != null){

			final float x = pos.getX();
			final float y = pos.getY();
		
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
}
