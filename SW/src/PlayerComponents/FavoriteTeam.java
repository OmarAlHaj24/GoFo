package PlayerComponents;
import System.*;
/**
 * @author Omar Khaled Al Haj      20190351
 * @author Mirette Amin Danial     20190570
 * @author Mostafa Mahmoud Anwar   20190544
 * Created on 6/6/2021
 * @version 1.1
 */

import java.util.ArrayList;
import java.util.Scanner;

public class FavoriteTeam {
    public String name;
    public ArrayList<Player> playerList = new ArrayList<Player>();
    public int playgroundId;
    public String text;

    /**
     * The constructor is called upon the creation of a favorite team, it prompts the user to enter all the details of his favorite team
     * @param player
     */
    public FavoriteTeam(Player player){
        Scanner scan = new Scanner (System.in);
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
     * @param player
     */
    public void addPlayer(Player player){
        playerList.add(player);
    }

    /**
     * toString function is overridden to be able to display all the information of a team
     * @return all the details of a team
     */
    @Override
    public String toString(){
        return "Team name: " + name + "\n" + "Playground ID: " + playgroundId + "\n" + "Additional info: " + text + "\n";
    }
}
