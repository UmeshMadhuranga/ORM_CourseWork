package lk.ijse.D24_Hostel.util;

import lk.ijse.D24_Hostel.entity.Reservation;
import lk.ijse.D24_Hostel.entity.Room;
import lk.ijse.D24_Hostel.entity.Student;
import lk.ijse.D24_Hostel.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    public  SessionFactory sessionFactory;
    private static FactoryConfiguration factoryConfiguration;

    public FactoryConfiguration() {
        Configuration configuration = new Configuration().configure()
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(User.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return factoryConfiguration == null ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
