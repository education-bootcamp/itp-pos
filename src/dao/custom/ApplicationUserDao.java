package dao.custom;

import dao.CrudDao;
import entity.ApplicationUser;

import java.sql.SQLException;

public interface ApplicationUserDao extends CrudDao<ApplicationUser, String> {
    public ApplicationUser findByEmail(String email) throws SQLException, ClassNotFoundException;
}
