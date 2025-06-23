package bo;

import bo.custom.impl.ApplicationUserBoImpl;
import bo.custom.impl.CustomerBoImpl;
import dao.DaoType;
import dao.custom.impl.ApplicationUserDaoImpl;

public class BoFactory {
    private static BoFactory boFactory;

    public static BoFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }
    public <T> T getBo(BoType boType) {
        switch (boType) {
            case APPLICATION_USER:return (T) new ApplicationUserBoImpl();
            case CUSTOMER:return (T) new CustomerBoImpl();
            default:return null;
        }
    }
}
