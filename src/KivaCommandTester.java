public class KivaCommandTester {

    /*Method for testing the KivaCommand.FORWARD*/
    public void testForward() {
        KivaCommand command = KivaCommand.FORWARD;
        System.out.println(command);
        System.out.println(command.getDirectionKey());
    }

    /*Method for testing the KivaCommand.TURN_LEFT*/
    public void testTurnLeft() {
        KivaCommand command = KivaCommand.TURN_LEFT;
        System.out.println(command);
        System.out.println(command.getDirectionKey());
    }

    /*Method for testing the KivaCommand.TURN_RIGHT*/
    public void testTurnRight() {
        KivaCommand command = KivaCommand.TURN_RIGHT;
        System.out.println(command);
        System.out.println(command.getDirectionKey());
    }

    /*Method for testing the KivaCommand.TAKE*/
    public void testTake() {
        KivaCommand command = KivaCommand.TAKE;
        System.out.println(command);
        System.out.println(command.getDirectionKey());
    }

    /*Method for testing the KivaCommand.DROP*/
    public void testDrop() {
        KivaCommand command = KivaCommand.DROP;
        System.out.println(command);
        System.out.println(command.getDirectionKey());
    }


    public static void main(String[] args) {
        new KivaCommandTester().testForward();
        new KivaCommandTester().testTurnLeft();
        new KivaCommandTester().testTurnRight();
        new KivaCommandTester().testTake();
        new KivaCommandTester().testDrop();
    }
}


