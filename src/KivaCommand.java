public enum KivaCommand {
    FORWARD,
    TURN_LEFT,
    TURN_RIGHT,
    TAKE,
    DROP;


    public String getDirectionKey() {

        String name = this.name();
        if (name.equals("TURN_LEFT") || name.equals("TURN_RIGHT")) {
            return name.substring(5, 6);
        }
        return name.substring(0, 1);
       
    }


}
