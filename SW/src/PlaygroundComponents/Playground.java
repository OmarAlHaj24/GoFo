package PlaygroundComponents;
import java.util.ArrayList;
import java.util.Scanner;

public class Playground {
    String name;
    String address;
    public static int id;
    int playgroundOwnerId;
    ArrayList<Slot> slotList = new ArrayList <Slot>();
    Scanner scan = new Scanner (System.in);

    public Status state;

    public Playground(int pId) {
        id++;
        playgroundOwnerId = pId;
        System.out.print("Playground's Name: ");
        name = scan.next();
        System.out.print("Playground's Address: ");
        address = scan.next();
    }

    public void run () {

    }
    // tostring overloading required



    // m7tagha fel database :DD
    public void addSlot(Slot slot) {
        slotList.add(slot);
    }

    public void display(){

    }
}
