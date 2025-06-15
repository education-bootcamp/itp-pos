package dao.custom.impl;

import dao.custom.ApplicationUserDao;
import entity.ApplicationUser;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ApplicationUserDaoImpl implements ApplicationUserDao {

    @Override
    public void create(ApplicationUser entity) throws IOException {

    }

    @Override
    public void update(ApplicationUser entity) throws IOException {

    }

    @Override
    public void delete(ApplicationUser entity) throws IOException {

    }

    @Override
    public ApplicationUser findById(String s) throws IOException {
        return null;
    }

    @Override
    public List<ApplicationUser> findAll() throws IOException {
        return Collections.emptyList();
    }
}
