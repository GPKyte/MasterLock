import java.util.Scanner;

class MasterLock {
    private static final int size = 40;
    private State state; // Enum representing the states of the lock where OPEN is accepting
    private int position; // Index from [0..size]
    private int displacement; // Distance traveled since last direction change
    private int x, y, z; // The three lock combo numbers

    public static void main(String args[]) {
        int x, y, z;
        try {
            x = Integer.parseInt(args[0]);
            y = Integer.parseInt(args[1]);
            z = Integer.parseInt(args[2]);
        } catch (java.lang.ArrayIndexOutOfBoundsException i) {
            System.out.println("(Note: you can supply x, y, z as params at startup)");
            x = y = z = 0;
        }
        MasterLock m = new MasterLock(x, y, z);

        System.out.println("Welcome to MasterLock Emulator v1.0.0");
        System.out.println("For a list of commands, type help");
        Scanner in = new Scanner(System.in);
        boolean interacting = true;
        boolean debug = false;
        while(interacting) {
            // Read input and go through commands
            String[] input = in.nextLine().split(" ");
            String command = input[0];
            switch (command) {
                case "help":        System.out.println("Commands are camelCase and in the form of \"<CMD> [ARG1 ARG2 ...]\"");
                                    System.out.println("You can:\n"
                                                        + "\tchangeCombo\n"
                                                        + "\tgetState\n"
                                                        + "\tgetPosition\n"
                                                        + "\tturnLeft\n"
                                                        + "\tturnRight\n"
                                                        + "\topen\n"
                                                        + "\tclose\n"
                                                        + "\tdebug\n"
                                                        + "\texit");
                                    System.out.println(" - debug command will give you internal state of machine.");
                                    break;
                case "changeCombo": x = Integer.parseInt(input[1]);
                                    y = Integer.parseInt(input[2]);
                                    z = Integer.parseInt(input[3]);
                                    System.out.println((m.changeCombo(x, y, z)) ?
                                                        "Combo changed to "+x+", "+y+", "+z :
                                                        "Combo not changed. Must be open.");
                                    break;
                case "getState":    System.out.println(m.getState());
                                    break;
                case "getPosition": System.out.println(m.getPosition());
                                    break;
                case "turnLeft":    int i = Integer.parseInt(input[1]);
                                    System.out.println((m.turnLeft(i)) ?
                                                        "Turned left by "+i+" ticks" :
                                                        "Did not turn. ARG1 Must be > 0");
                                    break;
                case "turnRight":   i = Integer.parseInt(input[1]);
                                    System.out.println((m.turnRight(i)) ?
                                                        "Turned right by "+i+" ticks" :
                                                        "Did not turn. ARG1 Must be > 0");
                                    break;
                case "open":        System.out.println((m.open()) ?
                                                        "Success!" :
                                                        "Failed to open. Remember:\n"
                                                        + "\tTurn right to X,\n"
                                                        + "\tturn left a full turn and stop on Y,\n"
                                                        + "\tthen turn right to Z and open.");
                                    break;
                case "close":       System.out.println((m.close()) ?
                                                        "Closed" :
                                                        "Already closed");
                                    break;
                case "debug":       debug = !debug;
                                    break;
                case "exit":
                case "bye":         interacting = false;
                                    break;
                default:            break;
            }
            if (debug) {System.out.println("\t\t\t\t"+m.state);}
        }
        in.close();
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
        return (this.state == State.OPEN)? State.OPEN : State.CLOSED;
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
        if (this.state == State.Z) {
            this.state = State.OPEN;
            return true;
        }
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
        if (this.state == State.OPEN) {
            this.x = x;
            this.y = y;
            this.z = z;
            return true;
        }
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

        // Transition logic to mimic unlock process
        switch (this.state) {
            case CLOSED:    if (-this.displacement >= 2*size && this.position == this.x) {
                                this.state = State.X;
                            }
                            break;
            case X:         if (this.displacement >= 0 && this.displacement < 2*size && this.position != this.y) {
                                this.state = State.X;
                            } else if (this.displacement >= size && this.displacement < 2*size && this.position == this.y) {
                                this.state = State.Y;
                            } else {
                                this.state = State.CLOSED;
                            }
                            break;
            case Y:         if (this.displacement < 0 && -this.displacement <= size && this.position != z) {
                                this.state = State.Y;
                            } else if (this.displacement < 0 && -this.displacement <= size && this.position == z) {
                                this.state = State.Z;
                            } else {
                                this.state = State.CLOSED;
                            }
                            break;
            case Z:         if (this.position != z) {
                                this.state = State.CLOSED;
                            }
                            break;
            default:        break;
        }
    }
}
