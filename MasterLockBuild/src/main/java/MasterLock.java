class MasterLock {
    private static final int size = 40;
    private State state;
    private int position;
    private int x, y, z;

    public static int main(String args[]) {
        int x, y, z;
        x = Integer.parseInt(args[0]);
        y = Integer.parseInt(args[1]);
        z = Integer.parseInt(args[2]);
        MasterLock master = new MasterLock(x, y, z);

        boolean interacting = true;
        while(interacting) {
            // Read input and go through commands
        }
        return 0;
    }

    public enum State {
        CLOSED, X, Y, Z, OPEN
    }

    public MasterLock(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.state = State.OPEN;
        this.position = 0;
    }

    public State getState() {
        State externalState = this.state;
        return externalState;
    }

    public int getPosition() {
        return this.position;
    }

    public boolean close() {
        boolean wasOpen = (this.state == State.OPEN);
        this.state = State.CLOSED;
        return wasOpen;
    }

    public boolean open() {
        return false;
    }
    public boolean turnRight(int change) {
        if (change >= 0) {
            this.position = (this.position + change) % size;
            return true;
        }
        return false;
    }
    public boolean turnLeft(int change) {
        return false;
    }
    public boolean changeCombo(int x, int y, int z) {
        return false;
    }
}
