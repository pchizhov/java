package bl;

import db.repositories.UserRepo;
import db.entities.UserDBE;
import bl.entities.User;

import java.sql.SQLException;

public class LoginManager{

    final UserRepo uR;

    public LoginManager(UserRepo uR){
        this.uR = uR;
    }

    public User signIn(String enteredLogin, String enteredPassword){
        UserDBE account = null;
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
