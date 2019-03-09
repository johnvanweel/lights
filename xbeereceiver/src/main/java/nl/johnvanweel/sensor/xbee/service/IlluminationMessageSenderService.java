package nl.johnvanweel.sensor.xbee.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IlluminationMessageSenderService {
    private final Logger log = LoggerFactory.getLogger(IlluminationMessageSenderService.class);
    private final String uri = "http://192.168.0.24:8080/intensity/";

    public void sendMessage(final double value){
        log.info("Sending new value to {}", uri);

        RestTemplate template = new RestTemplate();
        template.postForLocation(uri+value, null);
    }
}
