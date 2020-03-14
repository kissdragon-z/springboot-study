package com.example.springbootstudy.self;

public abstract class SelfBaseService {

    public void execute(String json) {

        try {
            before(json);

            String check = equalsCheck(json);

            after(check);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void before(String json) {

        System.out.println("before json =" + json);

    }

    public abstract String equalsCheck(String param) throws Exception;

    public void after(String checkResult) {

        System.out.println("checkResult=" + checkResult);

    }

}
