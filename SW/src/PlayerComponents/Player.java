package PlayerComponents;
import System.*;
import PlaygroundComponents.*;

import java.util.Scanner;

public class Player implements User {
    Database db = new Database();
    FavoriteTeam favoriteTeam;
    public AccountInfo account;
    public int id;
    public Player (int nxtId, AccountInfo accountInfo) {
        account = new AccountInfo(accountInfo);
        id = nxtId;
    }

    public void bookPlayground(){
        System.out.println("---Book playground---");
        Booking booking = new Booking(this);
        db.addBooking(booking);
    }

    public void createFavoriteTeam(){
        System.out.println("---Create favorite team---");
        favoriteTeam = new FavoriteTeam(this);
        db.addFavoriteTeam(favoriteTeam);
    }

    public void viewFavoriteTeam(){
        System.out.println("---Viewing all team info---");
        System.out.println(favoriteTeam);
    }

    public void joinTeam(){
        Scanner scan = new Scanner (System.in);
        System.out.println("---Join team---");
        String teamName = scan.nextLine();
        boolean added = db.addPlayerToTeam(teamName, this);
        if(added){
            System.out.println("Player joined successfully");
        }
    }

    public int displayMenu(){
        Scanner scan = new Scanner (System.in);
        System.out.println("---Player---");
        System.out.println("---Choose an option---");
        System.out.println("1- Book a playground");
        System.out.println("2- Create favorite team");
        System.out.println("3- View favorite team");
        System.out.println("4- Join team");
        System.out.println("5- File a complaint");
        System.out.println("6- Log out");
        while(true){
            int n = scan.nextInt();
            if(n < 1 || n > 6){
                System.out.println("Invalid Input, enter a number between 1 and 5");
            }else{
                return n;
            }
        }
    }

    public void fileComplaint(){
        System.out.println("---File a complaint---");
        Complaint complaint = new Complaint(this);
        System.out.println("Complaint filed successfully");
        db.addComplaint(complaint);
    }

    @Override
    public void run() {
        while(true){
            int n = displayMenu();
            if(n == 1){
                bookPlayground();
            }else if(n == 2){
                createFavoriteTeam();
            }else if(n == 3){
                viewFavoriteTeam();
            }else if(n == 4){
                joinTeam();
            }else if(n == 5){
                fileComplaint();
            }else if(n == 6){
                break;
            }
        }
    }
}
