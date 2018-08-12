package nl.johnvanweel.sensor.xbee.service;

import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.XBeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SensorService {
    private final String serialPort = "/dev/ttyUSB0";
    private final int serialBaudRate = 9600;

    private final XbeeSampleListener listener;

    @Autowired
    public SensorService(final XbeeSampleListener listener) {
        this.listener = listener;
    }

    @PostConstruct
    public void startListener() throws XBeeException {
        XBeeDevice xBee = new XBeeDevice(serialPort, serialBaudRate);
        xBee.open();
        xBee.addIOSampleListener(listener);
    }
}
