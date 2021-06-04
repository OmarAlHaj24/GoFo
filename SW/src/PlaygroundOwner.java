public class PlaygroundOwner implements User {

    AccountInfo account;
    int id;
    PlaygroundOwner (int nxtId, AccountInfo accountInfo) {
        account = new AccountInfo(accountInfo);
        id = nxtId;
    }

    @Override
    public void run() {

    }
}
