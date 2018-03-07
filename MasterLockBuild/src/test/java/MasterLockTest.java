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


}
