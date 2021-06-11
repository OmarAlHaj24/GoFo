package PlaygroundComponents;

import System.Database;

/**
 * Booking class contains all the details of a booking including the player ID, the playground owner ID and so on.
 *
 * @author Mirette Amin Danial     20190570
 * @version 1.0
 * @since 7 June 2021
 */

public class Booking {
    /**
     * Static variable to counts Booking's objects.
     */
    public static int cnt;
    public int playgroundId;
    public int playerId;
    public int playgroundOwnerId;
    public int slotId;
    /**
     * Playground status.
     */
    public Status state;
    public int id;
    double cost;

    Database db = new Database();

    /**
     * Parameterized constructor.
     *
     * @param plID
     * @param pID
     * @param slotID
     */
    public Booking(int plID, int pID, int slotID) {
        playerId = plID;
        state = Status.PENDING;
        cnt++;
        id = cnt;
        playgroundId = pID;
        cost = db.getCost(playgroundId);
        slotId = slotID;
        playgroundOwnerId = db.getPlaygroundOwnerId(playgroundId);
    }

    /**
     * Overriding toString Function to display booking's information.
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("Booking's ID: " + id + "\nSlot's ID: " + slotId + "\nPlayground's ID: " + playgroundId + "\nCost: " + cost);
    }

    /**
     * Function that updates booking's status.
     *
     * @param newState
     */
    public void updateState(Status newState) {
        state = newState;
    }
}
