package pers.lionlinzq.excel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.lionlinzq.excel.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String name, String password);

}
