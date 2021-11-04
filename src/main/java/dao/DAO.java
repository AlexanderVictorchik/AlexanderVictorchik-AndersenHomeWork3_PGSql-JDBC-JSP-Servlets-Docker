package dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Parent access interface
 * @param <T> - Object
 * @param <ID> - primary key
 */
public interface DAO<T, ID> {
    /**
     * List of methods for CRUD actions
     */
    T select(int ID) throws SQLException, ClassNotFoundException;

    List<T> selectAll() throws SQLException, ClassNotFoundException;

    void insert(T o) throws SQLException, ClassNotFoundException;

    boolean update(T o) throws SQLException, ClassNotFoundException;

    boolean delete(int ID) throws SQLException, ClassNotFoundException;
}
