package PlayerComponents;

import PlaygroundComponents.Booking;
import PlaygroundComponents.Playground;
import PlaygroundComponents.Status;
import System.AccountInfo;
import System.Database;
import System.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Player implements User {
    Scanner scan = new Scanner(System.in);
    public AccountInfo account;
    public int id;
    Database db = new Database();
    FavoriteTeam favoriteTeam;

    public Player(int nxtId, AccountInfo accountInfo) {
        account = new AccountInfo(accountInfo);
        id = nxtId;
    }

    public void bookPlayground() {
        System.out.println("---Book playground---");
        System.out.println("---Available Playgrounds---");
        db.displayPlaygroundByState(Status.ACCEPTED);
        System.out.print("Chosen Playground's ID:");
        int playgroundID = scan.nextInt();
        System.out.print("Chosen Slot's ID:");
        int slotID = scan.nextInt();
        ArrayList<Playground> temp = db.getPlaygroundsList();
        Booking booking = new Booking(this.id,playgroundID,slotID);
        db.addBooking(booking);
    }

    public void createFavoriteTeam() {
        System.out.println("---Create favorite team---");
        favoriteTeam = new FavoriteTeam(this);
        db.addFavoriteTeam(favoriteTeam);
    }

    public void viewFavoriteTeam() {
        System.out.println("---Viewing all team info---");
        System.out.println(favoriteTeam);
    }

    public void joinTeam() {
        Scanner scan = new Scanner(System.in);
        System.out.println("---Join team---");
        String teamName = scan.nextLine();
        boolean added = db.addPlayerToTeam(teamName, this);
        if (added) {
            System.out.println("Player joined successfully");
        }
    }

    public int displayMenu() {
        System.out.println("---Player---");
        System.out.println("---Choose an option---");
        System.out.println("1- Book a playground");
        System.out.println("2- Create favorite team");
        System.out.println("3- View favorite team");
        System.out.println("4- Join team");
        System.out.println("5- File a complaint");
        System.out.println("6- View bookings");
        System.out.println("7- View my requests");
        System.out.println("8- Log out");
        while (true) {
            int n = scan.nextInt();
            if (n < 1 || n > 6) {
                System.out.println("Invalid Input, enter a number between 1 and 5");
            } else {
                return n;
            }
        }
    }

    public void fileComplaint() {
        System.out.println("---File a complaint---");
        Complaint complaint = new Complaint(this);
        System.out.println("Complaint filed successfully");
        db.addComplaint(complaint);
    }

    public void viewBookings() {
        System.out.println("---View Bookings---");
        ArrayList<Booking> temp = db.getBookingsList();
        for (int i = 0; i < temp.size(); ++i) {
            if (temp.get(i).playerId == id && temp.get(i).state != Status.PENDING) {
                System.out.println(temp.get(i));
            }
        }
    }

    public void viewRequests() {
        System.out.println("---View Requests---");
        ArrayList<Booking> temp = db.getBookingsList();
        for (int i = 0; i < temp.size(); ++i) {
            if (temp.get(i).playerId == id && temp.get(i).state == Status.PENDING) {
                System.out.println(temp.get(i));
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            int n = displayMenu();
            if (n == 1) {
                bookPlayground();
            } else if (n == 2) {
                createFavoriteTeam();
            } else if (n == 3) {
                viewFavoriteTeam();
            } else if (n == 4) {
                joinTeam();
            } else if (n == 5) {
                fileComplaint();
            } else if (n == 6) {
                viewBookings();
            } else if (n == 7) {
                viewRequests();
            } else if (n == 8) {
                break;
            }
        }
    }
}
