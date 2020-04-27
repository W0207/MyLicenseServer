package com.abee.supervisor.service;

import com.abee.supervisor.domain.Organization;
import com.abee.supervisor.repository.OrganizationRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationServiceTest {

    @Autowired
    private OrganizationRepo organizationRepo;

    @Test
    void test() {
        System.out.println(organizationRepo.findById(10));
    }
}