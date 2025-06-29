package dao;

import dao.custom.impl.ApplicationUserDaoImpl;
import dao.custom.impl.CustomerDaoImpl;
import dao.custom.impl.ProductDaoImpl;

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
            case CUSTOMER:return (T) new CustomerDaoImpl();
            case PRODUCT:return (T) new ProductDaoImpl();
            default:return null;
        }
    }
}
