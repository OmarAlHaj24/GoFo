package PlaygroundComponents;
import PlayerComponents.*;
import System.*;

import javax.xml.crypto.Data;

public class Booking {
    public int playgroundId;
    public int playerId;
    public Slot slot;
    public Status state;
    public static int id;
    Database db = new Database();

    public Booking(int plID, int pID, int slotID){
        playerId = plID;
        state = Status.PENDING;
        id++;
        playgroundId = pID;

    }
    public void updateState(Status newState){
        state = newState;
    }
}
