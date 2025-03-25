package pers.lionlinzq.excel.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import pers.lionlinzq.excel.entity.User;
import pers.lionlinzq.excel.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void saveUserBatch(List<User> user) {
        userRepository.saveAll(user);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public <S extends T> void batchSave(List<User> list) {
        int BATCH_SIZE = 1000;
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            entityManager.persist(list.get(i));
            index++;
            if (index % BATCH_SIZE == 0){
                entityManager.flush();
                entityManager.clear();
            }
        }
    }


}