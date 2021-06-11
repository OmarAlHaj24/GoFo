package System;

import PlayerComponents.*;
import PlaygroundComponents.*;

import java.util.ArrayList;

/**
 * Database class acts as the database for our system,
 * it stores static arrays which will be holding all the data that will be used by any of the users,
 * it will also allow us to manipulate any of these arrays.
 *
 * @author Mostafa Mahmoud Anwar   20190544
 * @version 1.0
 * @since 4 June 2021
 */

public class Database {
    /**
     * Data
     */
    static ArrayList<Player> playersList = new ArrayList<Player>();
    static ArrayList<PlaygroundOwner> playgroundOwnersList = new ArrayList<PlaygroundOwner>();
    static ArrayList<Playground> playgroundsList = new ArrayList<Playground>();
    static ArrayList<Booking> bookingsList = new ArrayList<Booking>();
    static ArrayList<Complaint> complaintsList = new ArrayList<Complaint>();
    static ArrayList<FavoriteTeam> favoriteTeamsList = new ArrayList<FavoriteTeam>();
    static Administrator admin = new Administrator();
    static int nxtId = 0;

    /**
     * Function that calls user's interface.
     *
     * @param id
     */
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

    /**
     * Function that checks if the given username and password exist or not in the database.
     *
     * @param username
     * @param password
     * @return user's id
     */
    public int checkLogin(String username, String password) {
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

    /**
     * Function that checks if the account info is unique for the registration process.
     *
     * @param accInfo
     * @return New ID if account info is valid and -1 if it's repeated.
     */
    public int verifyAccountInfo(AccountInfo accInfo) {
        if (!admin.account.checkUsernameAndEmail(accInfo)) {
            return -1;
        }
        for (Player player : playersList) {
            if (!player.account.checkUsernameAndEmail(accInfo)) {
                return -1;
            }
        }
        for (PlaygroundOwner playgroundOwner : playgroundOwnersList) {
            if (!playgroundOwner.account.checkUsernameAndEmail(accInfo)) {
                return -1;
            }
        }
        return ++nxtId;
    }

    /**
     * Function that adds new player to players list
     *
     * @param player
     */
    public void addPlayer(Player player) {
        playersList.add(player);
    }

    public void deletePlayer(int id) {
        for (int i = 0; i < playersList.size(); ++i) {
            if (playersList.get(i).id == id) {
                playersList.remove(i);
                break;
            }
        }
    }

    /**
     * Function that adds new playground owner to the playgroundOwners' list.
     *
     * @param playgroundOwner
     */
    public void addPlaygroundOwner(PlaygroundOwner playgroundOwner) {
        playgroundOwnersList.add(playgroundOwner);
    }

    /**
     * Function that deletes playground owner from the database by its ID.
     *
     * @param id
     */
    public void deletePlaygroundOwner(int id) {

        for (int i = 0; i < playgroundOwnersList.size(); ++i) {
            if (playgroundOwnersList.get(i).id == id) {
                playgroundOwnersList.remove(i);
                break;
            }
        }
    }

    /**
     * Function that adds new playground to the playgrounds' list.
     *
     * @param playground
     */
    public void addPlayground(Playground playground) {
        playgroundsList.add(playground);
    }

    /**
     * Function that adds new favorite team to the favorite teams' list to be accessed by player.
     *
     * @param favoriteTeam
     */
    public void addFavoriteTeam(FavoriteTeam favoriteTeam) {
        favoriteTeamsList.add(favoriteTeam);
    }


    /**
     * Function that adds na player to a favorite team.
     *
     * @param name
     * @param player
     * @return true or false based on the existence of selected player in favorite team in the database.
     */
    public boolean addPlayerToTeam(String name, Player player) {
        for (FavoriteTeam team : favoriteTeamsList) {
            if (team.name.equals(name)) {
                team.addPlayer(player);
                return true;
            }
        }
        return false;
    }

    /**
     * Function that deletes a playground from the database by its ID.
     *
     * @param id
     */
    public void deletePlayground(int id) {
        for (int i = 0; i < playgroundsList.size(); ++i) {
            if (playgroundsList.get(i).id == id) {
                playgroundsList.remove(i);
                break;
            }
        }
    }

    /**
     * '
     * Function that displays playground by its status.
     *
     * @param status
     * @return
     */
    public boolean displayPlaygroundByState(Status status) {
        int playgroundNum = 0;
        boolean ret = false;
        for (Playground playground : playgroundsList) {
            if (playground.state.equals(status) || status.equals(Status.all)) {
                System.out.println("--- Playground " + (++playgroundNum) + " ---");
                System.out.println(playground);
                if (status.equals(status.all)) {
                    switch (playground.state) {
                        case ACCEPTED -> System.out.println("Status: Accepted");
                        case REJECTED -> System.out.println("Status: Rejected");
                        case PENDING -> System.out.println("Status: Pending");
                        case SUSPENDED -> System.out.println("Status: Suspended");
                    }
                }
                ret = true;
            }
        }
        return ret;
    }

    /**
     * Function that updates the playground's status.
     *
     * @param id
     * @param s
     */
    public void updatePlaygroundState(int id, Status s) {
        for (Playground playground : playgroundsList) {
            if (playground.id == id) {
                playground.state = s;
                break;
            }
        }
    }

    /**
     * Function that adds a new booking.
     *
     * @param booking
     */
    public void addBooking(Booking booking) {
        bookingsList.add(booking);
    }

    /**
     * Function that deletes a booking.
     *
     * @param id
     */
    public void deleteBooking(int id) {
        for (int i = 0; i < bookingsList.size(); ++i) {
            if (bookingsList.get(i).id == id) {
                bookingsList.remove(i);
                break;
            }
        }
    }

    /**
     * Function that adds a new complaint.
     *
     * @param complaint
     */
    public void addComplaint(Complaint complaint) {
        complaintsList.add(complaint);
    }

    /**
     * Function that deletes a complaint.
     *
     * @param id
     * @return
     */
    public boolean deleteComplaint(int id) {
        boolean ret = false;
        return ret;
    }

    /**
     * Function that displays complaints.
     *
     * @return
     */
    public boolean displayComplaints() {
        boolean ret = false;
        for (Complaint complaint : complaintsList) {
            System.out.println(complaint);
            ret = true;
        }
        return ret;
    }

    /**
     * Function that verifies the username's uniqueness.
     *
     * @param username
     * @return true if the username is unique or false
     */
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

    /**
     * Function that verifies the email's uniqueness.
     *
     * @param email
     * @return true if the email is unique or false
     */
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

    /**
     * bookingsList getter
     *
     * @return bookingsList array
     */
    public ArrayList<Booking> getBookingsList() {
        return bookingsList;
    }

    /**
     * playgroundsList getter
     *
     * @return playgroundsList array
     */
    public ArrayList<Playground> getPlaygroundsList() {
        return playgroundsList;
    }

    /**
     * Function that displays a playground by its ID.
     *
     * @param id
     */
    public void displayPlaygroundById(int id) {
        for (Playground playground : playgroundsList) {
            if (playground.id == id) {
                System.out.println(playground);
            }
        }
    }

    /**
     * Function that runs playground's interface.
     *
     * @param playgroundId
     * @param playgroundOwnerId
     * @return
     */
    public boolean runPlayground(int playgroundId, int playgroundOwnerId) {
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

    /**
     * Function that checks if the player has enough money to book the playground.
     *
     * @param id
     * @param balance
     * @return if the balance exceeds the playground's price
     */
    public boolean checkeWalletBalance(int id, double balance) {
        for (Player player : playersList) {
            if (player.id == id) {
                return player.eWallet >= balance;
            }
        }
        return false;
    }

    /**
     * Functioin that updates eWallet balance by searching in ID's list if it's a player it will withdraw playground's price
     * and if it's a playground owner it'll add playground's price to the eWallet balance.
     *
     * @param id
     * @param balance
     */
    public void updateWalletBalance(int id, double balance) {
        for (Player player : playersList) {
            if (player.id == id) {
                player.eWallet += balance;
                return;
            }
        }
        for (PlaygroundOwner playgroundOwner : playgroundOwnersList) {
            if (playgroundOwner.id == id) {
                playgroundOwner.eWallet += balance;
                return;
            }
        }
    }

    /**
     * Function that gets playground's cost.
     *
     * @param playgroundId
     * @return playground's cost
     */
    public double getCost(int playgroundId) {
        for (Playground playground : playgroundsList) {
            if (playground.id == playgroundId) {
                return playground.cost;
            }
        }
        return 0;
    }

    /**
     * Function that displays a playground by its playground owner's ID.
     *
     * @param playgroundOwnerId
     */
    public void viewPlaygroundsByOwnerId(int playgroundOwnerId) {
        for (Playground playground : playgroundsList) {
            if (playground.playgroundOwnerId == playgroundOwnerId) {
                System.out.println(playground);
                switch (playground.state) {
                    case ACCEPTED:
                        System.out.println("Status: Accepted");
                        break;
                    case REJECTED:
                        System.out.println("Status: Rejected");
                        break;
                    case PENDING:
                        System.out.println("Status: Pending");
                        break;
                    case SUSPENDED:
                        System.out.println("Status: Suspended");
                        break;
                }
            }
        }
    }

    /**
     * Function that checks if the slot is available.
     *
     * @param playgroundId
     * @param slotId
     * @return if slot is available
     */
    public boolean checkSlot(int playgroundId, int slotId) {
        boolean ret = false;
        for (Playground playground : playgroundsList) {
            if (playground.id == playgroundId) {
                for (Slot slot : playground.slotList) {
                    if (slot.id == slotId) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Function the gets playground owner's ID.
     *
     * @param playgroundId
     * @return playground owner's ID.
     */
    public int getPlaygroundOwnerId(int playgroundId) {
        for (Playground playground : playgroundsList) {
            if (playground.id == playgroundId) {
                return playground.playgroundOwnerId;
            }
        }
        return 0;
    }

    /**
     * Function that displays(filters) playgrounds by its address.
     *
     * @param address
     */
    public void displayPlaygroundFilteredByAddress(String address) {
        for (Playground playground : playgroundsList) {
            if (playground.state.equals(Status.ACCEPTED) && (playground.address == address || address.equalsIgnoreCase("all"))) {
                System.out.println(playground);
                for (Slot slot : playground.slotList) {
                    System.out.print(slot + " ");
                }
                System.out.println();
            }
        }
    }
}