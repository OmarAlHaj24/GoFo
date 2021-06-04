import java.util.Scanner;

public class AccountInfo {
    Scanner scan = new Scanner (System.in);
    String username;
    String password;
    String name;
    String email;
    String address;
    String phone;

    public AccountInfo() {
        System.out.print("Enter username: ");
        username = scan.next();
        System.out.print("Enter password: ");
        password = scan.next();
        scan.skip("\\R");
        System.out.print("Enter full name: ");
        username = scan.nextLine();
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
        return (!name.equals(accInfo.name) && !email.equals(accInfo.email));
    }

    public boolean checkUsernameAndPassword (String username, String password) {
        return (this.username.equals(username) && this.password.equals(password));
    }
}
