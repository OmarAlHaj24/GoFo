package PlayerComponents;

import java.util.Scanner;

/**
 * Complaint class will contain all the attributes and functions required by a player to file a complaint against any of the playgrounds he booked before.
 *
 * @author Omar Khaled Al Haj      20190351
 * @version 1.0
 * @since 6 June 2021
 */

public class Complaint {
    /**
     * Player who complained
     */
    int playerId;
    /**
     * Playground the player complained
     */
    int playgroundId;
    /**
     * Complaint content
     */
    String text;

    /**
     * The constructor is called upon the creation of a complaint, it prompts the user to enter all the details of his complaint
     *
     * @param player
     */
    public Complaint(Player player) {
        Scanner scan = new Scanner(System.in);
        playerId = player.id;
        System.out.println("Please enter the playground ID:");
        playgroundId = scan.nextInt();
        System.out.println("Please enter all the details of your complaint:");
        scan.skip("\\R");
        text = scan.nextLine();
    }
}
