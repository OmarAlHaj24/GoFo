/**
 * @author Omar Khaled Al Haj      20190351
 * @author Mirette Amin Danial     20190570
 * @author Mostafa Mahmoud Anwar   20190544
 * Created on 6/6/2021
 * @version 1.51
 */

package PlaygroundComponents;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Scanner;

public class Playground {
    String name;
    String address;
    public static int id;
    public int playgroundOwnerId;
    ArrayList<Slot> slotList = new ArrayList <Slot>();
    Scanner scan = new Scanner (System.in);
    public double cost;

    public Status state;

    public Playground(int pId) {
        id++;
        playgroundOwnerId = pId;
        System.out.print("Playground's Name: ");
        name = scan.next();
        System.out.print("Playground's Address: ");
        address = scan.next();
        System.out.print("Playground's Cost: ");
        cost = scan.nextDouble();
        state = Status.PENDING;
    }

    public void run () {


    }

    @Override
    public String toString(){
        return  String.format("Playground's Name: "+name+"\nAddress: " + address + "\nPlayground's ID: " + id + "\nPlayground Owner ID: " + playgroundOwnerId);
    }



    // m7tagha fel database :DD
    public void addSlot(Slot slot) {
        slotList.add(slot);
    }

    public void display(){

    }
}
