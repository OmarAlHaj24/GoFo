package PlayerComponents;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * FavoriteTeam class that represents the created team by the player named favorite team to invite those players to a specific playground.
 *
 * @author Omar Khaled Al Haj      20190351
 * @version 1.0
 * @since 6 June 2021
 */

public class FavoriteTeam {
    public String name;
    public ArrayList<Player> playerList = new ArrayList<Player>();
    public int playgroundId;
    public String text;

    /**
     * The constructor is called upon the creation of a favorite team, it prompts the user to enter all the details of his favorite team
     *
     * @param player
     */
    public FavoriteTeam(Player player) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the name of your favorite team:");
        name = scan.nextLine();
        System.out.println("Please enter the ID of the playground:");
        playgroundId = scan.nextInt();
        System.out.println("Enter any extra details about the team:");
        scan.skip("\\R");
        text = scan.nextLine();
        System.out.println("Team created successfully");
        playerList.add(player);
    }

    /**
     * The function addPlayer allows a player to be added to another player's favorite team
     *
     * @param player
     */
    public void addPlayer(Player player) {
        playerList.add(player);
    }

    /**
     * toString function is overridden to be able to display all the information of a team
     *
     * @return all the details of a team
     */
    @Override
    public String toString() {
        return "Team name: " + name + "\n" + "Playground ID: " + playgroundId + "\n" + "Additional info: " + text + "\n";
    }
}
