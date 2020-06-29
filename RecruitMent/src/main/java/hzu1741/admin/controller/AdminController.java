package hzu1741.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")//跨域传参
public class AdminController
{
    //1.管理员查看操作日志
    //2.管理员查看登录日志
}
