import java.util.Vector;

public class Database {
    private static Vector<UserColl> userDB;

    public Database(){
        userDB = new Vector<UserColl>();
    }

    public void RegisterUser(UserColl u){
        if(!userDB.contains(u)){
            userDB.add(u);
        }
        //ADD RETURN TO SAY ADD WAS UNSUCCESSFUL
    }
}

