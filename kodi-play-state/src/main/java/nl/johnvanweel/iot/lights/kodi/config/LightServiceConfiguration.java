package nl.johnvanweel.iot.lights.kodi.config;

import nl.johnvanweel.iot.light.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan("nl.johnvanweel.iot.light")
public class LightServiceConfiguration {
    @Value("${lightservice.uri}")
    private String basePath;

    private final ApiClient apiClient;

    @Autowired
    public LightServiceConfiguration(final ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @PostConstruct
    public void alterUrl() {
        apiClient.setBasePath(basePath);
    }
}
