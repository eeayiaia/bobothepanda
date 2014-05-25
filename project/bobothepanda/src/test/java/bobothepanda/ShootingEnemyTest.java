package bobothepanda;

import model.Position;
import model.ShootingEnemy;
import model.Size;

import org.junit.Assert;
import org.junit.Before;

public class ShootingEnemyTest extends Assert {
	private ShootingEnemy shootingEnemy;
	private Size size;
	private Position position;
	
	@Before
	public void setUp() {
		size  = new Size(2f, 2f);
		position = new Position(2f, 2f);
		shootingEnemy = new ShootingEnemy(position, size);
	}
	
//	@Test
//	public void 
}
