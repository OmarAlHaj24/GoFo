public class Player implements User {

    AccountInfo account;
    int id;
    Player (int nxtId, AccountInfo accountInfo) {
        account = new AccountInfo(accountInfo);
        id = nxtId;
    }

    @Override
    public void run() {

    }
}
