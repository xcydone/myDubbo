package com.crossyf.dubbo.springtest.test.testExam;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Scanner;

public class calcaulate {

    public static void main(String[] args) throws Exception{

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String expression = scanner.nextLine();
            expression = expression.replaceAll("\\{","(")
                    .replaceAll("\\}",")")
                    .replaceAll("\\[","(")
                    .replaceAll("\\]",")");

            ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
            ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");

            String result = String.valueOf(scriptEngine.eval(expression));
            System.out.println(result);
        }
    }
}
