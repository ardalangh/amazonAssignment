import edu.duke.Point;

public class KivaConstructorTest {
    String defaultLayout = ""
            + "-------------\n"
            + "        P   *\n"
            + "   **       *\n"
            + "   **       *\n"
            + "  K       D *\n"
            + " * * * * * **\n"
            + "-------------\n";
    FloorMap defaultMap = new FloorMap(defaultLayout);

    /*tests the kiva constructor using a single parameter*/
    public void testSingleArgumentConstructor() {
        Kiva kiva = new Kiva(defaultMap);
        Point initialLocation = kiva.getCurrentLocation();
        Point expectedLocation = new Point(2, 4);
        if (sameLocation(initialLocation, expectedLocation)) {
            System.out.println("testSingleArgumentConstructor SUCCESS");
        } else {
            System.out.printf("testSingleArgumentConstructor FAIL: %s != (2,4)!%n", initialLocation);
        }
    }

    /*tests the kiva constructor using two parameters*/
    public void testTwoArgumentConstructor() {
        Point point = new Point(5, 6);
        Kiva kiva = new Kiva(
                defaultMap,
                point
        );
        Point initialLocation = kiva.getCurrentLocation();
        if (sameLocation(initialLocation, point)) {
            System.out.println("testTwoArgumentConstructor SUCCESS");
        } else {
            System.out.printf("testTwoArgumentConstructor FAIL: %s != (5,6)!%n", initialLocation);
        }
    }


    /* PRIVATE METHODS */
    private boolean sameLocation(Point a, Point b) {
        return a.getX() == b.getX() && a.getY() == b.getY();
    }


    public static void main(String[] args) {
        KivaConstructorTest constructorTester = new KivaConstructorTest();
        constructorTester.testTwoArgumentConstructor();
        constructorTester.testSingleArgumentConstructor();

    }


}





