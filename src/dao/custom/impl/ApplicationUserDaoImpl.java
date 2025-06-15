package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ApplicationUserDao;
import entity.ApplicationUser;
import util.PasswordManager;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ApplicationUserDaoImpl implements ApplicationUserDao {

    @Override
    public boolean create(ApplicationUser entity) throws IOException, SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO application_user VALUES(?,?,?)",
                entity.getEmail(),
                entity.getFullName(),
                PasswordManager.encryptPassword(entity.getPassword())
        );
    }

    @Override
    public boolean update(ApplicationUser entity) throws IOException {
        return false;
    }

    @Override
    public boolean delete(ApplicationUser entity) throws IOException {
        return false;
    }

    @Override
    public ApplicationUser findById(String s) throws IOException {
        return null;
    }

    @Override
    public List<ApplicationUser> findAll() throws IOException {
        return Collections.emptyList();
    }

    @Override
    public ApplicationUser findByEmail(String email) throws SQLException, ClassNotFoundException {
        ResultSet set =
                CrudUtil.execute("SELECT * FROM application_user WHERE email=?", email);
        if(set.next()){
            return new ApplicationUser(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3));
        }
        return null;
    }
}
