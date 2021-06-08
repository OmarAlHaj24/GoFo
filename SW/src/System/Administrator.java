/**
 * @author Omar Khaled Al Haj      20190351
 * @author Mirette Amin Danial     20190570
 * @author Mostafa Mahmoud Anwar   20190544
 * Created on 6/6/2021
 * @version 1.0
 */

package System;
import PlaygroundComponents.*;
import java.util.Scanner;

public class Administrator {
    Scanner scan = new Scanner(System.in);
    int id = 0;
    public AccountInfo account;
    Database db = new Database();

    int inputRange (int l, int r) {
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

    void executeDeleteComplaint() {
        System.out.println("Enter complaint ID");
        int id = scan.nextInt();
        if (!db.deleteComplaint(id)) {
            System.out.println("Complaint not found");
        }
    }

    void executeViewComplaints() {
        if (!db.displayComplaints()) {
            System.out.println("No complaints found");
        }
    }

    Status getState() {
        System.out.println("1- Update to accepted");
        System.out.println("2- Update to denied");
        System.out.println("3- Update to suspended");
        System.out.println("4- Return to home page");
        int in = inputRange(1, 4);
        if (in == 1) {
            return Status.ACCEPTED;
        } else if (in == 2) {
            return Status.REJECTED;
        } else if (in == 3) {
            return Status.SUSPENDED;
        }
        return Status.all;
    }

    void executeEditPlayground() {
        System.out.println("Enter playground's ID: ");
        int id = scan.nextInt();
        System.out.println("1- Update playground state");
        System.out.println("2- Delete playground");
        System.out.println("3- Return to home page");
        int in = inputRange(1, 3);
        if (in == 1) {
            Status s = getState();
            if (s != s.all) {
              db.updatePlaygroundState(id, s);
            }
        } else if (in == 2) {
            db.deletePlayground(id);
        }
    }

    void displayPlaygroundsMenu() {
        System.out.println("=== Playgrounds Menu ===");
        System.out.println("1- View all playgrounds");
        System.out.println("2- View pending playgrounds");
        System.out.println("3- View accepted playgrounds");
        System.out.println("4- View denied playgrounds");
        System.out.println("5- Return to home page");
    }

    void executeViewPlaygrounds() {
        Status s;
        displayPlaygroundsMenu();
        int in = inputRange(1, 5);
        if (in == 5) return;
        if (in == 1) {
            s = Status.all;
        } else if (in == 2) {
            s = Status.PENDING;
        } else if (in == 3) {
            s = Status.ACCEPTED;
        } else {
            s = Status.REJECTED;
        }
        if (!db.displayPlaygroundByState(s)) {
            System.out.println("No playgrounds found");
        }
    }

    void executeProfileOption(int in) {
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

        }
    }

    void displayProfileOptions() {
        System.out.println("1- Change username");
        System.out.println("2- Change password");
        System.out.println("3- Change email");
        System.out.println("4- Change address");
        System.out.println("5- Change phone: ");
        System.out.println("6- Return to homepage");
    }

    void executeProfile() {
        int in = -1;
        while (in != 6) {
            System.out.println("--- Administrator info ---");
            System.out.println(account);
            System.out.println("ID: " + id);
            displayProfileOptions();
            in = inputRange(1, 6);
            if (in != 6) {
                executeProfileOption(in);
            }
        }
    }

    void executeComplaints() {
        int in;
        do {
            System.out.println("--- Complaint options ---");
            System.out.println("1- View complaints");
            System.out.println("2- Delete complaint");
            System.out.println("3- Return to homepage");
            switch (in = inputRange(1, 3)) {
                case 1 -> executeViewComplaints();
                case 2 -> executeDeleteComplaint();
            }
        } while (in != 3);

    }

    void executePlaygrounds() {
        int in;
        do {
            System.out.println("--- Playground options ---");
            System.out.println("1- View playgrounds");
            System.out.println("2- Edit playground");
            System.out.println("3- Return to homepage");
            switch (in = inputRange(1, 3)) {
                case 1 -> executeViewPlaygrounds();
                case 2 -> executeEditPlayground();
            }
        } while (in != 3);

    }

    void displayMenu() {
        System.out.println("=== Administrator Menu ===");
        System.out.println("1- Playgrounds");
        System.out.println("2- Complaints");
        System.out.println("3- Profile");
        System.out.println("4- Logout");
    }

    int executeMenu() {
        displayMenu();
        return inputRange(1, 4);
    }

    public void run() {
        int in;
        do {
            switch (in = executeMenu()) {
                case 1 -> executePlaygrounds();
                case 2 -> executeComplaints();
                case 3 -> executeProfile();
            }
        } while (in != 4);
    }


}
