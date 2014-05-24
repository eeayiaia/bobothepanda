package bobothepanda;

import model.Position;
import model.ShootingEnemy;
import model.Size;

import org.junit.Assert;
import org.junit.Before;

public class ShootingEnemyTest extends Assert {
	private ShootingEnemy shootingEnemy;
	private final Size size = new Size(2f, 2f);
	private final Position position = new Position(2f, 2f);
	
	@Before
	public void setUp() {
		shootingEnemy = new ShootingEnemy(position, size);
	}
}
