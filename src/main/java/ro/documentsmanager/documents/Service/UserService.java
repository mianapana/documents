package ro.documentsmanager.documents.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.documentsmanager.documents.Entity.User;
import ro.documentsmanager.documents.Repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User getById(int id) {
        return userRepository.findById(id).orElse(null);
    }
    
}
