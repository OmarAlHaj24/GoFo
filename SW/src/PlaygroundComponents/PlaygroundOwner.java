package PlaygroundComponents;

import System.AccountInfo;
import System.Database;
import System.User;

import java.util.ArrayList;
import java.util.Scanner;

public class PlaygroundOwner implements User {

    public AccountInfo account;
    public int id;
    Database db = new Database();
    ArrayList<Playground> playgroundsList = new ArrayList<Playground>();
    Scanner scan = new Scanner(System.in);

    public PlaygroundOwner(int nxtId, AccountInfo accountInfo) {
        account = new AccountInfo(accountInfo);
        id = nxtId;
    }

    public int displayMenu() {
        System.out.println("---Playground Owner---");
        System.out.println("1- Register a playground");
        System.out.println("2- Delete playground");
        System.out.println("3- View bookings");
        System.out.println("4- View requests");
        System.out.println("5- View my playgrounds");
        System.out.println("6- Log out");
        System.out.print("Your Choice number: ");
        while (true) {
            int n = scan.nextInt();
            if (n < 1 || n > 6) {
                System.out.println("Invalid Input, enter a number between 1 and 6");
            } else {
                return n;
            }
        }
    }

    public void registerPlayground() {
        System.out.println("---Register a playground---");
        Playground playground = new Playground(this.id);
        playgroundsList.add(playground);
        db.addPlayground(playground);
    }

    public void deletePlayground() {
        System.out.println("---Delete playground---");
        System.out.print("Playground's ID: ");
        int pId = scan.nextInt();
        boolean flag = false;
        for (int i = 0; i < playgroundsList.size(); ++i) {
            if (playgroundsList.get(i).id == pId) {
                playgroundsList.remove(i);
                flag = true;
                break;
            }
        }
        if (flag == true)
            db.deletePlayground(pId);
        else
            System.out.println("Playground not found");
    }

    public void viewBookings() {
        System.out.println("---View Bookings---");
        ArrayList<Booking> temp = db.getBookingsList();
        for (int i = 0; i < temp.size(); ++i) {
            if (playgroundsList.contains(temp.get(i).playgroundId) && temp.get(i).state != Status.PENDING) {
                System.out.println(temp.get(i));
            }
        }
    }

    public void viewRequests() {
       /* System.out.println("---View Requests---");
        ArrayList<Booking> temp = db.getBookingsList();
        for (int i = 0; i < temp.size(); ++i) {
            if (temp.get(i).playgroundId == id && temp.get(i).state == Status.PENDING) {
                System.out.println(temp.get(i));
            }
        }*/
    }

    @Override
    public void run() {
        while (true) {
            int n = displayMenu();
            if (n == 1) {
                registerPlayground();
            } else if (n == 2) {
                deletePlayground();
            } else if (n == 3) {
                viewBookings();
            } else if (n == 4) {
                viewRequests();
            } else {
                break;
            }
        }
    }
}
