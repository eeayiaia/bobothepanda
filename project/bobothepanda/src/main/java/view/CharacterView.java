package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


import utilities.Position;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class CharacterView implements PropertyChangeListener{

	protected Image spriteRight;
	private Image spriteLeft;
	private Image [] boboRightAnimation = {new Image("/data/Bobo-01.png"), new Image("/data/BoboRightLeg-01.png"),
	                                  new Image("/data/Bobo-01.png"), new Image("/data/BoboLeftLeg-01.png")};
	private Image [] boboLeftAnimation = {new Image("/data/Bobo-01.png").getFlippedCopy(true, false), new Image("/data/BoboRightLeg-01.png").getFlippedCopy(true, false),
            new Image("/data/Bobo-01.png").getFlippedCopy(true, false), new Image("/data/BoboLeftLeg-01.png").getFlippedCopy(true, false)};
	
	private Animation movingRightAnimation;
	private Animation movingLeftAnimation;
	
	private Position position;
	
	/**
	 * Assigns sprites and animations.
	 * @throws SlickException
	 */
	public CharacterView() throws SlickException{
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

	/**
	 * Renders the character differently depending on what state
	 * the character is in.
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		
		position = (Position)evt.getNewValue();
		
		if(evt.getPropertyName().equals("MOVING_LEFT")){
			drawMovingLeftAnimation(position.getX(), position.getY());
			
		}else if(evt.getPropertyName().equals("MOVING_RIGHT")){
			drawMovingRightAnimation(position.getX(), position.getY());
		
		}else if(evt.getPropertyName().equals("RIGHT")){
			drawSpriteRight(position.getX(), position.getY());
		
		}else if(evt.getPropertyName().equals("LEFT")){
			drawSpriteLeft(position.getX(), position.getY());
		}
	}
}
