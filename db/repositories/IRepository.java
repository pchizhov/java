package db.repositories;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IRepository<T> {

    T get(String id) throws SQLException ;

    ArrayList<T> list() throws SQLException;

    void update(T object) throws SQLException;

    void delete(String id) throws SQLException;

}
