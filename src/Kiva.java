import edu.duke.Point;

public class Kiva {

    private Point currentLocation;
    private FacingDirection directionFacing;
    FloorMap map;
    private boolean carryingPod;
    private boolean successfullyDropped;

    /* GETTERS */
    public Point getCurrentLocation() {
        return currentLocation;
    }

    public boolean isCarryingPod() {
        return this.isCarryingPod();
    }

    public boolean isSuccessfullyDropped() {
        return this.isSuccessfullyDropped();
    }

    public FacingDirection getDirectionFacing() {
        return directionFacing;
    }


    /* SETTERS */
    public void setDirectionFacing(FacingDirection directionFacing) {
        this.directionFacing = directionFacing;
    }


    /* CONSTRUCTORS */
    public Kiva(FloorMap map) {
        this.currentLocation = map.getInitialKivaLocation();
    }

    public Kiva(FloorMap map, Point currentPoint) {
        this.currentLocation = currentPoint;
    }


    /* PUBLIC METHODS */
    public void move(KivaCommand command) {
        if (command == KivaCommand.FORWARD) {
            moveForwardHelper();
        } else if (command == KivaCommand.TURN_LEFT) {
            moveTurnLeftHelper();
        } else if (command == KivaCommand.TURN_RIGHT) {
            moveTurnLeftHelper();
        } else if (command == KivaCommand.DROP) {
            moveDropHelper();
        } else if (command == KivaCommand.TAKE) {
            moveTakeHelper();
        } else {
            throw new IllegalMoveException("Command Does Not Exists!");
        }
    }


    /* PRIVATE METHODS */

    private boolean sameLocation(Point a, Point b) {
        return a.getX() == b.getX() && a.getY() == b.getY();
    }


    private void moveTakeHelper() {
        if (!sameLocation(this.map.getPodLocation(), this.getCurrentLocation())) {
            throw new NoPodException("No Pod At this position!");
        }

        if (!this.carryingPod) {
            this.carryingPod = true;
            this.successfullyDropped = false;
        }
    }

    private void moveDropHelper() {
        if (this.carryingPod) {
            this.carryingPod = false;
            this.successfullyDropped = true;
        }
    }


    private void moveTurnRightHelper() {
        if (this.directionFacing == FacingDirection.UP) {
            this.directionFacing = FacingDirection.RIGHT;
        } else if (this.directionFacing == FacingDirection.LEFT) {
            this.directionFacing = FacingDirection.UP;
        } else if (this.directionFacing == FacingDirection.DOWN) {
            this.directionFacing = FacingDirection.LEFT;
        } else if (this.directionFacing == FacingDirection.RIGHT) {
            this.directionFacing = FacingDirection.DOWN;
        }
    }


    private void moveTurnLeftHelper() {
        if (this.directionFacing == FacingDirection.UP) {
            this.directionFacing = FacingDirection.LEFT;
        } else if (this.directionFacing == FacingDirection.LEFT) {
            this.directionFacing = FacingDirection.DOWN;
        } else if (this.directionFacing == FacingDirection.DOWN) {
            this.directionFacing = FacingDirection.RIGHT;
        } else if (this.directionFacing == FacingDirection.RIGHT) {
            this.directionFacing = FacingDirection.UP;
        }
    }

    private void moveForwardHelper() {
        if (this.directionFacing == FacingDirection.UP) {
            this.currentLocation = new Point(
                    this.currentLocation.getX(),
                    this.currentLocation.getY() + 1

            );
        } else if (this.directionFacing == FacingDirection.DOWN) {
            this.currentLocation = new Point(
                    this.currentLocation.getX(),
                    this.currentLocation.getY() - 1

            );
        } else if (this.directionFacing == FacingDirection.LEFT) {
            this.currentLocation = new Point(
                    this.currentLocation.getX() - 1,
                    this.currentLocation.getY()

            );
        } else if (this.directionFacing == FacingDirection.RIGHT) {
            this.currentLocation = new Point(
                    this.currentLocation.getX() + 1,
                    this.currentLocation.getY()

            );
        }
    }


}
