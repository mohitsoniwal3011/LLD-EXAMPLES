package proxy;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

interface PlatformUser{
    void deleteUser(User user) throws OperationNotSupportedException ;
}

class User implements  PlatformUser{
    String name;
    String age;
    String role;

    public User(String name, String age, String role) {
        this.name = name;
        this.age = age;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public void deleteUser(User user) {
        System.out.println("Deleting User...");
        System.out.println("Deleted :"+user.getName());
    }
}

class UserManager implements PlatformUser{
    private List<User> users;

    private User currentUser;
    UserManager(User user) {
        this.users = new ArrayList<>();
        this.currentUser = user;
    }

    private void addUser(User user){
        this.users.add(user);
    }

    public void deleteUser(User user) throws OperationNotSupportedException {
        if(Objects.equals(currentUser.getRole(), "ADMIN")){
            user.deleteUser(user);
        }else{
            throw new OperationNotSupportedException("Only admins can delete users");
        }
    }
}

public class UserProtectionProxyDesignPattern {
    public static void main(String[] args) throws OperationNotSupportedException {
        User user = new User("Mohit", "23","ADMIN");
        UserManager manager = new UserManager(user);
        manager.deleteUser(user);
        user.setRole("USER");
        manager.deleteUser(user);
    }
}
