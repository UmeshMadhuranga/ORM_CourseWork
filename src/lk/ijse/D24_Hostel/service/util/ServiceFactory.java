package lk.ijse.D24_Hostel.service.util;

import lk.ijse.D24_Hostel.service.impl.RegistrationServiceImpl;
import lk.ijse.D24_Hostel.service.impl.RoomServiceImpl;
import lk.ijse.D24_Hostel.service.impl.StudentServiceImpl;
import lk.ijse.D24_Hostel.service.impl.UserServiceImpl;

public class ServiceFactory {
    public static SuperService getService(ServiceTypes serviceTypes) {
        switch (serviceTypes) {
            case USER:
                return new UserServiceImpl();

            case ROOMS:
                return new RoomServiceImpl();

            case STUDENT:
                return new StudentServiceImpl();

            case RESERVATION:
                return new RegistrationServiceImpl();

            default:
                return null;
        }
    }
}
