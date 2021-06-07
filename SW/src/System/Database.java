package System;
import PlayerComponents.*;
import PlaygroundComponents.*;

import java.awt.print.Book;
import java.io.File;
import java.util.ArrayList;

public class Database {
    /* Data */
    static ArrayList<Player> playersList = new ArrayList<Player>();
    static ArrayList<PlaygroundOwner> playgroundOwnersList = new ArrayList<PlaygroundOwner>();
    static ArrayList<Playground> playgroundsList = new ArrayList<Playground>();
    static ArrayList<Booking> bookingsList = new ArrayList<Booking>();
    static ArrayList<Complaint> complaintsList = new ArrayList<Complaint>();
    static ArrayList<FavoriteTeam> favoriteTeamsList = new ArrayList<FavoriteTeam>();
    static Administrator admin = new Administrator();
    static int nxtId = 0;

    public void runUser(int id) {
        if (id == 0) {
            admin.run();
        } else {
            for (Player player : playersList) {
                if (player.id == id) {
                    player.run();
                    return;
                }
            }
            for (PlaygroundOwner playgroundOwner : playgroundOwnersList) {
                if (playgroundOwner.id == id) {
                    playgroundOwner.run();
                    return;
                }
            }
        }
    }

    public int checkLogin (String username, String password) {
        if (admin.account.checkUsernameAndPassword(username, password)) {
            return 0;
        }
        for (Player player : playersList) {
            if (player.account.checkUsernameAndPassword(username, password)) {
                return player.id;
            }
        }
        for (PlaygroundOwner playgroundOwner : playgroundOwnersList) {
            if (playgroundOwner.account.checkUsernameAndPassword(username, password)) {
                return playgroundOwner.id;
            }
        }
        return -1;
    }

    public int verifyAccountInfo (AccountInfo accInfo) {
        if (!admin.account.compare(accInfo)) {
            return -1;
        }
        for (Player player : playersList) {
            if (!player.account.compare(accInfo)) {
                return -1;
            }
        }
        for (PlaygroundOwner playgroundOwner : playgroundOwnersList) {
            if (!playgroundOwner.account.compare(accInfo)) {
                return -1;
            }
        }
        return ++nxtId;
    }

    public void addPlayer (Player player) {
        playersList.add(player);
    }

    public void deletePlayer (int id) {
        for (int i=0; i<playersList.size(); ++i) {
            if (playersList.get(i).id == id) {
                playersList.remove(i);
                break;
            }
        }
    }

    public void addPlaygroundOwner (PlaygroundOwner playgroundOwner) {
        playgroundOwnersList.add(playgroundOwner);
    }

    public void deletePlaygroundOwner (int id) {

        for (int i=0; i<playgroundOwnersList.size(); ++i) {
            if (playgroundOwnersList.get(i).id == id) {
                playgroundOwnersList.remove(i);
                break;
            }
        }
    }

    public void addPlayground (Playground playground) {
        playgroundsList.add(playground);
    }

    public void addFavoriteTeam (FavoriteTeam favoriteTeam) {
        favoriteTeamsList.add(favoriteTeam);
    }

    public boolean addPlayerToTeam (String name, Player player){
        for(FavoriteTeam team : favoriteTeamsList){
            if(team.name.equals(name)){
                team.addPlayer(player);
                return true;
            }
        }
        return false;
    }

    public void deletePlayground (int id) {
        for (int i=0; i<playgroundsList.size(); ++i) {
            if (playgroundsList.get(i).id == id) {
                playgroundsList.remove(i);
                break;
            }
        }
    }

    public boolean displayPlaygroundByState(Status status) {
        int playgroundNum = 0;
        boolean ret = false;
        for (Playground playground : playgroundsList) {
            if (playground.state.equals(status) || status.equals(Status.all)){
                System.out.println("--- Playground " + (++playgroundNum) + " ---");
                System.out.println(playground);
                ret = true;
            }
        }
        return ret;
    }

    public void updatePlaygroundState(int id, Status s) {
        for (Playground playground : playgroundsList) {
            if (playground.id == id) {
                playground.state = s;
                break;
            }
        }
    }

    public void addBooking (Booking booking) {
        bookingsList.add(booking);
    }

    public void deleteBooking (int id) {
        for (int i=0; i<bookingsList.size(); ++i) {
            if (bookingsList.get(i).id == id) {
                bookingsList.remove(i);
                break;
            }
        }
    }

    public void addComplaint (Complaint complaint) {
        complaintsList.add(complaint);
    }

    public boolean deleteComplaint (int id) {
        boolean ret = false;
        return ret;
    }

    public void addSlot (int playgroundId, Slot slot) {
        for (Playground playground : playgroundsList) {
            if (playground.id == playgroundId) {
                playground.addSlot(slot);
            }
        }
    }

    public boolean displayComplaints() {
        boolean ret = false;
        for (Complaint complaint : complaintsList) {
            System.out.println(complaint);
            ret = true;
        }
        return ret;
    }

    public boolean verifyUsername(String username) {
        if (admin.account.username == username) {
            return false;
        }
        for (Player player : playersList) {
            if (player.account.username == username) {
                return false;
            }
        }
        for (PlaygroundOwner playgroundOwner : playgroundOwnersList) {
            if (playgroundOwner.account.username == username) {
                return false;
            }
        }
        return true;
    }

    public boolean verifyEmail(String email) {
        if (admin.account.email == email) {
            return false;
        }
        for (Player player : playersList) {
            if (player.account.email == email) {
                return false;
            }
        }
        for (PlaygroundOwner playgroundOwner : playgroundOwnersList) {
            if (playgroundOwner.account.email == email) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Booking> getBookingsList() {
        return bookingsList;
    }

    public ArrayList<Playground> getPlaygroundsList() {
        return playgroundsList;
    }

    public void displayPlaygroundById(int id) {
        for (Playground playground: playgroundsList) {
            if (playground.id == id) {
                System.out.println(playground);
            }
        }
    }

    public boolean runPlayground(int playgroundId,int playgroundOwnerId) {
        boolean ret = false;
        for (Playground playground : playgroundsList) {
            if (playground.id == playgroundId && playground.playgroundOwnerId == playgroundOwnerId) {
                playground.run();
                ret = true;
                break;
            }
        }
        return ret;
    }

     public boolean checkeWalletBalance(int id, double balance) {
        for (Player player: playersList) {
            if (player.id == id) {
                return player.eWallet <= balance;
            }
        }
        return false;
     }

     public void updateeWalletBalance(int id, double balance) {
        for (Player player: playersList) {
            if (player.id == id) {
                player.eWallet+= balance;
                return;
            }
        }
     }

     public double getCost(int playgroundId) {
        for (Playground playground : playgroundsList) {
            if (playground.id == playgroundId) {
                return playground.cost;
            }
        }
        return 0;
     }
}
