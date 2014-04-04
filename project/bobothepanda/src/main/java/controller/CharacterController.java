package controller;

import org.newdawn.slick.Input;

import model.CharacterModel;
import view.CharacterView;

public class CharacterController {
	
	private CharacterModel characterModel;
	private CharacterView characterView;
	private long lastTimeMoved;
	private boolean facingRight = true;
	
	public CharacterController(CharacterModel characterModel, CharacterView characterView){
		
		this.characterModel = characterModel;
		this.characterView = characterView;	
	}
	
	public void handleInput(Input i, int delta){
		handleKeyboardInput(i,delta);
	}
	private void handleKeyboardInput(Input i, int delta){
		if(i.isKeyDown(Input.KEY_LEFT) && characterModel.getPosition().getX() > 0){
			characterModel.moveLeft(delta);
			lastTimeMoved = System.currentTimeMillis();
			facingRight = false;
		}else if(i.isKeyDown(Input.KEY_RIGHT) && characterModel.getPosition().getX() < 608){
			characterModel.moveRight(delta);
			lastTimeMoved = System.currentTimeMillis();
			facingRight = true;
		}
	}
	
	public void render(){
		if(lastTimeMoved + 150 > System.currentTimeMillis() && facingRight){
			characterView.drawMovingRightAnimation(characterModel.getPosition().getX(), characterModel.getPosition().getY());
		}else if(lastTimeMoved + 150 > System.currentTimeMillis() && !facingRight){
			characterView.drawMovingLeftAnimation(characterModel.getPosition().getX(),characterModel.getPosition().getY());
		} else{
			if(facingRight){
				characterView.drawSpriteRight(characterModel.getPosition().getX(), characterModel.getPosition().getY());
			}else{
				characterView.drawSpriteLeft(characterModel.getPosition().getX(),characterModel.getPosition().getY());
			}
		}
	}
}
