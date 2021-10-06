import java.util.HashMap;

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
            res[i] = mapCommandNameToKey.get(userInput.charAt(i));
        }
        return res;
    }

}
