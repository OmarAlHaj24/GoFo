package PlaygroundComponents;
import PlayerComponents.*;

public class Booking {
    int playgroundOwnerId;
    int playerId;
    Slot slot;
    String state;

    public Booking(Player player){

    }
    public void updateState(String newState){
        state = newState;
    }
}
