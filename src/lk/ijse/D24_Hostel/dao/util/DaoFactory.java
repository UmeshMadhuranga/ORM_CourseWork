package lk.ijse.D24_Hostel.dao.util;

import lk.ijse.D24_Hostel.dao.custom.RegistrationDAO;
import lk.ijse.D24_Hostel.dao.impl.RegistrationDAOImpl;
import lk.ijse.D24_Hostel.dao.impl.RoomsDAOImpl;
import lk.ijse.D24_Hostel.dao.impl.StudentDAOImpl;
import lk.ijse.D24_Hostel.dao.impl.UserDAOImpl;

public class DaoFactory {
    public static SuperDao getDAO(DaoTypes daoTypes){
        switch (daoTypes){
            case USER:
                return new UserDAOImpl();

            case ROOMS:
                return new RoomsDAOImpl();

            case STUDENT:
                return new StudentDAOImpl();

            case RESERVATION:
                return new RegistrationDAOImpl();

            default:
                return null;
        }
    }
}
