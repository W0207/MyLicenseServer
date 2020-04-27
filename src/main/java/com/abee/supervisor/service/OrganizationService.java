package com.abee.supervisor.service;

import com.abee.supervisor.domain.Organization;
import com.abee.supervisor.repository.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xincong yao
 */
@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepo organizationRepo;

    public boolean add(String username, String password) {
        Organization org = new Organization(username, password);
        if (organizationRepo.save(org) == null) {
            return false;
        } else {
            return true;
        }
    }
}
