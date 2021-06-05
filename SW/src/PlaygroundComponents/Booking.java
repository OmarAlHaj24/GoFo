package PlaygroundComponents;

public class Booking {
    int playgroundOwnerId;
    int playerId;
    Slot slot;
    String state;

    public void updateState(String newState){
        state = newState;
    }
}
