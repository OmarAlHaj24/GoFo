package PlaygroundComponents;

import System.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * PlaygroundOwner class will contain all the functions that are exclusive to the playground owner such as registering a new playground as well as all of his account’s info.
 *
 * @author Mirette Amin Danial     20190570
 * @version 1.0
 * @since 6 June 2021
 */

public class PlaygroundOwner implements User {
    /**
     * Account info object to store playgroundOwner's information
     */
    public AccountInfo account;
    /**
     * Playground Owner's ID
     */
    public int id;
    /**
     * eWallet balance
     */
    public double eWallet;
    /**
     * Playgrounds' list registered by this playground owner
     */
    ArrayList<Playground> playgroundsList = new ArrayList<Playground>();

    Database db = new Database();
    Scanner scan = new Scanner(System.in);

    /**
     * Parametrized constructor
     *
     * @param pID
     * @param acc
     */
    public PlaygroundOwner(int pID, AccountInfo acc) {
        id = pID;
        account = acc;
    }

    /**
     * Function that displays administrator's user interface.
     *
     * @return choice
     */
    public int displayMenu() {
        System.out.println("---Playground Owner---");
        System.out.println("1- Register a playground");
        System.out.println("2- Delete playground");
        System.out.println("3- View bookings");
        System.out.println("4- View requests");
        System.out.println("5- View my playgrounds");
        System.out.println("6- Profile");
        System.out.println("7- Edit Playground");
        System.out.println("8- Delete Account");
        System.out.println("9- Log out");
        System.out.print("Your Choice number: ");
        while (true) {
            int n = scan.nextInt();
            if (n < 1 || n > 9) {
                System.out.println("Invalid Input, enter a number between 1 and 9");
            } else {
                return n;
            }
        }
    }

    /**
     * Function that enables the playground owner to register a new playground.
     */
    public void registerPlayground() {
        System.out.println("---Register a playground---");
        Playground playground = new Playground(this.id);
        playgroundsList.add(playground);
        db.addPlayground(playground);
    }

    /**
     * Function that enables the playground owner to delete playground.
     */
    public void deletePlayground() {
        System.out.println("---Delete playground---");
        System.out.print("Playground's ID: ");
        int pId = scan.nextInt();
        boolean flag = false;
        for (int i = 0; i < playgroundsList.size(); ++i)
            if (playgroundsList.get(i).id == pId) {
                playgroundsList.remove(i);
                flag = true;
                break;
            }
        if (flag == true) db.deletePlayground(pId);
        else System.out.println("Playground not found");
    }

    /**
     * Function that enables the playground owner to view his playgrounds' bookings.
     */
    public void viewBookings() {
        System.out.println("---View Bookings---");
        ArrayList<Booking> temp = db.getBookingsList();
        for (int i = 0; i < temp.size(); ++i) {
            if (temp.get(i).playgroundOwnerId == id && temp.get(i).state != Status.PENDING) {
                System.out.println(temp.get(i));
                System.out.println("State: " + temp.get(i).state);
            }
        }
    }

    /**
     * Function that enables the playground owner to view player's requests to book a playground.
     */
    public void viewRequests() {
        System.out.println("---View Requests---");
        ArrayList<Booking> temp = db.getBookingsList();
        for (int i = 0; i < temp.size(); ++i) {
            if (temp.get(i).playgroundId == id && temp.get(i).state == Status.PENDING) {
                System.out.println(temp.get(i));
            }
        }
        int choice = 0;
        while (true) {
            System.out.println("1. Accept/2. Deny/3. Return");
            choice = scan.nextInt();
            if (choice == 3) {
                break;
            }
            System.out.print("Input Booking's ID: ");
            int id = scan.nextInt();

            if (choice == 1) {
                for (int i = 0; i < temp.size(); ++i) {
                    if (temp.get(i).id == id && temp.get(i).state == Status.PENDING) {
                        boolean flag = db.checkeWalletBalance(temp.get(i).playerId, temp.get(i).playgroundId);
                        if (flag) {
                            temp.get(i).updateState(Status.ACCEPTED);
                            db.updateWalletBalance(temp.get(i).playerId, (-1) * (temp.get(i).cost));
                            db.updateWalletBalance(id, (temp.get(i).cost));
                        } else {
                            temp.get(i).updateState(Status.REJECTED);
                        }
                        break;
                    }
                }
            } else if (choice == 2) {
                for (int i = 0; i < temp.size(); ++i) {
                    if (temp.get(i).id == id && temp.get(i).state == Status.PENDING) {
                        temp.get(i).updateState(Status.REJECTED);
                        break;
                    }
                }
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
     * Function that enables the playground owner to edit his profile.
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
        }
    }

    /**
     * Function that displays playground owner's profile.
     */
    public void displayProfileOptions() {
        System.out.println("1- Change username");
        System.out.println("2- Change password");
        System.out.println("3- Change email");
        System.out.println("4- Change address");
        System.out.println("5- Change phone");
        System.out.println("6- View eWallet");
        System.out.println("7- Return to homepage");
    }

    /**
     * The function executeProfile shows the user all of his account's info
     */
    public void executeProfile() {
        int in = -1;
        while (in != 6) {
            System.out.println("--- Playground Owner Info ---");
            System.out.println(account);
            System.out.println("ID: " + id);
            displayProfileOptions();
            in = inputRange(1, 7);
            if (in != 7) {
                executeProfileOption(in);
            }
        }
    }

    /**
     * Function that enables the playground owner to edit his playgrounds by their IDs.
     */
    public void editPlayground() {
        System.out.print("Input Playground's ID: ");
        int id = scan.nextInt();
        if (!db.runPlayground(id, this.id)) {
            System.out.println("Playground not found");
        }
    }

    /**
     * Interface Function
     */
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
            } else if (n == 5) {
                System.out.println("--- Playgrounds List ---");
                db.viewPlaygroundsByOwnerId(id);
            } else if (n == 6) {
                executeProfile();
            } else if (n == 7) {
                editPlayground();
            } else if (n == 8) {
                String s;
                System.out.print("Enter 'delete' to confirm: ");
                s = scan.next();
                if (s.equalsIgnoreCase("delete")) {
                    db.deletePlaygroundOwner(id);
                    break;
                } else {
                    System.out.println("Failed to delete the account");
                }
            } else {
                break;
            }
        }
    }
}