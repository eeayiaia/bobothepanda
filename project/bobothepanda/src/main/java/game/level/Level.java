package game.level;

//import java.util.ArrayList;
import game.character.Character;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Level {

	private TiledMap map;
	private Character character;
	//private ArrayList<Character> character;
	
	public Level(String level) throws SlickException{
		map = new TiledMap("data/levels/TestLevel.tmx","data/img");
		//character = new ArrayList<Character>();
		character = new Character(5,5);
	}
	
	public void addCharacter(Character c){
		character = c;
		//character.add(c);
	}
	
	public void render(){
		map.render(0,0);
		/*
		for(Character c: character){
			c.render();
		}
		*/
		character.render();
	}
}
