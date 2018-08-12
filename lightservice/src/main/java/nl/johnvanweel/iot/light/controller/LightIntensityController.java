package nl.johnvanweel.iot.light.controller;

import nl.johnvanweel.iot.light.service.LightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("intensity")
public class LightIntensityController {
    private final Logger log = LoggerFactory.getLogger(LightIntensityController.class);
    private final LightService lightService;

    @Autowired
    public LightIntensityController(LightService lightService) {
        this.lightService = lightService;
    }

    @PostMapping("/{value}")
    public void setLights(@PathVariable final double value) {
        log.info("Received new value: {}", value);
        lightService.changeIntensity(value);
    }
}
