package controller;

import org.newdawn.slick.Input;

import model.CharacterModel;
import view.CharacterView;

public class CharacterController {
	
	private CharacterModel characterModel;
	private long lastTimeMoved;
	private boolean facingRight = true;
	
	public CharacterController(CharacterModel characterModel){
		
		this.characterModel = characterModel;
	}
	
	public void handleInput(Input i, int delta){
		handleKeyboardInput(i,delta);
	}
	private void handleKeyboardInput(Input i, int delta){
		if(i.isKeyDown(Input.KEY_LEFT) && characterModel.getPosition().getX() > 0){
			characterModel.moveLeft(delta);

		}else if(i.isKeyDown(Input.KEY_RIGHT) && characterModel.getPosition().getX() < 608){
			characterModel.moveRight(delta);
			
		}else if(i.isKeyDown(Input.KEY_SPACE)/* && characterModel.onGround()*/){
			characterModel.jump(delta);
			
		}
	}
	
}
