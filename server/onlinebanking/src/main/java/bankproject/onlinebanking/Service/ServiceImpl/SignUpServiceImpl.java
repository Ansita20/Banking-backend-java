package bankproject.onlinebanking.Service.ServiceImpl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bankproject.onlinebanking.Model.User;

import bankproject.onlinebanking.Repository.UserRepository;
import bankproject.onlinebanking.Requests.ChangePasswordReq;
import bankproject.onlinebanking.Service.SignUpService;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    PasswordEncoder passwordEncoded;

    @Override
    public User createUser(User user) {

        user.setPassword(passwordEncoded.encode(user.getPassword()));

        User createdUser = userRepo.save(user);

        return createdUser;

    }

    @Transactional
    @Override
    public Optional<User> getAUser(String userId) {
        Optional<User> userOpt = userRepo.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getAccounts() != null) {
                user.getAccounts().size();
            }
            if (user.getBeneficiaries() != null) {
                user.getBeneficiaries().size();
            }
        }
        return userOpt;
    }

    @Transactional
    @Override
    public List<User> GetAllUsers() {
        System.out.println("++++++ insdi all user controller 2");
        List<User> users = userRepo.findAll();
        for (User user : users) {
            if (user.getAccounts() != null) {
                user.getAccounts().size();
            }
            if (user.getBeneficiaries() != null) {
                user.getBeneficiaries().size();
            }
        }
        return users;
    }

    @Override
    public boolean checkEmail(String email) {
        if (userRepo.findByEmail(email) != null)
            return true;
        return false;
    }

    @Override
    public User findByResetPasswordToken(String token) {
        return userRepo.findByResetPasswordToken(token);
    }

    @Override
    public void updateResetPasswordToken(String token, String email) {
        User theUser = userRepo.findByEmail(email);
        theUser.setResetPasswordToken(token);
        userRepo.save(theUser);
    }

    @Override
    public void updatePassword(String password, String token) {
        User theUser = userRepo.findByResetPasswordToken(token);
        theUser.setPassword(passwordEncoded.encode(password));
        theUser.setResetPasswordToken(null);
        userRepo.save(theUser);
    }

    @Override
    public User findByOTP(String otp) {
        return userRepo.findByotp(otp);
    }

    // @Override
    // public void updateOtp(String otp, String email) {
    // User theUser = userRepo.findByEmail(email);
    // theUser.setOtp(otp);
    // userRepo.save(theUser);
    // }

    @Override
    public void deleteAccount(String email) {
        userRepo.deleteUser(email);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public void save(User theUser) {
        userRepo.save(theUser);
    }

    @Override
    public void updateIsEmailVerified(String otp) {
        User theUser = userRepo.findByotp(otp);
        theUser.setEmailVerified(true);
        theUser.setOtp(null);
        userRepo.save(theUser);
    }

    @Override
    public Boolean changePassword(String userId, ChangePasswordReq changePasswordReq) {
        User user = userRepo.findById(userId).get();

        try {
            if (passwordEncoded.matches(changePasswordReq.getOldPassword(), user.getPassword())) {
                user.setPassword(passwordEncoded.encode(changePasswordReq.getNewPassWord()));
                userRepo.save(user);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findById(String userId) {

        User theUser = userRepo.findById(userId).get();

        return theUser;
    }

}
