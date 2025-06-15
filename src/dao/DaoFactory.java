package dao;

import dao.custom.impl.ApplicationUserDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }
    public <T> T getDao(DaoType daoType) {
        switch (daoType) {
            case APPLICATION_USER:return (T) new ApplicationUserDaoImpl();
            default:return null;
        }
    }
}
