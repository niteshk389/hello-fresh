package com.hellofresh.models;

import java.util.HashMap;
import java.util.Map;

public class RequestModel {
    private String url;
    private Map<String, String> params;
    private Map<String, String> cookies;
    private Map<String, String> headers;
    private Boolean followRedirect;
    private String rawRequest;
    private Map<String, String> postQueryParams;

    public RequestModel(final String urlValue) {
        this.url = urlValue;
        params = new HashMap<>();
        cookies = new HashMap<>();
        headers = new HashMap<>();
        followRedirect = Boolean.TRUE;
    }

    public final void addHeader(final String name, final String value) {
        this.headers.put(name, value);
    }

    public final void addCookie(final String name, final String value) {
        this.cookies.put(name, value);
    }

    public final void addParam(final String name, final String value) {
        this.params.put(name, value);
    }

    public final void addPostQueryParam(final String name, final String value) {
        this.postQueryParams.put(name, value);
    }

    public final String getUrl() {
        return url;
    }

    public final Map<String, String> getParams() {
        return params;
    }

    public final Map<String, String> getPostQueryParams() {
        return params;
    }

    public final Map<String, String> getCookies() {
        return cookies;
    }

    public final Map<String, String> getHeaders() {
        return headers;
    }

    public final Boolean getFollowRedirect() {
        return followRedirect;
    }

    public final void setFollowRedirect(final Boolean redirect) {
        this.followRedirect = redirect;
    }

    public final String getRawRequest() {
        return rawRequest;
    }

    public final void setRawRequest(final String request) {
        this.rawRequest = request;
    }

    @Override
    public final String toString() {
        return "RequestModel{"
                       + "url='" + url + '\''
                       + ", params=" + params
                       + ", cookies=" + cookies
                       + ", headers=" + headers
                       + ", followRedirect=" + followRedirect
                       + ", rawRequest='" + rawRequest + '\''
                       + ", postQueryParams=" + postQueryParams
                       + '}';
    }
}
