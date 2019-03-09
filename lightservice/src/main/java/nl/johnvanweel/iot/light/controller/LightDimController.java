package nl.johnvanweel.iot.light.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import nl.johnvanweel.iot.light.service.LightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dim")
@Api(value = "/dim", description = "Dims lights")
public class LightDimController {
    private final Logger log = LoggerFactory.getLogger(LightDimController.class);
    private final LightService lightService;

    @Autowired
    public LightDimController(final LightService lightService) {
        this.lightService = lightService;
    }

    @PostMapping("/{value}")
    @ApiOperation("dimLights")
    @ApiResponse(code = 200, message = "Lights changed")
    public void dimLights(@PathVariable final double value) {
        log.info("Dimming lights to {}", value);
        lightService.dimLights(value);
    }
}
