package com.abee.supervisor.web;

import com.abee.supervisor.domain.Licence;
import com.abee.supervisor.service.LicenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xincong yao
 */
@RestController
@RequestMapping("licence")
public class LicenceController {

    @Autowired
    private LicenceService licenceService;

    @PostMapping("purchase")
    public String purchase(String username, String password, Integer type) {
        Licence licence = licenceService.purchase(username, password, type);
        if (licence != null) {
            return "Purchase succeed, your serial number: "
                    + licence.getSerialNumber();
        } else {
            return "Purchase failed.";
        }
    }
}
