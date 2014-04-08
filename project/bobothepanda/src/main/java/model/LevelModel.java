package model;

import java.util.ArrayList;

import utilities.IllegalMovingStateX;
import utilities.ObjectType;
import utilities.Position;

public class LevelModel implements ILevel {
	
	private CharacterModel playerCharacter;
	private ArrayList <IMapObject> objectList;
	
	
	public LevelModel(ArrayList<IMapObject> objectList, CharacterModel playerCharacter){
		this.playerCharacter = playerCharacter;
		this.objectList = objectList;
	}
	public void collision(){
		for(IMapObject o: objectList){
			if(o.getHitbox().intersects(playerCharacter.getHitbox())){
				if(o.getObjectType().equals(ObjectType.TERRAIN)){
					if(o.getHitbox().getCenterX() < playerCharacter.getHitbox().getCenterX()){
						playerCharacter.setIllegalMovingState(IllegalMovingStateX.DOWN_LEFT);
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
