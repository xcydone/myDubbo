package com.crossyf.dubbo.springtest.test.testChat2;

import java.io.IOException;

public class BClient {
    public static void main(String[] args) throws IOException {
        new NioClient().start("BClient");
    }
}