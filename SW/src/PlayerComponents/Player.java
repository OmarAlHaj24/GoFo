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
    public double eWallet;

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
        System.out.println("8- View profile info");
        System.out.println("9- Log out");
        return inputRange(1, 9);
    }

    public void fileComplaint() {
        System.out.println("---File a complaint---");
        Complaint complaint = new Complaint(this);
        System.out.println("Complaint filed successfully");
        db.addComplaint(complaint);
    }

    public void viewBookings() {
        System.out.println("---View bookings---");
        ArrayList <Booking> temp = db.getBookingsList();
        for (int i = 0; i < temp.size(); ++i) {
            if (temp.get(i).playerId == id && temp.get(i).state != Status.PENDING) {
                System.out.println(temp.get(i));
            }
        }
    }

    public void viewRequests() {
        System.out.println("---View requests---");
        ArrayList <Booking> temp = db.getBookingsList();
        for (int i = 0; i < temp.size(); ++i) {
            if (temp.get(i).playerId == id && temp.get(i).state == Status.PENDING) {
                System.out.println(temp.get(i));
            }
        }
    }

    public int inputRange (int l, int r) {
        int in;
        while (true) {
            in = scan.nextInt();
            if (in < l || in > r) {
                System.out.println("Invalid Input, enter number between " + l + " and " + r);
                continue;
            }
            return in;
        }
    }

    public void executeProfileOption(int in) {
        String s;
        switch (in) {
            case 1 :
                System.out.print("Enter the new username: ");
                s = scan.next();
                db.verifyUsername(s);
                account.username = s;
                break;
            case 2 :
                System.out.print("Enter the new password: ");
                s = scan.next();
                account.password = s;
                break;
            case 3 :
                System.out.print("Enter the new email: ");
                s = scan.next();
                db.verifyEmail(s);
                account.email = s;
                break;
            case 4 :
                System.out.print("Enter the new address: ");
                scan.skip("\\R");
                s = scan.nextLine();
                account.address = s;
                break;
            case 5 :
                System.out.print("Enter the new phone number: ");
                s = scan.next();
                account.phone = s;
                break;
            case 6 :
                System.out.println("eWallet Balance: " + this.eWallet);
                break;
        }
    }

    public void displayProfileOptions() {
        System.out.println("1- Change username");
        System.out.println("2- Change password");
        System.out.println("3- Change email");
        System.out.println("4- Change address");
        System.out.println("5- Change phone");
        System.out.println("6- View eWallet");
        System.out.println("7- Return to homepage");
    }

    public void executeProfile() {
        int in = -1;
        while (in != 7) {
            System.out.println("--- Player info ---");
            System.out.println(account);
            System.out.println("ID: " + id);
            displayProfileOptions();
            in = inputRange(1, 7);
            if (in != 7) {
                executeProfileOption(in);
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
                executeProfile();
            }else{
                break;
            }
        }
    }
}
