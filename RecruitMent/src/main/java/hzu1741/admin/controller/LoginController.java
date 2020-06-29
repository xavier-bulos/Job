package hzu1741.admin.controller;

import hzu1741.admin.dao.LoginLogDao;
import hzu1741.admin.dao.OperationDao;
import hzu1741.admin.dao.UserDao;
import hzu1741.admin.service.LoginLogService;
import hzu1741.admin.service.OperationService;
import hzu1741.admin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;


@Controller
@RequestMapping("/")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")//跨域传参
public class LoginController
{

    @Resource
    UserService userService;

    @Resource
    LoginLogService loginLogService;

    @Resource
    OperationService operationService;

    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("/star")
    public String tostar(){
        return "star";
    }




    //登录功能
    @RequestMapping(value = "/tologin",method = {RequestMethod.POST})
    @ResponseBody
    public String doLogin(@RequestParam(name="userName") String userName,
                          @RequestParam(name="password") String password,
                          Model model)
    {
        if(!userName.equals(userService.getName(userName))){
            return "nameError";
        }

        UserDao user = userService.selectByName(userName);

        if( !password.equals(user.getUserPwd())) {
            return "pwdError";
        }
        else{
            //写进登录日志
            writeLoginLog(user,"在线",new Date(),null);
            //写进操作日志
            writeOperateLog(user,"登录");

            model.addAttribute("user",user);
            return "success";
        }
    }



    //注册功能
    @RequestMapping(value = "/register")
    public String toRegister()
    {
        return "register";
    }

    @RequestMapping(value = "/doRegister",method = {RequestMethod.POST})
    @ResponseBody
    public String doRegister(@RequestParam(name="phone") String phone,
                             @RequestParam(name="userName") String userName,
                             @RequestParam(name="password") String password)
    {

        if(userName.equals(userService.getName(userName))){
            return "nameError";
        }
        if(phone.equals(userService.getPhone(phone))){
            return "phoneError";
        }

        UserDao user = new UserDao();
        user.setUserId(phone);
        user.setUserName(userName);
        user.setUserPwd(password);
        user.setUserPhone(phone);
        user.setRolePower("用户");

        if(userService.insert(user) == 0){
            return "error";
        }
        else{
            return  "success";
        }

    }


    //登出功能
    @RequestMapping(value = "/exit",method = {RequestMethod.POST})
    @ResponseBody
    public String exit(@RequestParam(name="userName") String userName)
    {
        UserDao user = new UserDao();
        user = userService.selectByName(userName);

        //写进登录日志
        writeLoginLog(user,"离线",null,new Date());

        //写进操作日志
        writeOperateLog(user,"登出");


        return "success";
    }

    boolean  writeOperateLog(UserDao user,String operation)
    {
        Date date = new Date();

        OperationDao operationDao = new OperationDao();
        operationDao.setUserId(user.getUserId());
        operationDao.setUserName(user.getUserName());
        operationDao.setOperation(operation);
        operationDao.setOperationDate(date);
        if(operationService.insert(operationDao) == 0){
            return false;
        }
        else {
            return true;
        }

    }

    boolean writeLoginLog(UserDao user,String online,Date login,Date exit)
    {
        LoginLogDao loginLog = new LoginLogDao();
        loginLog.setUserId(user.getUserId());
        loginLog.setUseName(user.getUserName());
        if(login != null){
            loginLog.setLoginDate(login);
        }
        if(exit != null){
            loginLog.setExitDate(exit);
        }
        loginLog.setOnline(online);

        if(loginLogService.insert(loginLog) == 0){
            return false;
        }
        else {
            return  true;
        }

    }
}
