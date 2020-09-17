package com.crossyf.dubbo.springtest.aop;

import com.alibaba.fastjson.JSONObject;
import com.crossyf.dubbo.springtest.constant.DemoConstants;
import com.crossyf.dubbo.springtest.exception.GlobalException;
import com.crossyf.dubbo.springtest.utils.JSONUtils;
import com.crossyf.dubbo.springtest.utils.LoggerUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SignAop {
    /**
     * 声明一个切入点，范围为controller包下所有的类
     * 注：作为切入点签名的方法必须返回void类型
     */
    @Pointcut("execution(public * com.crossyf.dubbo.springtest.controller.*.*(..))")
    private void signAop() {

    }

    /**
     * 前置通知：在某连接点之前执行的通知，但这个通知不能阻止连接点之前的执行流程（除非它抛出一个异常）
     * 验签
     *
     * @param joinPoint
     * @throws Exception
     */
    /*@Before("signAop()")
    public void doBefore(JoinPoint joinPoint) throws Exception {

        Object[] objects = joinPoint.getArgs();
        String sign = objects[0].toString();
        String timestamp = objects[1].toString();
        String data = objects[2].toString();

        if (com.crossyf.dubbo.springtest.utils.StringUtils.isEmpty(sign) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(data)) {
            throw new GlobalException("sign or timestamp or data is null");
        }

        // 设置日志参数
        String strLog = "[" + Thread.currentThread().getId() + "]" + "[请求方法] " + joinPoint.getSignature().getName() + " ||";
        LoggerUtils.getLogger().info(strLog + "[请求参数] sign=" + sign + ",timestamp=" + timestamp + ",data=" + data);

        String md5String = "data=" + data + "&key=1234567890&timestamp=" + timestamp;
        String signNow = DigestUtils.md5Hex(md5String);

        if (!sign.equalsIgnoreCase(signNow)) {
            throw new GlobalException("sign is error");
        }
    }

    *//**
     * 后置通知：在某连接点正常完成后执行的通知，通常在一个匹配的方法返回的时候执行
     * 对接口返回参数进行签名
     *
     * @param joinPoint
     * @param params
     * @return
     *//*
    @AfterReturning(value = "signAop()", returning = "params")
    public JSONObject doAfterReturning(JoinPoint joinPoint, JSONObject params) {
        String data = JSONUtils.getJSONString(params, DemoConstants.DATA);
        long timestamp = System.currentTimeMillis() / 1000;

        String md5String = "data=" + data + "&key=1234567890&timestamp=" + timestamp;
        String sign = DigestUtils.md5Hex(md5String);

        params.put(DemoConstants.TIMESTAMP, timestamp);
        params.put(DemoConstants.SIGN, sign);

        String strLog = "[" + Thread.currentThread().getId() + "]" + "[请求方法] " + joinPoint.getSignature().getName() + " ||";
        LoggerUtils.getLogger().info(strLog + "[返回参数] " + params);

        return params;
    }*/

}
