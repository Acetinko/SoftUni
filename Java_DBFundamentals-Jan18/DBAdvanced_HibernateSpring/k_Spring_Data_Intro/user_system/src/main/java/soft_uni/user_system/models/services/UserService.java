package soft_uni.user_system.models.services;

import soft_uni.user_system.models.entities.User;

import java.util.Date;
import java.util.List;

public interface UserService {

    void saveUserToDatabase(User user);

    void saveUserToDatabase(List<User> user);

    List<User> getAllWhereEmailLike(String domain);

    User getByUsername(String username);

    List<User> getAll();

    List<User> getAllInactiveUsers(Date tillDate);

    void deleteAllInactive();
}
