package com.crossyf.dubbo.common.constant;

/**
 * @author lsl
 */
public class CaptchaConstant {
    //验证码秘钥
    public static final String key = "46EBA22EF5204DD5B110A1F730513965";

    //跳转登录秘钥
    public static final String jumpLoginKey = "26ECA22EB5204A51B110A1F730513861";

    //随机验证码取值范围
    public static final String baseString = "abcdefghkmnprstuvwxyz23456789";

    //初始密码加密后文本123456aBC
    // 使用hutool中,SecureUtil.md5("123456aBC")生成
    public static final String initialPwd = "9624fe5809b00f507989097f040d4339";


}
