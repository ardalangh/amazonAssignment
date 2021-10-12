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


    /*invalid command test case*/
    public void testMoveOutOfBound() {
        Kiva kiva = new Kiva(defaultMap);

        kiva.move(KivaCommand.TURN_LEFT);
        kiva.move(KivaCommand.FORWARD);

        try {
            kiva.move(KivaCommand.FORWARD);
        } catch (Exception e) {
            System.out.println("TEST CASE PASSED -- ERR as EXPECTED");
        }
    }


    /* forward test cases */
    public void testForwardFromUp() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.UP);
        kiva.move(KivaCommand.FORWARD);
        verifyKivaState("testForwardFromUp",
                kiva, new Point(2, 5), FacingDirection.UP, false, false);
    }

    public void testForwardFromLeft() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.LEFT);
        kiva.move(KivaCommand.FORWARD);
        verifyKivaState("testForwardFromLeft",
                kiva, new Point(1, 4), FacingDirection.LEFT, false, false);
    }

    public void testForwardFromRight() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.RIGHT);
        kiva.move(KivaCommand.FORWARD);
        verifyKivaState("testForwardFromRight",
                kiva, new Point(3, 4), FacingDirection.RIGHT, false, false);
    }

    public void testForwardFromDown() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.DOWN);
        kiva.move(KivaCommand.FORWARD);
        verifyKivaState("testForwardFromDown",
                kiva, new Point(2, 3), FacingDirection.DOWN, false, false);
    }


    /* turn_left test cases */
    public void testTurnLeftFromUp() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.UP);
        kiva.move(KivaCommand.TURN_LEFT);
        verifyKivaState("testTurnLeftFromUp",
                kiva, new Point(2, 4), FacingDirection.LEFT, false, false);
    }

    public void testTurnLeftFromDown() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.DOWN);
        kiva.move(KivaCommand.TURN_LEFT);
        verifyKivaState("testTurnLeftFromDown",
                kiva, new Point(2, 4), FacingDirection.RIGHT, false, false);
    }

    public void testTurnLeftFromRight() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.RIGHT);
        kiva.move(KivaCommand.TURN_LEFT);
        verifyKivaState("testTurnLeftFromRight",
                kiva, new Point(2, 4), FacingDirection.UP, false, false);
    }

    public void testTurnLeftFromLeft() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.LEFT);
        kiva.move(KivaCommand.TURN_LEFT);
        verifyKivaState("testTurnLeftFromLeft",
                kiva, new Point(2, 4), FacingDirection.DOWN, false, false);
    }


    /* turn_right test cases */
    public void testTurnRightFromUp() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.UP);
        kiva.move(KivaCommand.TURN_RIGHT);
        verifyKivaState("testTurnRightFromUp",
                kiva, new Point(2, 4), FacingDirection.RIGHT, false, false);
    }

    public void testTurnRightFromDown() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.DOWN);
        kiva.move(KivaCommand.TURN_RIGHT);
        verifyKivaState("testTurnRightFromDown",
                kiva, new Point(2, 4), FacingDirection.LEFT, false, false);
    }

    public void testTurnRightFromRight() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.RIGHT);
        kiva.move(KivaCommand.TURN_RIGHT);
        verifyKivaState("testTurnRightFromRight",
                kiva, new Point(2, 4), FacingDirection.DOWN, false, false);
    }


    public void testTurnRightFromLeft() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.LEFT);
        kiva.move(KivaCommand.TURN_RIGHT);
        verifyKivaState("testTurnRightFromLeft",
                kiva, new Point(2, 4), FacingDirection.UP, false, false);
    }


    /* take test cases */
    public void testTakeOnPod() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.UP);
        for (int i = 0; i < 3; i++) {
            kiva.move(KivaCommand.FORWARD);
        }
        kiva.setDirectionFacing(FacingDirection.RIGHT);
        for (int i = 0; i < 6; i++) {
            kiva.move(KivaCommand.FORWARD);
        }
        kiva.move(KivaCommand.TAKE);
        verifyKivaState("testTakeOnPod",
                kiva, new Point(8, 1), FacingDirection.RIGHT, true, false);
    }


    /* drop test cases */
    public void testTakeDropZone() {
        Kiva kiva = new Kiva(defaultMap);
        kiva.setDirectionFacing(FacingDirection.UP);
        for (int i = 0; i < 3; i++) {
            kiva.move(KivaCommand.FORWARD);
        }
        kiva.setDirectionFacing(FacingDirection.RIGHT);
        for (int i = 0; i < 6; i++) {
            kiva.move(KivaCommand.FORWARD);
        }
        kiva.move(KivaCommand.TAKE);

        kiva.setDirectionFacing(FacingDirection.RIGHT);
        for (int i = 0; i < 2; i++) {
            kiva.move(KivaCommand.FORWARD);
        }

        kiva.setDirectionFacing(FacingDirection.DOWN);
        for (int i = 0; i < 3; i++) {
            kiva.move(KivaCommand.FORWARD);
        }
        kiva.move(KivaCommand.DROP);

        verifyKivaState("testTakeOnPod",
                kiva, new Point(10, 4), FacingDirection.DOWN, false, true);
    }


    /* Private Methods */

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
            System.out.printf("%s: current location SUCCESS%n", testName);
        } else {
            System.out.printf("%s: current location FAIL!%n", testName);
            System.out.printf("Expected %s, got %s%n", expectLocation, actualLocation);
        }

        FacingDirection actualDirection = actual.getDirectionFacing();
        if (actualDirection == expectDirection) {
            System.out.printf("%s: facing direction SUCCESS%n", testName);
        } else {
            System.out.printf("%s: facing direction FAIL!%n", testName);
            System.out.printf("Expected %s, got %s%n",
                    expectDirection, actualDirection);
        }

        boolean actualCarry = actual.isCarryingPod();
        if (actualCarry == expectCarry) {
            System.out.printf("%s: carrying pod SUCCESS%n", testName);
        } else {
            System.out.printf("%s: carrying pod FAIL!%n", testName);
            System.out.printf("Expected %s, got %s%n",
                    expectCarry, actualCarry);
        }

        boolean actualDropped = actual.isSuccessfullyDropped();
        if (actualDropped == expectDropped) {
            System.out.printf("%s: successfully dropped SUCCESS%n", testName);
        } else {
            System.out.printf("%s: successfully dropped FAIL!%n", testName);
            System.out.printf("Expected %s, got %s%n",
                    expectDropped, actualDropped);
        }
    }


    public static void main(String[] args) {
        KivaMoveTest kTester = new KivaMoveTest();
        kTester.testMoveOutOfBound();
        kTester.testForwardFromUp();
        kTester.testForwardFromLeft();
        kTester.testForwardFromDown();
        kTester.testForwardFromRight();

        kTester.testTurnLeftFromUp();
        kTester.testTurnLeftFromDown();
        kTester.testTurnLeftFromLeft();
        kTester.testTurnLeftFromRight();


        kTester.testTurnRightFromUp();
        kTester.testTurnRightFromDown();
        kTester.testTurnRightFromLeft();
        kTester.testTurnRightFromRight();
        kTester.testTakeOnPod();
        kTester.testTakeDropZone();
    }
}

