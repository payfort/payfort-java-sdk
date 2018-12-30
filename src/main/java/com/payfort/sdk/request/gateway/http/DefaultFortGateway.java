package com.payfort.sdk.request.gateway.http;

import com.payfort.sdk.exception.FortException;
import com.payfort.sdk.request.gateway.FortGateway;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


/**
 * Implementation for @{@link FortGateway } interface using Apache HttpClient
 * *** In case you don't like to have Apache HttpClient in your classpath then set any implementation for calling fort using {@link com.payfort.sdk.FortRequestConfiguration}
 */
final public class DefaultFortGateway implements FortGateway {

    private static final String CONTENT_TYPE = "content-type";
    private final String url;
    private final String contentType;

    public DefaultFortGateway(String url, String contentType) {
        this.url = url;
        this.contentType = contentType;
    }

    public String send(String content) {
        try {
            HttpUriRequest request = prepareRequest(content);
            return sendRequest(request);
        } catch (IOException ex) {
            throw new FortException("Error while calling fort : " + url, ex);
        }
    }

    private HttpPost prepareRequest(String content) throws UnsupportedEncodingException {
        HttpPost request = new HttpPost(url);
        StringEntity params = new StringEntity(content);
        request.addHeader(CONTENT_TYPE, contentType);
        request.setEntity(params);
        return request;
    }

    private String sendRequest(HttpUriRequest request) throws IOException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build()) {
            return processResponse(httpClient.execute(request));
        }
    }

    private String processResponse(HttpResponse response) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (InputStreamReader inputStreamReader = new InputStreamReader(response.getEntity().getContent());
             BufferedReader reader = new BufferedReader(inputStreamReader,Short.MAX_VALUE)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        }
    }
}
