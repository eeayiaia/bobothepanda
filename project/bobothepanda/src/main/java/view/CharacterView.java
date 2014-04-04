package view;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class CharacterView {

	protected Image spriteRight;
	private Image spriteLeft;
	private Image [] boboRightAnimation = {new Image("/data/Bobo-01.png"), new Image("/data/BoboRightLeg-01.png"),
	                                  new Image("/data/Bobo-01.png"), new Image("/data/BoboLeftLeg-01.png")};
	private Image [] boboLeftAnimation = {new Image("/data/Bobo-01.png").getFlippedCopy(true, false), new Image("/data/BoboRightLeg-01.png").getFlippedCopy(true, false),
            new Image("/data/Bobo-01.png").getFlippedCopy(true, false), new Image("/data/BoboLeftLeg-01.png").getFlippedCopy(true, false)};
	
	private Animation movingRightAnimation;
	private Animation movingLeftAnimation;
	
	public CharacterView() throws SlickException{
		spriteRight = new Image("/data/Bobo-01.png");
		spriteLeft = new Image("/data/Bobo-01.png").getFlippedCopy(true, false);
		movingRightAnimation = new Animation(boboRightAnimation, 125);
		movingLeftAnimation = new Animation(boboLeftAnimation, 125);
	}
	
	public void drawSpriteRight(float x, float y){
		spriteRight.draw(x,y);
	}
	
	public void drawSpriteLeft(float x, float y){
		spriteLeft.draw(x,y);
	}
	
	public void drawMovingRightAnimation(float x, float y){
		movingRightAnimation.draw(x,y);
	}
	
	public void drawMovingLeftAnimation(float x, float y){
		movingLeftAnimation.draw(x,y);
	}
}
