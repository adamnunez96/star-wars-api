package com.anunez.conexa.star.wars.config;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {

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
    public PoolingHttpClientConnectionManager connManager() {
        final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setDefaultMaxPerRoute(poolMaxPerRoute);
        cm.setMaxTotal(poolMaxTotal);
        return cm;
    }

    private ClientHttpRequestFactory getHttpClientRequestFactory(int socketTimeout, int connectTimeout, int connectionRequestTimeout,
                                                                 PoolingHttpClientConnectionManager connManager) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setResponseTimeout(Timeout.ofMilliseconds(socketTimeout))
                .setConnectTimeout(Timeout.ofMilliseconds(connectTimeout))
                .setConnectionRequestTimeout(Timeout.ofMilliseconds(connectionRequestTimeout))
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connManager).build();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Bean("swappiRestTemplate")
    public RestTemplate swappiRestTemplate() {
        return new RestTemplate(getHttpClientRequestFactory(swappiSocketTimeout, swappiConnectTimeout, swappiConnectionRequestTimeout, connManager()));
    }

}