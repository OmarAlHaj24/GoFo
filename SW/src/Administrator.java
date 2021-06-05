import java.util.ArrayList;
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

    String getState() {
        System.out.println("1- Update to accepted");
        System.out.println("2- Update to denied");
        System.out.println("3- Update to suspended");
        System.out.println("4- Return to home page");
        int in = inputRange(1, 4);
        if (in == 1) {
            return "accepted";
        } else if (in == 2) {
            return "denied";
        } else if (in == 3) {
            return "suspended";
        }
        return "noChange";
    }

    void executeEditPlayground() {
        System.out.println("Enter playground's ID: ");
        int id = scan.nextInt();
        System.out.println("1- Update playground state");
        System.out.println("2- Delete playground");
        System.out.println("3- Return to home page");
        int in = inputRange(1, 3);
        if (in == 1) {
            String s = getState();
            if (!s.equals("noChange")) {
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
        displayPlaygroundsMenu();
        int in = inputRange(1, 5);
        if (in == 5) return;
        String filter;
        if (in == 1) {
            filter = "all";
        } else if (in == 2) {
            filter = "pending";
        } else if (in == 3) {
            filter = "accepted";
        } else {
            filter = "denied";
        }
        if (!db.displayPlaygroundByState(filter)) {
            System.out.println("No playgrounds found");
        }
    }

    void displayMenu() {
        System.out.println("=== Administrator Menu ===");
        System.out.println("1- View Playgrounds");
        System.out.println("2- Edit Playground");
        System.out.println("3- View Complaints");
        System.out.println("4- Delete Complaint");
        System.out.println("5- Logout");
    }

    int executeMenu() {
        displayMenu();
        return inputRange(1, 5);
    }

    public void run() {
        int in;
        do {
            in = executeMenu();
            if (in == 1) {
                executeViewPlaygrounds();
            } else if (in == 2) {
                executeEditPlayground();
            } else if (in == 3) {
                executeViewComplaints();
            } else if (in == 4) {
                executeDeleteComplaint();
            }
        } while (in != 5);
    }


}
