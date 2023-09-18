package com.like4u.aim.controller;

import com.like4u.aim.service.AIMServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Zhang Min
 * @version 1.0
 * @date 2023/7/16 8:58
 */
@Controller
public class AIMServerController {

    @Autowired
    private AIMServerService aimServerService;


    @RequestMapping("test")
    private void serverStart() {
        aimServerService.checkLogin();
    }
}
