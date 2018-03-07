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

    public MasterLock(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // getState
    // getNumber
    // turnRight
    // turnLeft
    // close
    // open
    // changeCombo

    private enum State {
        LOCKED_INIT, LOCKED_X, LOCKED_Y, LOCKED_Z, OPEN
    }
}
