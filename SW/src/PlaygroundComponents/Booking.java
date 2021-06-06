package PlaygroundComponents;
import PlayerComponents.*;

public class Booking {
    public int playgroundOwnerId;
    public int playerId;
    public Slot slot;
    public String state;

    public Booking(Player player){

    }
    public void updateState(String newState){
        state = newState;
    }
}
