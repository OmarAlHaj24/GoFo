public class Player implements User {

    AccountInfo account;
    int id;
    Player (int nxtId, AccountInfo accountInfo) {
        account = new AccountInfo(accountInfo);
        id = nxtId;
    }

    public void fileComplaint(){
        //We need to create a complain
        //object and give it the player id as a parameter.
    }

    @Override
    public void run() {

    }
}
