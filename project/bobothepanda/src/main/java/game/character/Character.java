package game.character;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Character {

	protected float x;
	protected float y;
	private boolean topReached;
	protected Image sprite;
	private Image spriteLeft;
	private Image [] boboRightAnimation = {new Image("/data/Bobo-01.png"), new Image("/data/BoboRightLeg-01.png"),
	                                  new Image("/data/Bobo-01.png"), new Image("/data/BoboLeftLeg-01.png")};
	private Image [] boboLeftAnimation = {new Image("/data/Bobo-01.png").getFlippedCopy(true, false), new Image("/data/BoboRightLeg-01.png").getFlippedCopy(true, false),
            new Image("/data/Bobo-01.png").getFlippedCopy(true, false), new Image("/data/BoboLeftLeg-01.png").getFlippedCopy(true, false)};
	
	private Animation movingRightAnimation;
	private Animation movingLeftAnimation;
	private long lastTimeMoved;
	private boolean facingRight = true;
	
	public Character (float x, float y) throws SlickException{
		this.x = x;
		this.y = y;
		
		sprite = new Image("/data/Bobo-01.png");
		spriteLeft = new Image("/data/Bobo-01.png").getFlippedCopy(true, false);
		movingRightAnimation = new Animation(boboRightAnimation, 125);
		movingLeftAnimation = new Animation(boboLeftAnimation, 125);
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void render(){
		if(lastTimeMoved + 150 > System.currentTimeMillis() && facingRight){
			movingRightAnimation.draw(x, y);
		}else if(lastTimeMoved + 150 > System.currentTimeMillis() && !facingRight){
			movingLeftAnimation.draw(x,y);
		} else{
			if(facingRight){
				sprite.draw(x,y);
			}else{
				spriteLeft.draw(x,y);
			}
		}
	}
	public void moveLeft(int delta){
		facingRight = false;
		x = x - (0.30f*delta);
		lastTimeMoved = System.currentTimeMillis();
	}
	public void moveRight(int delta){
		facingRight = true;
		x = x + (0.30f*delta);	
		lastTimeMoved=System.currentTimeMillis();
	}
	/*public void jump(int delta){
		if (y > 352 && topReached == false) {
			y = y - (0.30f*delta);
			if (y <= 352) {
				topReached = true;
			}
		} else {
			y = y + (0.30f*delta);
			if(y >= 416) {
				topReached = false;
				y=416;
			}
		}

	}*/
}
