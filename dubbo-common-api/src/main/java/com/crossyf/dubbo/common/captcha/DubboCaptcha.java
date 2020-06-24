package com.crossyf.dubbo.common.captcha;

import cn.hutool.captcha.LineCaptcha;

public class DubboCaptcha extends LineCaptcha {

    public DubboCaptcha(int width, int height, int codeCount, int interfereCount, String code) {
        super(width, height, codeCount, interfereCount);
        this.code = code;
    }

    @Override
    public void generateCode() {

    }
}
