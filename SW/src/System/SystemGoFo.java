/**
 * @author Omar Khaled Al Haj      20190351
 * @author Mirette Amin Danial     20190570
 * @author Mostafa Mahmoud Anwar   20190544
 * Created on 6/6/2021
 * @version 1.5
 */

package System;
import PlaygroundComponents.*;
import PlayerComponents.*;

import java.util.Scanner;

public class SystemGoFo {
    Scanner scan = new Scanner(System.in);
    Database db = new Database();

    public SystemGoFo () {
        System.out.println("--- Register administrator account ---");
        Database.admin.account = new AccountInfo();
    }

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

    void register() {
        System.out.println("--- Register ---");
        AccountInfo account = new AccountInfo();
        int id = db.verifyAccountInfo(account);
        if (id != -1) {
            System.out.println ("--- Enter Account type ---");
            System.out.println("1- Player");
            System.out.println("2- Playground Owner");
            int in = inputRange(1, 2);
            if (in == 1) {
                db.addPlayer(new Player(id ,account));
                System.out.println("--- Register Succeed ---");
                db.runUser(id);
            } else {
                db.addPlaygroundOwner(new PlaygroundOwner(id, account));
                System.out.println("--- Register Succeed ---");
                db.runUser(id);
            }
        } else {
            System.out.println ("Invalid account details, returning to the main menu");
        }
    }

    void login() {
        System.out.print ("Enter username: ");
        String username = scan.next();
        System.out.print ("Enter password: ");
        String password = scan.next();
        int id = db.checkLogin(username, password);
        if (id == -1) {
            System.out.println ("Invalid username or password, returning to the main menu");
        } else {
            db.runUser(id);
        }
    }

    void displayMenu() {
        System.out.println("=== System Menu ===");
        System.out.println("1- Login");
        System.out.println("2- Register");
        System.out.println("3- Exit");
    }

    int executeMenu() {
        displayMenu();
        return inputRange(1, 3);
    }

    public void run() {
        int in;
        do {
            in = executeMenu();
            if (in == 1) {
                login();
            } else if (in == 2) {
                register();
            }
        } while (in != 3);
    }
}
