package com.meteor.untils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/30 22:29
 * @description：启动时的操作
 * @modified By：
 * @version: $
 */
@Component
public class StartPing implements CommandLineRunner {
    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("**********************************************");
    }
}
