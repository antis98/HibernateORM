package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        CarService carService = context.getBean(CarService.class);

        Car toyota = new Car("Toyota", 11);
        Car honda = new Car("Honda", 22);
        Car nissan = new Car("Nissan", 33);
        Car ford = new Car("Ford", 44);

        carService.add(toyota);
        carService.add(honda);
        carService.add(nissan);
        carService.add(ford);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        userService.setCarById(1L, nissan);
        userService.setCarById(2L, toyota);
        userService.setCarById(3L, ford);
        userService.setCarById(4L, honda);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        System.out.println(honda + " Owner = " + userService.getUserByCar(honda));

        context.close();
    }
}
