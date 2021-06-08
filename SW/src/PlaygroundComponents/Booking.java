/**
 * @author Omar Khaled Al Haj      20190351
 * @author Mirette Amin Danial     20190570
 * @author Mostafa Mahmoud Anwar   20190544
 * Created on 6/6/2021
 * @version 1.0
 */
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
