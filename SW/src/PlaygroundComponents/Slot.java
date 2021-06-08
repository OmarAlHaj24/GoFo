package PlaygroundComponents;

/**
 * Slot class will contain all the details of a slot such as the time and date of it.
 *
 * @author Omar Khaled Al Haj      20190351
 * @author Mirette Amin Danial     20190570
 * @author Mostafa Mahmoud Anwar   20190544
 * @version 1.0
 * @since 7 June 2021
 */

public class Slot {

    /**
     * Static variable to counts Slot objects.
     */
    public static int slotId = 0;
    /**
     * Slot begin time of TimeDate type.
     */
    public TimeDate beginOfSlot;
    /**
     * Slot end time of TimeDate type.
     */
    public TimeDate endOfSlot;
    /**
     * Slot's ID.
     */
    public int id;

    /**
     * Parametrized Constructor.
     *
     * @param b
     * @param e
     */
    public Slot(TimeDate b, TimeDate e) {
        beginOfSlot = b;
        endOfSlot = e;
        slotId++;
        id = slotId;
    }
}