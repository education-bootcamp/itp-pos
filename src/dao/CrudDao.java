package dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T, ID> extends SuperDao{
    public boolean create(T entity) throws IOException, SQLException, ClassNotFoundException;
    public boolean update(T entity) throws IOException;
    public boolean delete(T entity) throws IOException;
    public T findById(ID id) throws IOException;
    public List<T> findAll() throws IOException;
}
