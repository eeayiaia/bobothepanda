package model;

import java.util.ArrayList;

import utilities.IllegalMovingStateX;
import utilities.IllegalMovingStateY;
import utilities.ObjectType;
import utilities.Position;

public class LevelModel{
	
	private CharacterModel playerCharacter;
	private ArrayList <IMapObject> objectList;
	private IMapObject door;
	private boolean keyPickedUp;
	
	
	public LevelModel(ArrayList<IMapObject> objectList, CharacterModel playerCharacter){
		this.playerCharacter = playerCharacter;
		this.objectList = objectList;
		for(IMapObject o: objectList){
			if(o.getObjectType().equals(ObjectType.DOOR)){
				door = o;
			}
		}
	}
	public void collision(){
		for(IMapObject o: objectList){
			//Checks if bobo has collided with another object
			if(o.getHitbox().intersects(playerCharacter.getHitbox())){
				//if object is terrain
				if(o.getObjectType().equals(ObjectType.TERRAIN)){	
					playerCharacter.terrainCollision(o.getHitbox());
				} else if (o.getObjectType().equals(ObjectType.KEY)){
					playerCharacter.keyCollision(o.getHitbox());
					objectList.remove(o);
					door.setObjectType(ObjectType.DOOR_OPEN);
				} else if (o.getObjectType().equals(ObjectType.LETHAL)){
					playerCharacter.lethalCollision(o.getHitbox());
				} else if (o.getObjectType().equals(ObjectType.DOOR)){
					playerCharacter.terrainCollision(o.getHitbox());
				} else if(o.getObjectType().equals(ObjectType.DOOR_OPEN)){
					loadNext(this);
				}
			}
		}
	}
	public void loadNext(LevelModel level){
	}
	
}
