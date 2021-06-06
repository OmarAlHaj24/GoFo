package PlaygroundComponents;

public class Booking {
    public int playgroundOwnerId;
    public int playerId;
    public Slot slot;
    public String state;

    public void updateState(String newState){
        state = newState;
    }
}
