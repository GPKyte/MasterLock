import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MasterLockTest {
    private static final int x = 5;
    private static final int y = 20;
    private static final int z = 15;

    // @Test
    // public void get() {
    //     MasterLock m = new MasterLock(x, y, z);
    // }

    @Test
    public void getInitState() {
        MasterLock m = new MasterLock(x, y, z);
        assertEquals(m.getState(), MasterLock.State.OPEN);
    }

    @Test
    public void getClosedState() {
        MasterLock m = new MasterLock(x, y, z);
        assertEquals(m.getState(), MasterLock.State.OPEN);
        assertEquals(m.close(), true);
        assertEquals(m.getState(), MasterLock.State.CLOSED);
    }

    @Test
    public void getInitPosition() {
        MasterLock m = new MasterLock(x, y, z);
        assertEquals(m.getPosition(), 0);
    }

    @Test
    public void turnLeftNormal() {
        MasterLock m = new MasterLock(x, y, z);
        assertEquals(m.turnLeft(5), true);
        assertEquals(m.getPosition(), 5);
    }

    @Test
    public void turnLeftNegated() {
        MasterLock m = new MasterLock(x, y, z);
        assertEquals(m.turnLeft(-5), false);
        assertEquals(m.getPosition(), 0);
    }

    @Test
    public void turnLeftModulus() {
        MasterLock m = new MasterLock(x, y, z);
        m.turnLeft(5);
        assertEquals(m.getPosition(), 5);
        m.turnLeft(40);
        assertEquals(m.getPosition(), 5);
    }

    @Test
    public void turnLeftZero() {
        MasterLock m = new MasterLock(x, y, z);
        assertEquals(m.turnLeft(0), false);
        assertEquals(m.getPosition(), 0);
    }

    @Test
    public void turnRightZero() {
        MasterLock m = new MasterLock(x, y, z);
        assertEquals(m.turnRight(0), false);
        assertEquals(m.getPosition(), 0);
    }

    @Test
    public void turnRightNormal() {
        MasterLock m = new MasterLock(x, y, z);
        assertEquals(m.turnRight(5), true);
        assertEquals(m.getPosition(), 35);
    }

    @Test
    public void turnRightNegated() {
        MasterLock m = new MasterLock(x, y, z);
        assertEquals(m.turnRight(-5), false);
        assertEquals(m.getPosition(), 0);
    }

    @Test
    public void turnRightModulus() {
        MasterLock m = new MasterLock(x, y, z);
        m.turnRight(5);
        assertEquals(m.getPosition(), 35);
        m.turnRight(40);
        assertEquals(m.getPosition(), 35);
    }

    @Test
    public void testOpen() {
        MasterLock a = new MasterLock(5, 3, 1); // x > y > z
        a.close();
        a.turnRight(40 - 5);
        a.turnLeft(80 - (5 - 3));
        a.turnRight(3 - 1);
        a.open();
        assertEquals(a.getState(), MasterLock.State.OPEN);

        MasterLock b = new MasterLock(25, 4, 20); // x > z > y
        b.close();
        b.turnRight(40 - 25);
        b.turnLeft(80 - (25 - 4));
        b.turnRight(40 - (20 - 4));
        b.open();
        assertEquals(b.getState(), MasterLock.State.OPEN);

        // MasterLock c = new MasterLock(7, 39, 0); // y > x > z
        // MasterLock d = new MasterLock(3, 19, 9); // y > z > x
        // MasterLock e = new MasterLock(7, 0, 29); // z > x > y

        e.close();
        e.turnRight(40 - 7);
        e.turnLeft(80 - (7 - 0));
        e.turnRight(40 - 29);
        e.open();
        assertEquals(e.getState(), MasterLock.State.OPEN);
        
        // MasterLock f = new MasterLock(15, 20, 25); // z > y > x
        // MasterLock g = new MasterLock(10, 10, 5); // x == y > z
        // MasterLock h = new MasterLock(5, 20, 20); // y == z > x
        // MasterLock i = new MasterLock(0, 0, 0); // x == y == z
    }
}
