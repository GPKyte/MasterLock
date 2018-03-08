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
}
