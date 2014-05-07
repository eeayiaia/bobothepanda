//package bobothepanda;
//
//import static org.junit.Assert.*;
//
//import java.awt.Rectangle;
//
//import model.MapObject;
//import model.ObjectType;
//import model.Position;
//import model.Size;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//public class MapObjectTest extends Assert{
//	private MapObject mapObject;
//
//	@Before
//	public void setUp() throws Exception {
//		mapObject = new MapObject(new Position(1f,1f), new Size(3f, 3f), ObjectType.TERRAIN);
//	}
//
//	@Test
//	public void testGetPosition() {
//		assertTrue(mapObject.getPosition().equals(new Position(1f,1f)));
//	}
//
//	@Test
//	public void testGetSize() {
//		assertTrue(mapObject.getSize().equals(new Size (3f, 3f)));
//	}
//
//	@Test
//	public void testGetObjectType() {
//		assertSame(mapObject.getObjectType(),ObjectType.TERRAIN);
//	}
//
//	@Test
//	public void testGetHitbox() {
//		assertTrue(mapObject.getHitbox().equals(new Rectangle(1,1,3,3)));
//	}
//
//	@Test
//	public void testSetObjectType() {
//		mapObject.setObjectType(ObjectType.KEY);
//		assertSame(mapObject.getObjectType(),ObjectType.KEY);
//	}
//
//}
