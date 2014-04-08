package model;

import java.util.ArrayList;
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
			if(o.getPosition().equals(playerCharacter.getPosition())){
				
			}
		}
	}
	
	
}
