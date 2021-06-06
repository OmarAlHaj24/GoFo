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

    }

    public void addPlaygroundOwner (PlaygroundOwner playgroundOwner) {
        playgroundOwnersList.add(playgroundOwner);
    }

    public void deletePlaygroundOwner (int id) {

    }

    public void addPlayground (Playground playground) {
        playgroundsList.add(playground);
    }

    public void deletePlayground (int id) {

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

    public void updatePlaygroundState(int id, String state) {

    }

    public void addBooking (Booking booking) {
        bookingsList.add(booking);
    }

    public void deleteBooking (int id) {

    }

    public void addComplaint (Complaint complaint) {
        complaintsList.add(complaint);
    }

    public boolean deleteComplaint (int id) {
        boolean ret = false;
        return ret;
    }

    public void addSlot (int playgroundId, Slot slot) {

    }

    public boolean displayComplaints() {
        boolean ret = false;
        for (Complaint complaint : complaintsList) {
            System.out.println(complaint);
            ret = true;
        }
        return ret;
    }
}
