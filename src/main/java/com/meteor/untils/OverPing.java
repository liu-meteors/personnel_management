package com.meteor.untils;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/30 22:35
 * @description：关闭时的操作
 * @modified By：
 * @version: $
 */
@Component
public class OverPing implements DisposableBean {
    /**
     * Invoked by the containing {@code BeanFactory} on destruction of a bean.
     *
     * @throws Exception in case of shutdown errors. Exceptions will get logged
     *                   but not rethrown to allow other beans to release their resources as well.
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("关闭服务器");
    }
}
