package com.crossyf.dubbo.springtest.test.interfaceClass;

public class SportsImpl implements Sports{
    @Override
    public void setHomeTeam(String name) {
        System.out.println("SportsImpl Class setHomeTeam method");
    }

    @Override
    public void setVisitingTeam(String name) {
        System.out.println("SportsImpl Class setVisitingTeam method");
    }
}
