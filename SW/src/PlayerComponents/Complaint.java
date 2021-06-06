package PlayerComponents;
import System.*;
import PlaygroundComponents.*;

import java.util.Scanner;

public class Complaint {
    int playerId;
    int playgroundId;
    String text;

    public Complaint(Player player){
        Scanner scan = new Scanner (System.in);
        playerId = player.id;
        System.out.println("Please enter the playground ID:");
        playgroundId = scan.nextInt();
        System.out.println("Please enter all the details of your complaint:");
        text = scan.nextLine();
        text = scan.nextLine();
    }
}
