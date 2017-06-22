package org.invoice.session;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by 李浩然 on 2017/5/17.
 */
public class SessionListener implements HttpSessionListener{
    public static SessionContext sessionContext = SessionContext.getInstance();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        sessionContext.addSession(httpSessionEvent.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        sessionContext.delSession(httpSessionEvent.getSession());
    }
}
