package com.crossyf.dubbo.springtest.test.testNIO;

import java.io.IOException;

public class AClient {
    public static void main(String[] args) throws IOException {
        new NioClient().start("AClient");
    }
}
