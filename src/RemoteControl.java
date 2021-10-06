import edu.duke.FileResource;

import java.util.HashMap;
import java.util.Scanner;

public class RemoteControl {


    public KivaCommand[] convertToKivaCommands(String userInput) {
        KivaCommand[] res = new KivaCommand[userInput.length()];
        HashMap<Character, KivaCommand> mapCommandNameToKey = new HashMap<>();
        mapCommandNameToKey.put('F', KivaCommand.FORWARD);
        mapCommandNameToKey.put('D', KivaCommand.DROP);
        mapCommandNameToKey.put('L', KivaCommand.TURN_LEFT);
        mapCommandNameToKey.put('R', KivaCommand.TURN_RIGHT);
        mapCommandNameToKey.put('T', KivaCommand.TAKE);

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


    public void run() {

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
        System.out.println(floorMap);
        System.out.println("\n\n");
        System.out.println("Kiva is at position " + kiva.getCurrentLocation());
        System.out.println("Kiva is pointing at direction " + kiva.getDirectionFacing());


        KeyboardResource keyboardResource = new KeyboardResource();
        System.out.println("Please enter the directions for the Kiva Robot to take.");
        String directions = keyboardResource.getLine();
        System.out.println("Directions that you typed in: " + directions);

        KivaCommand[] command = convertToKivaCommands(directions);
        System.out.println(command[0]);
        kiva.move(command[0]);
        System.out.println(kiva.getCurrentLocation());


    }


    public static void main(String[] args) {
        RemoteControl rc = new RemoteControl();
        rc.run();


    }

}
