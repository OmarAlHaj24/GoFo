package PlaygroundComponents;
import System.*;
import PlayerComponents.*;

public class PlaygroundOwner implements User {

    public AccountInfo account;
    public int id;
    public PlaygroundOwner (int nxtId, AccountInfo accountInfo) {
        account = new AccountInfo(accountInfo);
        id = nxtId;
    }

    @Override
    public void run() {

    }
}
