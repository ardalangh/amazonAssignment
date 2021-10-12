import edu.duke.FileResource;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/*RemoteControl class is in charge of converting user input into commands that kiva will understand */
public class RemoteControl {


    private final Map<Character, KivaCommand> mapCommandNameToKey = new HashMap<>() {{
        put('F', KivaCommand.FORWARD);
        put('D', KivaCommand.DROP);
        put('L', KivaCommand.TURN_LEFT);
        put('R', KivaCommand.TURN_RIGHT);
        put('T', KivaCommand.TAKE);
    }};


    /**
     * @returns: KivaCommand[]
     * @params: String userInput
     * create a kivaCommand array. iterate through the userInputs and add the mapped kivaCommand to the array
     * return the array when iteration is over.
     */
    public KivaCommand[] convertToKivaCommands(String userInput) {
        KivaCommand[] res = new KivaCommand[userInput.length()];
        for (int i = 0; i < userInput.length(); i++) {
            KivaCommand mapperRes = mapCommandNameToKey.get(userInput.charAt(i));
            if (mapperRes == null) {
                throw new IllegalArgumentException("Character" + userInput.charAt(i) + " does not correspond to a command!");
            } else {
                res[i] = mapperRes;
            }
        }
        return res;
    }


    /**
     * @returns: void
     * @params: Nothing
     * 1 : prompt user to choose a map. if user input is invalid return null and end execution other wise instantiate
     * kiva object using the proper chosen map
     * 2 : display the map
     * display Kiva position and rotation for the user
     * process the user input and call the proper command to alter the state of kiva accordingly
     */

    public void run() {
        // 1:
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select a map file.");
        String mapNumber = scanner.nextLine();
        int mapNumberInt = Integer.parseInt(mapNumber);
        if (mapNumberInt > 3 || mapNumberInt < 1) {
            System.out.println("Map number " + mapNumber + " does not exits!");
            return;
        }
        FileResource fileResource = new FileResource("src/sample_floor_map" + mapNumber + ".txt");
        String inputMap = fileResource.asString();
        FloorMap floorMap = new FloorMap(inputMap);
        Kiva kiva = new Kiva(floorMap);
        // 2:
        System.out.println(floorMap + "\n\n");
        System.out.println("Kiva is at position " + kiva.getCurrentLocation());
        System.out.println("Kiva is pointing at direction " + kiva.getDirectionFacing());
        KeyboardResource keyboardResource = new KeyboardResource();
        System.out.println("Please enter the directions for the Kiva Robot to take.");
        String directions = keyboardResource.getLine();
        System.out.println("Directions that you typed in: " + directions);

        KivaCommand[] command = convertToKivaCommands(directions);
        kiva.move(command[0]);
        System.out.println(kiva.getCurrentLocation());


    }


    public static void main(String[] args) {
        RemoteControl rc = new RemoteControl();
        rc.run();
    }

}
