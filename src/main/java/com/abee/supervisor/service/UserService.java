package com.abee.supervisor.service;

import com.abee.supervisor.domain.User;
import com.abee.supervisor.domain.UserPK;
import com.abee.supervisor.repository.LicenceRepo;
import com.abee.supervisor.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xincong yao
 */
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LicenceRepo licenceRepo;

    @Transactional(rollbackFor = Exception.class)
    public boolean activate(String ip, Integer port, String serialNumber) {
        User user = new User(ip, port, serialNumber, 1);

        if (userRepo.findByUserPK(new UserPK(ip, port)) != null) {
            userRepo.save(user);
            return true;
        } else {
            if (licenceRepo.addCurrentNumber(serialNumber) != 0) {
                userRepo.save(user);
                return true;
            }
        }

        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean forbid(String ip, Integer port) {
        User user = userRepo.findByUserPK(new UserPK(ip, port));
        if (user != null) {
            if (licenceRepo.subCurrentNumber(user.getSerialNumber()) != 0) {
                user.setState(0);
                userRepo.save(user);
                return true;
            }
        }

        return false;
    }

    public void forbidAll() {
        userRepo.forbidAll();
    }
}
