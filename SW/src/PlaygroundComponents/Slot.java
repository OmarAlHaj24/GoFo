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

