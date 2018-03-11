import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MasterLockTest {
    private static final int x = 5;
    private static final int y = 20;
    private static final int z = 15;
    private static final int size = 40;

    @Test
    public void getInitState() {
        MasterLock m = new MasterLock(x, y, z);
        assertEquals(MasterLock.State.OPEN, m.getState());
    }

    @Test
    public void getClosedState() {
        MasterLock m = new MasterLock(x, y, z);
        assertEquals(MasterLock.State.OPEN, m.getState());
        assertEquals(true, m.close());
        assertEquals(MasterLock.State.CLOSED, m.getState());
    }

    @Test
    public void getInitPosition() {
        MasterLock m = new MasterLock(x, y, z);
        assertEquals(0, m.getPosition());
    }

    @Test
    public void turnLeftNormal() {
        MasterLock m = new MasterLock(x, y, z);
        assertEquals(true, m.turnLeft(5));
        assertEquals(5, m.getPosition());
    }

    @Test
    public void turnLeftNegated() {
        MasterLock m = new MasterLock(x, y, z);
        assertEquals(false, m.turnLeft(-5));
        assertEquals(0, m.getPosition());
    }

    @Test
    public void turnLeftModulus() {
        MasterLock m = new MasterLock(x, y, z);
        m.turnLeft(5);
        assertEquals(5, m.getPosition());
        m.turnLeft(40);
        assertEquals(5, m.getPosition());
    }

    @Test
    public void turnLeftZero() {
        MasterLock m = new MasterLock(x, y, z);
        assertEquals(false, m.turnLeft(0));
        assertEquals(0, m.getPosition());
    }

    @Test
    public void turnRightZero() {
        MasterLock m = new MasterLock(x, y, z);
        assertEquals(false, m.turnRight(0));
        assertEquals(0, m.getPosition());
    }

    @Test
    public void turnRightNormal() {
        MasterLock m = new MasterLock(x, y, z);
        assertEquals(true, m.turnRight(5));
        assertEquals(35, m.getPosition());
    }

    @Test
    public void turnRightNegated() {
        MasterLock m = new MasterLock(x, y, z);
        assertEquals(false, m.turnRight(-5));
        assertEquals(0, m.getPosition());
    }

    @Test
    public void turnRightModulus() {
        MasterLock m = new MasterLock(x, y, z);
        m.turnRight(5);
        assertEquals(35, m.getPosition());
        m.turnRight(40);
        assertEquals(35, m.getPosition());
    }

    @Test
    public void turnRightUntil() {
        MasterLock m = new MasterLock(x, y, z);
        assertEquals(5, m.turnRightUntil(35));
        assertEquals(35, m.turnRightUntil(0));
    }

    @Test
    public void turnLeftUntil() {
        MasterLock m = new MasterLock(x, y, z);
        assertEquals(5, m.turnLeftUntil(5));
        assertEquals(35, m.turnLeftUntil(0));
    }
    // Will test these cases eventually inside other tests
    // MasterLock a = new MasterLock(5, 3, 1); // x > y > z
    // MasterLock b = new MasterLock(25, 4, 20); // x > z > y
    // MasterLock c = new MasterLock(7, 39, 0); // y > x > z
    // MasterLock d = new MasterLock(3, 19, 9); // y > z > x
    // MasterLock e = new MasterLock(7, 0, 29); // z > x > y
    // MasterLock f = new MasterLock(15, 20, 25); // z > y > x
    // MasterLock g = new MasterLock(10, 10, 5); // x == y > z
    // MasterLock h = new MasterLock(5, 20, 20); // y == z > x
    // MasterLock i = new MasterLock(0, 0, 0); // x == y == z

    @Test
    public void testOpen() {
        MasterLock a = new MasterLock(5, 3, 1); // x > y > z
        a.close();
        a.turnRight(80);
        a.turnRight(40 - 5);
        a.turnLeft(80 - (5 - 3));
        a.turnRight(3 - 1);
        a.open();
        assertEquals(MasterLock.State.OPEN, a.getState());

        MasterLock b = new MasterLock(25, 4, 20); // x > z > y
        b.close();
        b.turnRight(80);
        b.turnRight(40 - 25);
        b.turnLeft(80 - (25 - 4));
        b.turnRight(40 - (20 - 4));
        b.open();
        assertEquals(MasterLock.State.OPEN, b.getState());

        MasterLock e = new MasterLock(7, 0, 29); // z > x > y
        e.close();
        e.turnRight(80);
        e.turnRight(40 - 7);
        e.turnLeft(80 - (7 - 0));
        e.turnRight(40 - 29);
        e.open();
        assertEquals(MasterLock.State.OPEN, e.getState());
    }

    @Test
    public void testIncrementalOpen() {
        MasterLock f = new MasterLock(15, 20, 25); // z > y > x
        f.close();
        f.turnRight(40);
        f.turnRight(40);
        f.turnRight(5);
        f.turnRight(20);
        f.turnLeft(40);
        f.turnLeft(5);
        f.turnRight(1);
        f.turnRight(1);
        f.turnRight(1);
        f.turnRight(1);
        f.turnRight(1);
        f.turnRight(30);
        f.open();
        assertEquals(MasterLock.State.OPEN, f.getState());

        MasterLock g = new MasterLock(10, 10, 5); // x == y > z
        g.close();
        g.turnRight(40);
        g.turnRight(33);
        g.turnRight(37);
        g.turnLeft(20);
        g.turnLeft(20);
        g.turnRight(2);
        g.turnRight(3);
        g.open();
        assertEquals(MasterLock.State.OPEN, g.getState());
    }

    @Test
    public void openAllEqual() {
        MasterLock i = new MasterLock(0, 0, 0); // x == y == z
        i.close();
        i.turnRight(80);
        i.turnLeft(40);
        i.turnRight(40);
        i.open();
        assertEquals(MasterLock.State.OPEN, i.getState());
    }

    @Test
    public void testIncrementalOpenWithEarlyStop() {
        MasterLock h = new MasterLock(5, 20, 20); // y == z > x
        h.close();
        h.turnRight(2 * size);
        h.turnRightUntil(5);
        h.turnLeftUntil(20);
        h.turnLeft(size);
        h.turnRight(size);
        h.open();
        assertEquals(MasterLock.State.OPEN, h.getState());
    }
}
