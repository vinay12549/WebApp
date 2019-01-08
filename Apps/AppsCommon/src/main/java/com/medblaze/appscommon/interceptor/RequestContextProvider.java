package com.medblaze.appscommon.interceptor;

import com.medblaze.beans.model.RequestInfo;

public final class RequestContextProvider {

    private static final ThreadLocal<RequestInfo> REQUEST_CONTEXT_PROVIDER = new ThreadLocal<RequestInfo>();

    private RequestContextProvider() {
    }

    public static RequestInfo getRequestInfo() {
        return REQUEST_CONTEXT_PROVIDER.get();
    }

    public static void setRequestInfo(RequestInfo requestInfo) {
        REQUEST_CONTEXT_PROVIDER.set(requestInfo);
    }

    public static String getTenantName() {
        if(REQUEST_CONTEXT_PROVIDER.get()!=null){
            return REQUEST_CONTEXT_PROVIDER.get().getTenantName();
        }
        return "";
    }

    public static String getUsername() {
        if(REQUEST_CONTEXT_PROVIDER.get()!=null){
            return REQUEST_CONTEXT_PROVIDER.get().getUsername();
        }
        return "";
    }

}
