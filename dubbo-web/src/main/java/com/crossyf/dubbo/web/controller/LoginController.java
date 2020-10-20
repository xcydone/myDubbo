package com.crossyf.dubbo.web.controller;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.crossyf.dubbo.common.captcha.DubboCaptcha;
import com.crossyf.dubbo.common.constant.CaptchaConstant;
import com.crossyf.dubbo.common.enums.ResultEnum;
import com.crossyf.dubbo.common.response.Result;
import com.crossyf.dubbo.common.utils.JWTUtil;
import com.crossyf.dubbo.common.utils.ServletUtil;
import com.crossyf.dubbo.first.api.IFirstApi;
import com.crossyf.dubbo.first.dto.OperDto;
import com.crossyf.dubbo.web.dto.LoginDto;
import com.crossyf.dubbo.web.dto.LoginRespDto;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {
    @Autowired
    private JWTUtil jwtUtil;

    private SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, CaptchaConstant.key.getBytes());

    @ApiOperation("获取随机数字")
    @GetMapping("/onReady")
    public Result onReady(){
        String randomNum = RandomUtil.randomString(CaptchaConstant.baseString,4);
        return Result.ok(aes.encryptHex(randomNum));
    }

    @ApiOperation(value = "获取验证码写到浏览器（输出到servlet）")
    @GetMapping("/getCaptcha")
    public void getCaptcha(@RequestParam("randnum") String randnum) {
        //解密后的字符串
        String decryptStr = aes.decryptStr(randnum, CharsetUtil.CHARSET_UTF_8);

        //4位数，150个干扰项
        DubboCaptcha captcha = new DubboCaptcha(100, 50, 4, 150, decryptStr);
        try {
            OutputStream out = ServletUtil.getRequestAttributes().getResponse().getOutputStream();
            captcha.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "登录")
    @PostMapping("/loginIn")
    public Result loginIn(@RequestBody LoginDto loginDto){
        String verificationCode = loginDto.getVerificationCode();
        String randomNum = loginDto.getRandnum();
        if(aes.encryptHex(verificationCode).equals(randomNum)){
            OperDto operDto = new OperDto();  // 后期从数据库中获取
            operDto.setOperId(1001);
            operDto.setOperName("root");

            LoginRespDto loginRespDto = new LoginRespDto();
            loginRespDto.setOperId(operDto.getOperId());
            loginRespDto.setOperName(operDto.getOperName());

            putValueToToken(ServletUtil.getRequestAttributes().getResponse(), loginRespDto);
            return Result.ok(loginRespDto);

        }else{
            return Result.fail("验证码错误");
        }
    }

    /**
     * 往session里存值
     * @param response http响应
     * @param loginRespDto 登录返回实体类
     */
    private void putValueToToken(HttpServletResponse response, LoginRespDto loginRespDto) {
        // 放入token的内容
        Map<String, Object> map = new HashMap<>();
        map.put("operId", loginRespDto.getOperId());

        // 工具类jwtUtil 创建token
        String token = jwtUtil.createJWT(map);
        response.setHeader("Access-Control-Expose-Headers","token");
        response.setHeader("Cache-Control","no-store");
        response.setHeader("token", token);
    }

    @ApiOperation(value = "拦截跳转登录")
    @GetMapping("/toLogin")
    public Result toLogin() {
        ResultEnum resultEnum = ResultEnum.TOKEN_ERROR;
        return Result.fail(resultEnum.getCode(), resultEnum.getMsg());
    }
}
