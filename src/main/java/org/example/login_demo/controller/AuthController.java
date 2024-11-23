package org.example.login_demo.controller;

import org.example.login_demo.util.Const;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Const.API_PREFIX + "/authentication")
public class AuthController {
}
