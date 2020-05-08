package com.meteor.pojo;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/5/7 0:02
 * @description：
 * @modified By：
 * @version: $
 */
public class ManagerSessions {
    private Map<String, HttpSession> sessions=new HashMap<String,HttpSession>();

    public Map<String, HttpSession> getSessions() {
        return sessions;
    }

    public void setSessions(Map<String, HttpSession> sessions) {
        this.sessions = sessions;
    }

}

