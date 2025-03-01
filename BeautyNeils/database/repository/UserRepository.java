package BeautyNeils.database.repository;

import BeautyNeils.database.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> getUserByEmailAndPassword(String email, String password);

    List<User> getUserByEmail(String email);

}
