class MasterLock {
    private State state;
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
    }

    public State getState() {
        State externalState = this.state;
        return externalState;
    }

    public boolean close() {
        boolean wasOpen = (this.state == State.OPEN);
        this.state = State.CLOSED;
        return wasOpen;
    }
    // getState
    // getNumber
    // turnRight
    // turnLeft
    // close
    // open
    // changeCombo
}
