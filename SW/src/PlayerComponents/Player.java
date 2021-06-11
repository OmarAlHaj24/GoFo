package PlayerComponents;

import PlaygroundComponents.Booking;
import PlaygroundComponents.Playground;
import PlaygroundComponents.Status;
import System.AccountInfo;
import System.Database;
import System.User;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Player class includes all the players data, his team,
 * As well as all the functions that has to do with the player such as viewing his bookings,
 * booking a new playground, registering for a team and so on.
 *
 * @author Omar Khaled Al Haj      20190351
 * @version 1.0
 * @since 6 June 2021
 */

public class Player implements User {
    /**
     * Account info object to store player's information
     */
    public AccountInfo account;
    /**
     * Player's ID
     */
    public int id;
    /**
     * eWallet balance
     */
    public double eWallet;
    /**
     * Favorite team created by the player
     */
    FavoriteTeam favoriteTeam;

    Scanner scan = new Scanner(System.in);
    Database db = new Database();

    /**
     * Parametrized Constructor that sets the player's id and account info.
     *
     * @param nxtId
     * @param accountInfo
     */
    public Player(int nxtId, AccountInfo accountInfo) {
        account = new AccountInfo(accountInfo);
        id = nxtId;
    }

    /**
     * The bookPlayground function allows the player to book a playground
     */
    public void bookPlayground() {
        System.out.println("---Book playground---");
        System.out.println("1- Filter Playgrounds by address\n2- Filter Playgrounds near you\n3- View All Playgrounds");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Enter Address: ");
                String address = scan.next();
                System.out.println("---Available Playgrounds---");
                db.displayPlaygroundFilteredByAddress(address);
                break;

            case 2:
                System.out.println("---Available Playgrounds---");
                db.displayPlaygroundFilteredByAddress(this.account.address);
                break;
            case 3:
                System.out.println("---Available Playgrounds---");
                db.displayPlaygroundFilteredByAddress("all");
                break;
        }

        System.out.print("Chosen Playground's ID:");
        int playgroundID = scan.nextInt();
        System.out.print("Chosen Slot's ID:");
        int slotID = scan.nextInt();

        if (db.checkSlot(playgroundID, slotID)) {
            ArrayList<Playground> temp = db.getPlaygroundsList();
            Booking booking = new Booking(this.id, playgroundID, slotID);
            db.addBooking(booking);
        } else {
            System.out.println("Wrong Playground's ID or Slot's ID");
        }
    }

    /**
     * The function createFavoriteTeam allows the user to create a favorite team
     */
    public void createFavoriteTeam() {
        System.out.println("---Create favorite team---");
        favoriteTeam = new FavoriteTeam(this);
        db.addFavoriteTeam(favoriteTeam);
    }

    /**
     * The function viewFavoriteTeam allows the user to view all the details of his favorite team
     */
    public void viewFavoriteTeam() {
        System.out.println("---Viewing all team info---");
        System.out.println(favoriteTeam);
    }

    /**
     * The function join team allows the user to join the favorite team of another player
     */
    public void joinTeam() {
        Scanner scan = new Scanner(System.in);
        System.out.println("---Join team---");
        String teamName = scan.nextLine();
        boolean added = db.addPlayerToTeam(teamName, this);
        if (added) {
            System.out.println("Player joined successfully");
        }
    }

    /**
     * The function displayMenu shows the player all of his choices and asks him to choose one
     *
     * @return The function returns the choice that the player chooses from the main menu
     */
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

    /**
     * The function fileComplaint allows the user to file a complaint against a playground
     */
    public void fileComplaint() {
        System.out.println("---File a complaint---");
        Complaint complaint = new Complaint(this);
        System.out.println("Complaint filed successfully");
        db.addComplaint(complaint);
    }

    /**
     * The function viewBookings shows the player all of his Accepted or rejected bookings
     */
    public void viewBookings() {
        System.out.println("---View bookings---");
        ArrayList<Booking> temp = db.getBookingsList();
        for (int i = 0; i < temp.size(); ++i) {
            if (temp.get(i).playerId == id && temp.get(i).state != Status.PENDING) {
                System.out.println(temp.get(i));
            }
        }
    }

    /**
     * The function viewRequests shows the player all of his pending bookings
     */
    public void viewRequests() {
        System.out.println("---View requests---");
        ArrayList<Booking> temp = db.getBookingsList();
        for (int i = 0; i < temp.size(); ++i) {
            if (temp.get(i).playerId == id && temp.get(i).state == Status.PENDING) {
                System.out.println(temp.get(i));
            }
        }
    }

    /**
     * The function inputRange takes the accepted range of inputs and validates if the user input is valid or not
     *
     * @param l the left boundary of the values
     * @param r the right boundary of the values
     * @return the user valid choice
     */
    public int inputRange(int l, int r) {
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

    /**
     * This function based on the user choice in the displayProfileOptions function will be asked to enter the new details he would like to add
     *
     * @param in
     */
    public void executeProfileOption(int in) {
        String s;
        switch (in) {
            case 1:
                System.out.print("Enter the new username: ");
                s = scan.next();
                db.verifyUsername(s);
                account.username = s;
                break;
            case 2:
                System.out.print("Enter the new password: ");
                s = scan.next();
                account.password = s;
                break;
            case 3:
                System.out.print("Enter the new email: ");
                s = scan.next();
                db.verifyEmail(s);
                account.email = s;
                break;
            case 4:
                System.out.print("Enter the new address: ");
                scan.skip("\\R");
                s = scan.nextLine();
                account.address = s;
                break;
            case 5:
                System.out.print("Enter the new phone number: ");
                s = scan.next();
                account.phone = s;
                break;
            case 6:
                System.out.println("eWallet Balance: " + this.eWallet);
                break;
            case 7:
                System.out.println("Enter the amount of credits to add: ");
                eWallet += scan.nextInt();
        }
    }

    /**
     * The function displayProfileOptions only shows the users the choices he has got regarding his profile
     */
    public void displayProfileOptions() {
        System.out.println("1- Change username");
        System.out.println("2- Change password");
        System.out.println("3- Change email");
        System.out.println("4- Change address");
        System.out.println("5- Change phone");
        System.out.println("6- View eWallet");
        System.out.println("7- Add credits");
        System.out.println("8- Return to homepage");
    }

    /**
     * The function executeProfile shows the user all of his account's info
     */
    public void executeProfile() {
        int in = -1;
        while (in != 8) {
            System.out.println("--- Player info ---");
            System.out.println(account);
            System.out.println("ID: " + id);
            displayProfileOptions();
            in = inputRange(1, 8);
            if (in != 8) {
                executeProfileOption(in);
            }
        }
    }

    /**
     * The function run acts as the main function of the player, it helps the player navigate through all of his options
     */
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
            } else {
                break;
            }
        }
    }
}