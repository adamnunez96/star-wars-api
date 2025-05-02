package com.anunez.conexa.star.wars.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfiguration {

    @Value("${http.pool.maxTotal:30000}")
    private int poolMaxTotal;
    @Value("${http.pool.maxPerRoute:4000}")
    private int poolMaxPerRoute;

    @Value("${swappi.socketTimeout:15000}")
    private int swappiSocketTimeout;
    @Value("${swappi.connectTimeout:15000}")
    private int swappiConnectTimeout;
    @Value("${swappi.connectionRequestTimeout:15000}")
    private int swappiConnectionRequestTimeout;

    @Bean("connManager")
    public HttpClientConnectionManager connManager(){
        final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(poolMaxTotal);
        cm.setDefaultMaxPerRoute(poolMaxPerRoute);
        return cm;
    }

    private ClientHttpRequestFactory getHttpClientRequestFactory(int socketTimeout, int connectTimeout, int connectionRequestTimeout,
                                                                 HttpClientConnectionManager connManager) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .build();
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connManager).build();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Bean("swappiRestTemplate")
    public RestTemplate swappiRestTemplate() {
        return new RestTemplate(getHttpClientRequestFactory(swappiSocketTimeout, swappiConnectTimeout, swappiConnectionRequestTimeout, connManager()));
    }

}
