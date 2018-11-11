package bl;

import db.Connector;

public class DatabaseManager {

    public static boolean connect() {
        new Connector();
        return Connector.getConnection() != null;
    }

    public static void disconnect() {
        Connector.disconnect();
    }

}
