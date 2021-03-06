package soft_uni.user_system.models.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import soft_uni.user_system.models.entities.User;
import soft_uni.user_system.models.repositories.UserRepository;
import soft_uni.user_system.models.services.UserService;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private Validator validator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUserToDatabase(User user){
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void saveUserToDatabase(List<User> user){
        this.userRepository.saveAll( user);
    }

    @Override
    public List<User> getAllWhereEmailLike(String lookupString) {
        return this.userRepository.findAllByEmailEndsWith(lookupString);
    }

    @Override
    public User getByUsername(String username){
        return this.userRepository.getByUsername(username);
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public List<User> getAllInactiveUsers(Date tillDate){
        return this.userRepository.findAllByLastTimeLoggedInLessThan(tillDate);
    }

    @Override
    public void deleteAllInactive(){
        this.userRepository.deleteAllByDeleted(true);
    }
}
