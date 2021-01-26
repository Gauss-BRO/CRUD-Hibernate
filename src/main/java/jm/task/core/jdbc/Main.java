package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        User neo = new User("Neo", "Chosen", (byte) 30);
        User spongeBob = new User("Sponge Bob", "Square Pants", (byte) 22);
        User patrickStar = new User("Patrick", "Star", (byte) 35);
        User squidWard = new User("SquidWard", "Sad", (byte) 30);

        UserService dataBase = new UserServiceImpl();
        dataBase.createUsersTable();
        dataBase.saveUser(neo.getName(), neo.getLastName(), neo.getAge());
        dataBase.saveUser(spongeBob.getName(), spongeBob.getLastName(), spongeBob.getAge());
        dataBase.saveUser(patrickStar.getName(), patrickStar.getLastName(), patrickStar.getAge());
        dataBase.saveUser(squidWard.getName(), squidWard.getLastName(), squidWard.getAge());
        List<User> userList = dataBase.getAllUsers();
        for (User user: userList) {
            System.out.println(user);
        }
        dataBase.cleanUsersTable();
        dataBase.dropUsersTable();
    }
}
