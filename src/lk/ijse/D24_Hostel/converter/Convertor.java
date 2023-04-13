package lk.ijse.D24_Hostel.converter;

import lk.ijse.D24_Hostel.dto.ReservationDTO;
import lk.ijse.D24_Hostel.dto.RoomDTO;
import lk.ijse.D24_Hostel.dto.StudentDTO;
import lk.ijse.D24_Hostel.dto.UserDTO;
import lk.ijse.D24_Hostel.entity.Reservation;
import lk.ijse.D24_Hostel.entity.Room;
import lk.ijse.D24_Hostel.entity.Student;
import lk.ijse.D24_Hostel.entity.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Convertor {

    public static UserDTO convertUserToUserDTO(User user) {
        return new UserDTO(user.getUserName(), user.getPassword());
    }

    public static User convertUserDTOToUser(UserDTO userDTO) {
        return new User(userDTO.getUserName(), userDTO.getPassword());
    }

    public static List<UserDTO> toUserDTOList(List<User> userList) {
        List<UserDTO> list = new ArrayList<>();
        for (User user : userList) {
            list.add(convertUserToUserDTO(user));
        }
        return list;
    }

    public static StudentDTO convertStudentToStudentDTO(Student student) {
        return new StudentDTO(student.getStudent_id(), student.getName(), student.getAddress(), student.getContact_no(),
                student.getDob(), student.getGender());
    }

    public static Student convertStudentDTOToStudent(StudentDTO studentDTO) {
        return new Student(studentDTO.getStudent_id(), studentDTO.getName(), studentDTO.getAddress(),
                studentDTO.getContact_no(), studentDTO.getDob(), studentDTO.getGender());
    }

    public static Room convertRoomDTOtoRoom(RoomDTO roomDTO) {
        return new Room(roomDTO.getRoom_type_id(), roomDTO.getType(), roomDTO.getKey_money(), roomDTO.getQty());
    }

    public static RoomDTO convertRoomToRoomDTO(Room room) {
        return new RoomDTO(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty());
    }

    public static List<RoomDTO> toRoomDTOList(List<Room> List) {
        List<RoomDTO> roomDTOArrayList = new ArrayList<>();
        for (Room room : List) {
            roomDTOArrayList.add(convertRoomToRoomDTO(room));
        }
        return roomDTOArrayList;
    }

    public static List<StudentDTO> toStudentDTOList(List<Student> List) {
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for (Student student : List) {
            studentDTOList.add(convertStudentToStudentDTO(student));
        }
        return studentDTOList;
    }

    public static Reservation reservationDTOtoReservation(ReservationDTO reservationDTO){
        return new Reservation(
                reservationDTO.getRes_id(),
                reservationDTO.getDate(),
                reservationDTO.getStatus(),
                Convertor.convertStudentDTOToStudent(reservationDTO.getStudent()),
                Convertor.convertRoomDTOtoRoom(reservationDTO.getRoom())
                );
    }

    public static ReservationDTO reservationToReservationDTO(Reservation reservation){
        return new ReservationDTO(
                reservation.getRes_id(),
                reservation.getDate(),
                reservation.getStatus(),
                Convertor.convertStudentToStudentDTO(reservation.getStudent()),
                Convertor.convertRoomToRoomDTO(reservation.getRoom())
        );
    }

    public static List<ReservationDTO> toReservationDTOList(List<Reservation> List) {
        List<ReservationDTO> reservationList = new ArrayList<>();
        for (Reservation reservation : List) {
            reservationList.add(reservationToReservationDTO(reservation));
        }
        return reservationList;
    }

}
