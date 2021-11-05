package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Parent access interface
 * @param <T> - Object
 * @param <ID> - primary key
 */
public interface DAO<T, ID> {
    /**
     * List of methods for CRUD actions
     */
    Optional<T> select(ID id) throws SQLException, ClassNotFoundException;

    List<T> selectAll() throws SQLException, ClassNotFoundException;

    boolean insert(T o) throws SQLException, ClassNotFoundException;

    boolean update(T o) throws SQLException, ClassNotFoundException;

    boolean delete(T o) throws SQLException, ClassNotFoundException;
}
