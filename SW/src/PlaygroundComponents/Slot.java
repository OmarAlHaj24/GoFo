/**
 * @author Omar Khaled Al Haj      20190351
 * @author Mirette Amin Danial     20190570
 * @author Mostafa Mahmoud Anwar   20190544
 * Created on 6/6/2021
 * @version 1.0
 */

package PlaygroundComponents;

public class Slot {
    public static int slotId;
    public TimeDate beginOfSlot;
    public TimeDate endOfSlot;

    public Slot(TimeDate b, TimeDate e){
        beginOfSlot=b;
        endOfSlot=e;
        slotId++;
    }
}