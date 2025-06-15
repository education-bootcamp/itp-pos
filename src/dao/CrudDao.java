package dao;

import java.io.IOException;
import java.util.List;

public interface CrudDao<T, ID> extends SuperDao{
    public void create(T entity) throws IOException;
    public void update(T entity) throws IOException;
    public void delete(T entity) throws IOException;
    public T findById(ID id) throws IOException;
    public List<T> findAll() throws IOException;
}
