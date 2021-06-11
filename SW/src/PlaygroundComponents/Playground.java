package PlaygroundComponents;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Playground class will contain all the details of a playground such as the name and the ID.
 *
 * @author Omar Khaled Al Haj      20190351
 * @author Mirette Amin Danial     20190570
 * @author Mostafa Mahmoud Anwar   20190544
 * @version 1.0
 * @since 6 June 2021
 */

public class Playground {
    /**
     * Static variable to counts Playground's objects.
     */
    public static int cnt = 0;

    public String name;
    public String address;
    public int id;
    public int playgroundOwnerId;
    public double cost;

    /**
     * Playground status.
     */
    public Status state;
    /**
     * ArrayList to store playground's slots.
     */
    public ArrayList<Slot> slotList = new ArrayList<Slot>();

    Scanner scan = new Scanner(System.in);

    /**
     * Parameterized constructor that runs playground's UI.
     *
     * @param pId
     */
    public Playground(int pId) {
        cnt++;
        id = cnt;
        playgroundOwnerId = pId;
        System.out.print("Playground's Name: ");
        name = scan.next();
        System.out.print("Playground's Address: ");
        address = scan.next();
        System.out.print("Playground's Cost: ");
        cost = scan.nextDouble();
        state = Status.PENDING;
    }

    /**
     * The function inputRange takes the accepted range of inputs and validates if the user input is valid or not
     *
     * @param l the left boundary of the values
     * @param r the right boundary of the values
     * @return the user valid choice
     */
    int inputRange(int l, int r) {
        int in = 0;
        while (true) {
            in = scan.nextInt();
            if (in < l || in > r) {
                System.out.println("Invalid Input, enter number between " + l + " and " + r);
                continue;
            }
            return in;
        }
    }

    /**
     * Function that executes adding slots.
     */
    void executeAddSlots() {
        TimeDate b = new TimeDate();
        TimeDate e = new TimeDate();
        Slot s = new Slot(b, e);
        slotList.add(s);
    }

    /**
     * Interface Function
     */
    public void run() {
        System.out.println("1- Add Slots");
        int in = inputRange(1, 1);
        switch (in) {
            case 1:
                executeAddSlots();
                break;
        }

    }

    /**
     * Overriding toString Function to display playground's information.
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("Playground's Name: " + name + "\nAddress: " + address + "\nPlayground's ID: " + id + "\nPlayground Owner ID: " + playgroundOwnerId + "\nCost: " + cost);
    }

    /**
     * Function that adds a new slot in slotsList.
     *
     * @param slot
     */
    public void addSlot(Slot slot) {
        slotList.add(slot);
    }
}