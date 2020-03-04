package com.ruyo.geotclient.controller.Utility;

import javax.servlet.http.HttpSession;

@SuppressWarnings("unchecked")
public class CustomSession<T> {

    private final HttpSession session;
    private T attribute;
    private final String attSessionCustom = "cliente";

    public CustomSession(HttpSession session) {
        this.session = session;
    }

    protected HttpSession getSession() {
        return session;
    }

    protected void setAttributeSession(String attibute){
        this.attribute = (T) session.getAttribute(attibute);
    }

    protected T getAttributeSession(){
        return attribute;
    }

    protected T getClienteSession(){
        return (T) session.getAttribute(attSessionCustom);
    }

    protected void setCustomSessionObject(T o){
        session.setAttribute(attSessionCustom, o);
        session.setMaxInactiveInterval(120);
    }

    protected void setSessionObject(String attribute, T o){
        session.setAttribute(attribute, o);
        session.setMaxInactiveInterval(120);
    }
}
