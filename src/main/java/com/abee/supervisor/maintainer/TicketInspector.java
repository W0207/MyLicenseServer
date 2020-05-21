package com.abee.supervisor.maintainer;

import com.abee.supervisor.service.LicenceService;
import com.abee.supervisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TicketInspector {

    @Autowired
    private UserService userService;

    @Autowired
    private LicenceService licenceService;

    @Scheduled(cron = "0 0/30 * * * ?")
    private void check() {
        licenceService.resetAll();
        userService.forbidAll();
    }
}
