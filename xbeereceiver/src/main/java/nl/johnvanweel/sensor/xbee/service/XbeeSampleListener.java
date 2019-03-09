package nl.johnvanweel.sensor.xbee.service;

import com.digi.xbee.api.RemoteXBeeDevice;
import com.digi.xbee.api.io.IOLine;
import com.digi.xbee.api.io.IOSample;
import com.digi.xbee.api.listeners.IIOSampleReceiveListener;
import nl.johnvanweel.iot.light.api.LightIntensityControllerApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Listener for Xbee serial connection packets.
 * <p>
 * Stores the received data on the network.
 */
@Component
public class XbeeSampleListener implements IIOSampleReceiveListener {
    private final Logger log = LoggerFactory.getLogger(XbeeSampleListener.class);
    private final LightIntensityControllerApi api;

    @Autowired
    public XbeeSampleListener(final LightIntensityControllerApi api) {
        this.api = api;
    }

    @Override
    public void ioSampleReceived(RemoteXBeeDevice remoteXBeeDevice, IOSample ioSample) {
        long analogValue = ioSample.getAnalogValue(IOLine.DIO1_AD1);
        double percentage = (analogValue) / (double) (1023);
        double inverse = ((double) 1) - percentage;
        log.info("Sending new value {} to {}", inverse, api.getApiClient().getBasePath());

        api.setLightsUsingPOST(inverse);
    }
}
