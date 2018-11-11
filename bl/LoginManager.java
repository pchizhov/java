package bl;

import db.repositories.UserRepo;
import db.entities.User;
import bl.entities.BLUser;

import java.sql.SQLException;

public class LoginManager{

    final UserRepo uR;

    public LoginManager(UserRepo uR){
        this.uR = uR;
    }

    public BLUser signIn(String enteredLogin, String enteredPassword){
        User account = null;
        try {
            account = uR.get(enteredLogin);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (account == null || !enteredPassword.equals(account.getPassword()) )
            return null;
        else
            return Mapper.map(account);
    }

}
