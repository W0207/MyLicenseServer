package com.abee.supervisor.web;

import com.abee.supervisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xincong yao
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("activate")
    public String activate(String ip, Integer port, String serialNumber) {
        if (userService.activate(ip, port, serialNumber)) {
            return "succeed.";
        } else {
            return "failed.";
        }
    }

    @RequestMapping("forbid")
    public String forbid(String ip, Integer port) {
        if (userService.forbid(ip, port)) {
            return "succeed.";
        } else {
            return "failed.";
        }
    }
}
