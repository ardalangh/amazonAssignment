/**
 * KivaCommand will be fed to move method of the Kiva class and that will update the state of Kiva
 */
public enum KivaCommand {
    FORWARD,
    TURN_LEFT,
    TURN_RIGHT,
    TAKE,
    DROP;

    /**
     * @returns: String
     * @params: Nothing
     * give a KivaCommand returns the first descriptive char of the command in string
     */
    public String getDirectionKey() {
        String name = this.name();
        if (name.equals("TURN_LEFT") || name.equals("TURN_RIGHT")) {
            return name.substring(5, 6);
        }
        return name.substring(0, 1);

    }
}
