import edu.duke.Point;

public class KivaMoveTest {

    String defaultLayout = ""
            + "-------------\n"
            + "        P   *\n"
            + "   **       *\n"
            + "   **       *\n"
            + "  K       D *\n"
            + " * * * * * **\n"
            + "-------------\n";

    FloorMap defaultMap = new FloorMap(defaultLayout);


    /* FORWARD TEST CASES */
    public void testForwardFromUp() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.UP);
        kiva.move(KivaCommand.FORWARD);
        verifyKivaState("testForwardFromUp",
                kiva, new Point(2, 3), FacingDirection.UP, false, false);
    }

    public void testForwardFromLeft() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.LEFT);
        kiva.move(KivaCommand.FORWARD);
        verifyKivaState("testForwardFromLeft",
                kiva, new Point(1, 2), FacingDirection.LEFT, false, false);
    }

    public void testForwardFromRight() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.RIGHT);
        kiva.move(KivaCommand.FORWARD);
        verifyKivaState("testForwardFromRight",
                kiva, new Point(3, 2), FacingDirection.RIGHT, false, false);
    }

    public void testForwardFromDown() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.DOWN);
        kiva.move(KivaCommand.FORWARD);
        verifyKivaState("testForwardFromDown",
                kiva, new Point(2, 1), FacingDirection.DOWN, false, false);
    }


    /* TURN LEFT TEST CASES */
    public void testTurnLeftFromUp() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.UP);
        kiva.move(KivaCommand.TURN_LEFT);
        verifyKivaState("testTurnLeftFromUp",
                kiva, new Point(2, 2), FacingDirection.LEFT, false, false);
    }

    public void testTurnLeftFromDown() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.DOWN);
        kiva.move(KivaCommand.TURN_LEFT);
        verifyKivaState("testTurnLeftFromDown",
                kiva, new Point(2, 2), FacingDirection.RIGHT, false, false);
    }

    public void testTurnLeftFromRight() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.RIGHT);
        kiva.move(KivaCommand.TURN_LEFT);
        verifyKivaState("testTurnLeftFromRight",
                kiva, new Point(2, 2), FacingDirection.UP, false, false);
    }

    public void testTurnLeftFromLeft() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        verifyKivaState("testTurnLeftFromLeft",
                kiva, new Point(2, 2), FacingDirection.DOWN, false, false);
    }


    /* TURN RIGHT TEST CASES */
    public void testTurnRightFromUp() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.UP);
        kiva.move(KivaCommand.TURN_RIGHT);
        verifyKivaState("testTurnRightFromUp",
                kiva, new Point(2, 2), FacingDirection.RIGHT, false, false);
    }

    public void testTurnRightFromDown() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.DOWN);
        kiva.move(KivaCommand.TURN_RIGHT);
        verifyKivaState("testTurnRightFromDown",
                kiva, new Point(2, 2), FacingDirection.LEFT, false, false);
    }

    public void testTurnRightFromRight() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.RIGHT);
        kiva.move(KivaCommand.TURN_RIGHT);
        verifyKivaState("testTurnRightFromRight",
                kiva, new Point(2, 2), FacingDirection.DOWN, false, false);
    }

    public void testTurnRightFromLeft() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.LEFT);
        kiva.move(KivaCommand.TURN_RIGHT);
        verifyKivaState("testTurnRightFromLeft",
                kiva, new Point(2, 2), FacingDirection.UP, false, false);
    }


    /* TAKE TEST CASE */
    public void testTakeOnPod() {
        Kiva kiva = new Kiva(defaultMap);
        // face up and move three times
        kiva.setDirectionFacing(FacingDirection.UP);
        for (int i = 0; i < 3; i++) {
            kiva.move(KivaCommand.FORWARD);
        }
        // Turn right and move 6 times
        kiva.setDirectionFacing(FacingDirection.RIGHT);
        for (int i = 0; i < 6; i++) {
            kiva.move(KivaCommand.FORWARD);
        }
        kiva.move(KivaCommand.TAKE);

        verifyKivaState("testTakeOnPod",
                kiva, new Point(5, 8), FacingDirection.RIGHT, true, false);
    }


    /* DROP TEST CASE */
    public void testTakeDropZone() {
        Kiva kiva = new Kiva(defaultMap);
        // face up and move three times
        kiva.setDirectionFacing(FacingDirection.UP);
        for (int i = 0; i < 3; i++) {
            kiva.move(KivaCommand.FORWARD);
        }
        // Turn right and move 6 times
        kiva.setDirectionFacing(FacingDirection.RIGHT);
        for (int i = 0; i < 6; i++) {
            kiva.move(KivaCommand.FORWARD);
        }
        kiva.move(KivaCommand.TAKE);

        // move right 2
        kiva.setDirectionFacing(FacingDirection.RIGHT);
        for (int i = 0; i < 2; i++) {
            kiva.move(KivaCommand.FORWARD);
        }

        // face down and move 3 -- end up at the drop point
        kiva.setDirectionFacing(FacingDirection.DOWN);
        for (int i = 0; i < 3; i++) {
            kiva.move(KivaCommand.FORWARD);
        }
        kiva.move(KivaCommand.DROP);

        verifyKivaState("testTakeOnPod",
                kiva, new Point(7, 2), FacingDirection.DOWN, false, true);
    }


    // For you: create all the other tests and call verifyKivaState() for each

    private boolean sameLocation(Point a, Point b) {
        return a.getX() == b.getX() && a.getY() == b.getY();
    }

    private void verifyKivaState(
            String testName,
            Kiva actual,
            Point expectLocation,
            FacingDirection expectDirection,
            boolean expectCarry,
            boolean expectDropped) {

        Point actualLocation = actual.getCurrentLocation();
        if (sameLocation(actualLocation, expectLocation)) {
            System.out.println(
                    String.format("%s: current location SUCCESS", testName));
        } else {
            System.out.println(
                    String.format("%s: current location FAIL!", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectLocation, actualLocation));
        }

        FacingDirection actualDirection = actual.getDirectionFacing();
        if (actualDirection == expectDirection) {
            System.out.println(
                    String.format("%s: facing direction SUCCESS", testName));
        } else {
            System.out.println(
                    String.format("%s: facing direction FAIL!", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectDirection, actualDirection));
        }

        boolean actualCarry = actual.isCarryingPod();
        if (actualCarry == expectCarry) {
            System.out.println(
                    String.format("%s: carrying pod SUCCESS", testName));
        } else {
            System.out.println(
                    String.format("%s: carrying pod FAIL!", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectCarry, actualCarry));
        }

        boolean actualDropped = actual.isSuccessfullyDropped();
        if (actualDropped == expectDropped) {
            System.out.println(
                    String.format("%s: successfully dropped SUCCESS", testName));
        } else {
            System.out.println(
                    String.format("%s: successfully dropped FAIL!", testName));
            System.out.println(
                    String.format("Expected %s, got %s",
                            expectDropped, actualDropped));
        }
    }
}

