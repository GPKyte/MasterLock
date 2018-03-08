class MasterLock {
    private static final int size = 40;
    private State state; // Enum representing the states of the lock where OPEN is accepting
    private int position; // Index from [0..size]
    private int displacement; // Distance traveled since last direction change
    private int x, y, z; // The three lock combo numbers

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
        this.displacement = 0;
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
        if (change > 0) {
            turn(-change);
            return true;
        }
        return false;
    }

    public boolean turnLeft(int change) {
        if (change > 0) {
            turn(change);
            return true;
        }
        return false;
    }

    public boolean changeCombo(int x, int y, int z) {
        return false;
    }

    private void turn(int change) {
        int tmp = (this.position + change) % size;
        this.position = (tmp < 0) ? tmp + 40 : tmp;

        // Determine if direction changed and update displacement
        if ((this.displacement == 0) ||
            (this.displacement > 0 && change > 0) ||
            (this.displacement < 0 && change < 0)) {
            this.displacement += change;
        } else {
            this.displacement = change;
        }

        switch (this.state) {
            case CLOSED:    if (this.displacement <= 0 && this.position == this.x) {
                                this.state = State.X;
                                this.displacement = 0;
                            }
                            break;
            case X:         if (this.displacement >= size && this.displacement < 2*size && this.position == this.y) {
                                this.state = State.Y;
                                this.displacement = 0;
                            } else {
                                this.state = State.CLOSED;
                            }
                            break;
            case Y:         if (this.displacement < 0 && this.position == z) {
                                this.state = State.Z;
                                this.displacement = 0;
                            } else {
                                this.state = State.CLOSED;
                            }
                            break;
            case Z:         if (this.displacement != 0 || this.position != z) {
                                this.state = State.CLOSED;
                            }
                            break;
            default:        break;
        }
    }
}
