package game.level;

//import java.util.ArrayList;
import controller.CharacterController;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Level {

	private TiledMap map;
	private CharacterController bobo;
	//private ArrayList<Character> character;
	
	public Level(String level) throws SlickException{
		map = new TiledMap("data/levels/TestLevel.tmx","data/img");
		//character = new ArrayList<Character>();
		//bobo = new Character(5,5);
	}
	
	public void addCharacter(CharacterController c){
		bobo = c;
		//character.add(c);
	}
	
	public void render(){
		map.render(0,0);
		/*
		for(Character c: character){
			c.render();
		}
		*/
		bobo.render();
	}
}
