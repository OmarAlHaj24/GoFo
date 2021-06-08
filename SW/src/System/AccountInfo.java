/**
 * @author Omar Khaled Al Haj      20190351
 * @author Mirette Amin Danial     20190570
 * @author Mostafa Mahmoud Anwar   20190544
 * Created on 6/6/2021
 * @version 1.0
 */

package System;

import java.util.Scanner;

public class AccountInfo {
    Scanner scan = new Scanner (System.in);
    public String username;
    public String password;
    public String name;
    public String email;
    public String address;
    public String phone;

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

    public AccountInfo (AccountInfo account) {
        username = account.username;
        password = account.password;
        name = account.name;
        email = account.email;
        address = account.address;
        phone = account.phone;
    }

    public boolean compare (AccountInfo accInfo) {
        return (!username.equals(accInfo.username) && !email.equals(accInfo.email));
    }

    public boolean checkUsernameAndPassword (String username, String password) {
        return (this.username.equalsIgnoreCase(username) && this.password.equals(password));
    }

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
