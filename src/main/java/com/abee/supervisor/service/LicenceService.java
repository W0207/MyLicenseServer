package com.abee.supervisor.service;

import com.abee.supervisor.domain.Licence;
import com.abee.supervisor.domain.Organization;
import com.abee.supervisor.repository.LicenceRepo;
import com.abee.supervisor.repository.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xincong yao
 */
@Service
public class LicenceService {

    @Autowired
    private LicenceRepo licenceRepo;

    @Autowired
    private OrganizationRepo organizationRepo;

    public Licence add(Integer type) {
       Licence licence;
       do {
           licence = new Licence(type);
       } while (licenceRepo.findById(licence.getSerialNumber()).isPresent());
       return licenceRepo.save(licence);
    }

    public boolean bind(Organization org, Licence licence) {
        if (licenceRepo.findById(licence.getSerialNumber()).isPresent()) {
            org.setSerialNumber(licence.getSerialNumber());
            if (organizationRepo.save(org) != null) {
                return true;
            }
        }

        return false;
    }

    public Licence purchase(String username, String password, Integer type) {
        Licence licence = add(type);

        Organization org = organizationRepo.findByUsernameAndPassword(username, password);

        if (org != null) {
            if (bind(org, licence)) {
                return licence;
            }
        }

        return null;
    }

    public void resetAll() {
        licenceRepo.resetAll();
    }

    public Licence getLicence(String serialNumber) {
        if (licenceRepo.findById(serialNumber).isPresent()) {
            return licenceRepo.findById(serialNumber).get();
        } else {
            return null;
        }
    }
}
