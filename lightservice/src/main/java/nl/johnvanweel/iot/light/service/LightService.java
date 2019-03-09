package nl.johnvanweel.iot.light.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.awt.*;

@Service
public class LightService {
    private final HyperionService service;

    @Autowired
    public LightService(final HyperionService service) {
        this.service = service;
    }

    @PostConstruct
    public void setDefault(){
        changeIntensity(1);
    }

    public void changeIntensity(final double value) {
        if (value < 0 || value > 1) {
            throw new IllegalArgumentException("Intensity value must be between 0 and 1");
        }

        final int rgbValue = (int) (255 * value);

        service.setColor(new Color(rgbValue, rgbValue, rgbValue));
    }
}
