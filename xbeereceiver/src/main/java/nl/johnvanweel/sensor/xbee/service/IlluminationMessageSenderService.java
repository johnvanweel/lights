package nl.johnvanweel.sensor.xbee.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IlluminationMessageSenderService {
    final String uri = "http://192.168.0.21:8080/intensity/";

    public void sendMessage(final double value){
        RestTemplate template = new RestTemplate();
        template.postForLocation(uri+value, null);
    }
}
