import edu.duke.Point;

public class Kiva {

    private Point currentLocation;
    private FacingDirection directionFacing;
    private long motorLifeTime;
    FloorMap map;
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
    public void incrementMotorLifeTime() {
        this.motorLifeTime += 1000;
    }


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
            throw new IllegalMoveException("Command Does Not Exists!");
        }
    }


    /* PRIVATE METHODS */
    private boolean sameLocation(Point a, Point b) {
        return a.getX() == b.getX() && a.getY() == b.getY();
    }


    private void moveTakeHelper() {
        // if no pod where Kiva is at and command to Take was given throw err
        if (!sameLocation(this.map.getPodLocation(), this.getCurrentLocation())) {
            throw new NoPodException("No Pod At this position!");
        }

        if (!this.carryingPod) {
            this.carryingPod = true;
            this.successfullyDropped = false;
        }
    }

    private void moveDropHelper() {
        if (!sameLocation(this.map.getDropZoneLocation(), this.getCurrentLocation())) {
            throw new IllegalDropZoneException("Cannot Drop Pod In Any Other Position Rather than Drop Position!");
        }

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


    private Point forecastFinalPos() {
        /* Returns the point that the robot would end up in if Forward was called */
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


}
