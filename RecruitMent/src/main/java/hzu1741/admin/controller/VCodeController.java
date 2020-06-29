package hzu1741.admin.controller;

import hzu1741.admin.code.IVerifyCodeGen;
import hzu1741.admin.code.SimpleCharVerifyCodeGenImpl;
import hzu1741.admin.code.VerifyCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")//跨域传参
public class VCodeController
{
    @ApiOperation(value = "验证码")
    @RequestMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response)
    {

        Logger logger = Logger.getLogger("VerifyCode");
        logger.setLevel(Level.INFO);

        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            logger.info(code);

            //将VerifyCode绑定session
            request.getSession().setAttribute("VerifyCode", code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        }
        catch (IOException e) {
            logger.info("");
        }
    }

    @RequestMapping("/checkCode")
    @ResponseBody
    public boolean checkVerify(@RequestParam(name = "codeInput") String codeInput, HttpSession session)
    {


        String code = (String)session.getAttribute("VerifyCode");

        if(code.equals(codeInput)){
            return true;
        }
        else{
            return false;
        }
    }


}
