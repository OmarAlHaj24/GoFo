package PlayerComponents;
import System.*;
import PlaygroundComponents.*;

import java.util.ArrayList;
import java.util.Scanner;

public class FavoriteTeam {
    public String name;
    public ArrayList<Player> playerList = new ArrayList<Player>();
    public int playgroundId;
    public String text;
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

    public void addPlayer(Player player){
        playerList.add(player);
    }

    @Override
    public String toString(){
        return "Team name: " + name + "\n" + "Playground ID: " + playgroundId + "\n" + "Additional info: " + text + "\n";
    }
}
