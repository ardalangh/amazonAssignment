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
            KivaCommand mapperRes = mapCommandNameToKey.get(userInput.charAt(i));
            if (mapperRes == null) {
                throw new IllegalArgumentException("Character" + userInput.charAt(i) + " does not correspond to a command!");
            } else {
                res[i] = mapperRes;
            }


        }
        return res;
    }

    public static void main(String[] args) {
        HashMap<Character, KivaCommand> mapCommandNameToKey = new HashMap<>();
        mapCommandNameToKey.put('F', KivaCommand.FORWARD);
        mapCommandNameToKey.put('D', KivaCommand.DROP);
        mapCommandNameToKey.put('L', KivaCommand.TURN_LEFT);
        mapCommandNameToKey.put('R', KivaCommand.TURN_RIGHT);
        mapCommandNameToKey.put('T', KivaCommand.TAKE);


    }

}
