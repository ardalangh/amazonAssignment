import edu.duke.Point;

public class Kiva {

    private Point currentLocation;
    private FacingDirection directionFacing;
    private long motorLifeTime;
    private final FloorMap map;
    private boolean carryingPod;
    private boolean successfullyDropped;

    /* GETTERS */
    public long getMotorLifeTime() {
        return motorLifeTime;
    }

    public Point getCurrentLocation() {
        return currentLocation;
    }

    public boolean isCarryingPod() {
        return this.carryingPod;
    }

    public boolean isSuccessfullyDropped() {
        return this.successfullyDropped;
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
        this.map = map;
        this.successfullyDropped = false;
        this.currentLocation = map.getInitialKivaLocation();
        this.directionFacing = FacingDirection.UP;
    }

    public Kiva(FloorMap map, Point currentPoint) {
        this.map = map;
        this.successfullyDropped = false;
        this.currentLocation = currentPoint;
        this.directionFacing = FacingDirection.UP;
    }


    /* PUBLIC METHODS */

    /**
     * @params: Nothing
     * @returns: void
     * adds the motorlife by 1 second
     */
    public void incrementMotorLifeTime() {
        this.motorLifeTime += 1000;
    }


    /**
     * @params: KivaCommand command
     * @returns: void
     * given a command of time KivaCommand call the appropriate helper method to do the task.
     * if command is not a valid command throw IllegalMoveException
     */
    public void move(KivaCommand command) {
        if (command == KivaCommand.FORWARD) {
            if (this.successfullyDropped) {
                this.successfullyDropped = false;
            }
            moveForwardHelper();
        } else if (command == KivaCommand.TURN_LEFT) {
            if (this.successfullyDropped) {
                this.successfullyDropped = false;
            }
            moveTurnLeftHelper();
            this.incrementMotorLifeTime();
        } else if (command == KivaCommand.TURN_RIGHT) {
            if (this.successfullyDropped) {
                this.successfullyDropped = false;
            }
            moveTurnRightHelper();
            this.incrementMotorLifeTime();
        } else if (command == KivaCommand.DROP) {
            moveDropHelper();
        } else if (command == KivaCommand.TAKE) {
            moveTakeHelper();
        } else {
            throw new IllegalMoveException("Command: " + command + "does Not Exists!");
        }
    }


    /* PRIVATE METHODS */

    /**
     * @params: Point a, Point b
     * @returns: boolean
     * return true if point a and point b are the same and false otherwise
     */
    private boolean sameLocation(Point a, Point b) {
        return a.getX() != b.getX() || a.getY() != b.getY();
    }


    /**
     * @params: Nothing
     * @returns: void
     * 1. if there is no pod at the current location throw a NoPodException
     * 2. if kiva is not carrying any pods, update the appropriate variables
     */
    private void moveTakeHelper() {
        if (sameLocation(this.map.getPodLocation(), this.getCurrentLocation())) {
            throw new NoPodException("No Pod At position of " + this.getCurrentLocation());
        }
        if (!this.carryingPod) {
            this.carryingPod = true;
            this.successfullyDropped = false;
        }
    }

    /**
     * @params: Nothing
     * @returns: void
     * 1. if kiva is not at the drop position throw a IllegalDropZoneException err
     * 2. if kiva is carrying a pod, update the appropriate variables
     */
    private void moveDropHelper() {
        if (sameLocation(this.map.getDropZoneLocation(), this.getCurrentLocation())) {
            throw new IllegalDropZoneException(this.getCurrentLocation() + " is not a valid dropPoint");
        }
        if (this.carryingPod) {
            this.carryingPod = false;
            this.successfullyDropped = true;
        }
    }


    /**
     * @params: Nothing
     * @returns: void
     * based on the current direction adjust the direction after the method is called
     */
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

    /**
     * @params: Nothing
     * @returns: void
     * based on the current direction adjust the direction after the method is called
     */
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

    /**
     * @params: Nothing
     * @returns: void
     * throw a IllegalMoveException if kiva is forecasted to go out of bound or is hitting an obstacle
     * if all well update the kiva position
     */
    private void moveForwardHelper() {
        Point forecastedFinalKivaPos = forecastFinalPos();
        if (forecastedFinalKivaPos.getX() > this.map.getMaxColNum() ||
                forecastedFinalKivaPos.getY() > this.map.getMaxRowNum() ||
                forecastedFinalKivaPos.getX() < 1 ||
                forecastedFinalKivaPos.getY() < 1) {
            throw new IllegalMoveException("Robot is out of bound!" +
                    "Robot position: " +
                    forecastedFinalKivaPos);
        }
        if (this.map.getObjectAtLocation(forecastedFinalKivaPos) == FloorMapObject.OBSTACLE) {
            throw new IllegalMoveException("Robot cannot ran into the wall!" + "kiva location: " + forecastedFinalKivaPos);
        }
        this.incrementMotorLifeTime();
        this.currentLocation = forecastedFinalKivaPos;
    }


    /**
     * @params: Nothing
     * @returns: Point
     * returns the point that the robot would end up in if Forward Command was executed
     * the position of kiva is not altered by calling this method. This is just a forecast.
     */
    private Point forecastFinalPos() {
        Point res = null;
        if (this.directionFacing == FacingDirection.UP) {
            res = new Point(
                    this.currentLocation.getX(),
                    this.currentLocation.getY() - 1

            );
        } else if (this.directionFacing == FacingDirection.DOWN) {
            res = new Point(
                    this.currentLocation.getX(),
                    this.currentLocation.getY() + 1

            );
        } else if (this.directionFacing == FacingDirection.LEFT) {
            res = new Point(
                    this.currentLocation.getX() - 1,
                    this.currentLocation.getY()

            );
        } else if (this.directionFacing == FacingDirection.RIGHT) {
            res = new Point(
                    this.currentLocation.getX() + 1,
                    this.currentLocation.getY()

            );
        }
        return res;
    }
}
