/**
 * @author Omar Khaled Al Haj      20190351
 * @author Mirette Amin Danial     20190570
 * @author Mostafa Mahmoud Anwar   20190544
 * Created on 6/6/2021
 * @version 1.2
 */

package PlayerComponents;
import System.*;
import PlaygroundComponents.*;

import java.util.Scanner;

public class Complaint {
    int playerId;
    int playgroundId;
    String text;

    /**
     * The constructor is called upon the creation of a complaint, it prompts the user to enter all the details of his complaint
     * @param player
     */
    public Complaint(Player player){
        Scanner scan = new Scanner (System.in);
        playerId = player.id;
        System.out.println("Please enter the playground ID:");
        playgroundId = scan.nextInt();
        System.out.println("Please enter all the details of your complaint:");
        scan.skip("\\R");
        text = scan.nextLine();
    }
}
