package model;

import java.util.ArrayList;

import utilities.IllegalMovingStateX;
import utilities.IllegalMovingStateY;
import utilities.ObjectType;
import utilities.Position;

public class LevelModel{
	
	private CharacterModel playerCharacter;
	private ArrayList <IMapObject> objectList;
	
	
	public LevelModel(ArrayList<IMapObject> objectList, CharacterModel playerCharacter){
		this.playerCharacter = playerCharacter;
		this.objectList = objectList;
	}
	public void collision(){
		for(IMapObject o: objectList){
			//Checks if bobo has collided with another object
			if(o.getHitbox().intersects(playerCharacter.getHitbox())){
				//if object is terrain
				if(o.getObjectType().equals(ObjectType.TERRAIN)){
					//if object if to the left
					if(o.getHitbox().getCenterX() < playerCharacter.getHitbox().getCenterX()){
						playerCharacter.setIllegalMovingStateX(IllegalMovingStateX.LEFT);
						//if object is above bobo
						if(o.getHitbox().getCenterY() < playerCharacter.getHitbox().getCenterY()){
							playerCharacter.setIllegalMovingStateY(IllegalMovingStateY.UP);
						} else {
							playerCharacter.setIllegalMovingStateY(IllegalMovingStateY.DOWN);
						}
					} else {
						playerCharacter.setIllegalMovingStateX(IllegalMovingStateX.RIGHT);
						if(o.getHitbox().getCenterY() < playerCharacter.getHitbox().getCenterY()){
							playerCharacter.setIllegalMovingStateY(IllegalMovingStateY.UP);
						} else {
							playerCharacter.setIllegalMovingStateY(IllegalMovingStateY.DOWN);
						}
					}
				} else if (o.getObjectType().equals(ObjectType.KEY)){
					o.setObjectType(ObjectType.KEY_PICKED_UP);
				} else if (o.getObjectType().equals(ObjectType.LETHAL)){
					playerCharacter.die();
				}
			}
		}
	}
	
	
}
