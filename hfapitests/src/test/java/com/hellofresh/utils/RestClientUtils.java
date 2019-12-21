package com.hellofresh.utils;

import com.hellofresh.constants.CommonConstants;
import com.hellofresh.models.RequestModel;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestClientUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestClientUtils.class);

    /**
     * Prepares the Httpcomponents required to make a GET call. Depends on
     * RequestModel inputs.
     *
     * @param reqModel
     * @return
     */
    public final Map<String, Object> doGET(final RequestModel reqModel, boolean redirectsAllowed) {

        LOGGER.info("**************************Creating an HTTP GET request for endpointURL : Start************************");
        URI endpointURI = createEndpointURI(reqModel.getUrl(), reqModel.getParams());
        LOGGER.info("endpointURI : " + endpointURI.getRawPath());
        Map<String, Object> responseMap = new HashMap<>();
        HttpGet getRequest = new HttpGet(endpointURI);
        // Add request headers
        LOGGER.info("Setting the GET request headers to be sent." + reqModel.getHeaders());
        setGETHeaders(getRequest, reqModel.getHeaders());

        // Perform the GET request to endpoint
        LOGGER.info("GET request Cookies : " + reqModel.getCookies());
        LOGGER.info("GET request body" + reqModel.getRawRequest());
        CloseableHttpClient closeableHttpClient = getHttpsClient(redirectsAllowed);

        try {
            LOGGER.info("Inside Try to fetch response.");
            HttpContext context = new BasicHttpContext();
            HttpResponse httpGetResponse = closeableHttpClient.execute(getRequest, context);
            HttpUriRequest currentReq = (HttpUriRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
            HttpHost currentHost = (HttpHost) context.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
            String responseURL = currentHost.toURI() + currentReq.getURI().toString();
            LOGGER.info("GET request has returned a response. Response URL : " + responseURL);
            // Place all the response params in a map
            responseMap.put(CommonConstants.HTTP_RESPONSE_URL, responseURL);
            responseMap.put(CommonConstants.HTTP_HEADERS, httpGetResponse.getAllHeaders());
            responseMap.put(CommonConstants.HTTP_STATUS_CODE, httpGetResponse.getStatusLine().getStatusCode());
            if (httpGetResponse.getEntity() == null) {
                responseMap.put(CommonConstants.HTTP_RESPONSE_BODY, "Response is Empty");
            } else {
                responseMap.put(CommonConstants.HTTP_RESPONSE_BODY, EntityUtils.toString(httpGetResponse.getEntity()));
            }
        } catch (Exception e) {
            getRequest.abort();
            e.printStackTrace();
            LOGGER.error("ERROR: HTTP Request failed and has been aborted." + getRequest.getURI(), e);
            Assert.fail("ERROR: HTTP Request failed and has been aborted. URI: " + getRequest.getURI() + " Reason: " + e.toString());
        } finally {
            LOGGER.info("Releasing the connections associated with resource");
            getRequest.releaseConnection();
        }
        LOGGER.info("**************************Creating an HTTP GET request for endpointURL : End************************");
        return responseMap;
    }

    public final Map<String, Object> doPOST(final RequestModel reqModel, boolean redirectsAllowed) {
        URI endpointURI = createEndpointURI(reqModel.getUrl(), reqModel.getParams());
        LOGGER.info(endpointURI.getRawPath());
        Map<String, Object> responseMap = new HashMap<>();

        HttpPost postRequest = new HttpPost(endpointURI);

        LOGGER.info("**************************Setting Data for POST Request Start************************");
        // Add request headers
        LOGGER.info("Setting the POST request headers to be sent." + reqModel.getHeaders());
        setPOSTHeaders(postRequest, reqModel.getHeaders());

        try {
            // Add logic to put Data payload in Post request.
            LOGGER.info("Setting the POST request body" + reqModel.getRawRequest());
            setPOSTBody(postRequest, reqModel.getRawRequest());

            // Perform the POST request to endpoint
            LOGGER.info("Rest URI is : " + postRequest.getURI().toString());
            LOGGER.info("Rest Headers : " + reqModel.getHeaders().toString());
            LOGGER.info("**************************Setting Data for POST Request Stop************************");
            CloseableHttpClient closeableHttpClient = getHttpsClient(redirectsAllowed);
            HttpClientContext context = HttpClientContext.create();
            HttpResponse httpPostResponse = closeableHttpClient.execute(postRequest, context);
            LOGGER.info("POST request has returned a response." + httpPostResponse.toString());

            // Get the cookies from the response
            CookieStore cookieStore = context.getCookieStore();
            List<Cookie> cookies = cookieStore.getCookies();
            if (cookies != null && !cookies.isEmpty()) {
                LOGGER.info("Cookies came with the response. Iterating over cookies and adding to response map.");
                for (Cookie cookie : cookies) {
                    responseMap.put(cookie.getName(), cookie.getValue());
                    responseMap.put(cookie.getName() + ".domain", cookie.getDomain());
                    LOGGER.info("Added following cookie to responsemap : " + cookie.getName());
                }
            } else {
                LOGGER.info("No cookies were in the response for this POST request");
            }
            // Place all the response params in a map
            responseMap.put(CommonConstants.HTTP_HEADERS, httpPostResponse.getAllHeaders());
            responseMap.put(CommonConstants.HTTP_STATUS_CODE, httpPostResponse.getStatusLine().getStatusCode());
            responseMap.put(CommonConstants.HTTP_RESPONSE_BODY, EntityUtils.toString(httpPostResponse.getEntity()));
        } catch (Exception e) {
            postRequest.abort();
            e.printStackTrace();
            LOGGER.error("ERROR: HTTP Request failed and has been aborted." + postRequest.getURI(), e);
            Assert.fail("ERROR: HTTP Request failed and has been aborted. URI: " + postRequest.getURI() + " Reason: " + e.toString());
        } finally {
            LOGGER.info("Releasing the connections associated with resource");
            postRequest.releaseConnection();
        }

        return responseMap;
    }

    /**
     * Sets the HTTP header for an HTTP POST request object
     *
     * @param request
     * @param httpHeaders
     */
    private void setPOSTHeaders(final HttpPost request, final Map<String, String> httpHeaders) {
        if (httpHeaders != null && !httpHeaders.isEmpty()) {
            for (Map.Entry<String, String> entry : httpHeaders.entrySet()) {
                request.setHeader(entry.getKey(), entry.getValue());
            }
        }
    }


    /**
     * @param request
     * @param rawRequest
     * @throws UnsupportedEncodingException
     */
    private void setPOSTBody(final HttpPost request, final String rawRequest) throws UnsupportedEncodingException {
        if (rawRequest != null) {
            // request.setEntity(new StringEntity(rawRequest));

            request.setEntity(new StringEntity(rawRequest, ContentType.APPLICATION_JSON));
        }
    }


    /**
     * Sets the HTTP header for an HTTP POST request object
     *
     * @param request
     * @param httpHeaders
     */
    private void setGETHeaders(final HttpGet request, final Map<String, String> httpHeaders) {
        if (httpHeaders != null && !httpHeaders.isEmpty()) {
            for (Map.Entry<String, String> entry : httpHeaders.entrySet()) {
                request.setHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Add query strings parameter to REST end point.
     *
     * @param endPointURL
     * @param queryParams
     * @return
     */
    private URI createEndpointURI(final String endPointURL, final Map<String, String> queryParams) {
        URI uri = null;

        // If query parameters are not empty then create the query string to
        // attach to URL.
        String endPointURLAppend = endPointURL;
        if (queryParams != null && !queryParams.isEmpty()) {
            StringBuilder queryString = new StringBuilder();
            // Iterate through Query parameter map and create a combined string.
            for (Map.Entry<String, String> qParam : queryParams.entrySet()) {
                if (queryString.length() > 0) {
                    queryString.append('&');
                }
                queryString.append(String.format("%s=%s", qParam.getKey(), qParam.getValue()));
            }
            endPointURLAppend = endPointURLAppend + "?" + queryString;
        }
        LOGGER.info("URL endpoint created : " + endPointURLAppend);
        uri = URI.create(endPointURLAppend);
        return uri;
    }

    private CloseableHttpClient getHttpsClient(boolean allowRedirect) {
        HttpClientBuilder builder;
        builder = HttpClients.custom();

        RequestConfig globalConfig = RequestConfig.custom()
                                             .setCircularRedirectsAllowed(allowRedirect).build();
        builder.setDefaultRequestConfig(globalConfig);
        return builder.build();
    }
}
