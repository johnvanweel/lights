package nl.johnvanweel.iot.lights.kodi.config;

import com.github.arteam.simplejsonrpc.client.JsonRpcClient;
import org.apache.commons.codec.Charsets;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;

@Configuration
@PropertySource("classpath:kodi.properties")
public class RpcClientConfig {
    @Value("${kodi.uri}")
    private String kodiUri;
    @Value("${kodi.auth}")
    private String credentials;

    @Bean
    public JsonRpcClient rpcClient() {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        return new JsonRpcClient(request -> {
            HttpPost post = new HttpPost("http://" + kodiUri + "/jsonrpc");
            post.setEntity(new StringEntity(request, Charsets.UTF_8));
            post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            post.setHeader(HttpHeaders.ACCEPT, "application/json, text/javascript, */*; q=0.01");
            post.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + credentials);
            try (CloseableHttpResponse httpResponse = httpClient.execute(post)) {
                return EntityUtils.toString(httpResponse.getEntity(), Charsets.UTF_8);
            }
        });

    }
}
