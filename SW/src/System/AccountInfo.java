package System;

import java.util.Scanner;

/**
 * AccountInfo class will contain all the data of all the users that are registered to the system whether it be a player or a playground owner.
 *
 * @author Mostafa Mahmoud Anwar   20190544
 * @version 1.0
 * @since 4 June 2021
 */


public class AccountInfo {
    public String username;
    public String password;
    public String name;
    public String email;
    public String address;
    public String phone;
    Scanner scan = new Scanner(System.in);

    /**
     * Default Constructor that run UI so the user enters his information.
     */
    public AccountInfo() {
        System.out.print("Enter username: ");
        username = scan.next();
        System.out.print("Enter password: ");
        password = scan.next();
        scan.skip("\\R");
        System.out.print("Enter full name: ");
        name = scan.nextLine();
        System.out.print("Enter e-mail: ");
        email = scan.next();
        System.out.print("Enter address: ");
        scan.skip("\\R");
        address = scan.nextLine();
        System.out.print("Enter phone number: ");
        phone = scan.next();
    }

    /**
     * Copy Constructor
     * @param account
     */
    public AccountInfo(AccountInfo account) {
        username = account.username;
        password = account.password;
        name = account.name;
        email = account.email;
        address = account.address;
        phone = account.phone;
    }

    /**
     * Function that checks if the username and email are unique.
     * @param accInfo
     * @return
     */
    public boolean checkUsernameAndEmail(AccountInfo accInfo) {
        return (!username.equals(accInfo.username) && !email.equals(accInfo.email));
    }

    /**
     * Function that checks if the username and email are correct.
     * @param username
     * @param password
     * @return
     */
    public boolean checkUsernameAndPassword(String username, String password) {
        return (this.username.equalsIgnoreCase(username) && this.password.equals(password));
    }

    /**
     * Overriding toString() to print user's account info.
     * @return
     */
    @Override
    public String toString() {
        return "Username: " + username + "\n" +
                "Password: " + password + "\n" +
                "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Address: " + address + "\n" +
                "Phone: " + phone;
    }
}
