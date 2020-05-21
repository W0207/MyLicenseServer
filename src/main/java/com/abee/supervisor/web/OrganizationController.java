package com.abee.supervisor.web;

import com.abee.supervisor.domain.Organization;
import com.abee.supervisor.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xincong yao
 */
@RestController
@RequestMapping("org")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping
    public String register(String username, String password) {
        if (organizationService.add(username, password)) {
            return "Registration successful.";
        } else {
            return "Registration failed.";
        }
    }
}
