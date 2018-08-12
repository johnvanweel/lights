package nl.johnvanweel.sensor.xbee;

import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.XBeeException;

/**
 *
 */
public class XbeeSensorReader {


    private final XBeeDevice xBee;
    private final DataStoringXbeeSerialSamplePacketListener listener;

    private String serialPort = "/dev/ttyUSB0";
    private int serialBaudRate = 9600;

    public XbeeSensorReader(XBeeDevice xBee, DataStoringXbeeSerialSamplePacketListener listener) {
        this.xBee = xBee;
        this.listener = listener;
    }

    public void startSensorReceiverThread() throws XBeeException {
        xBee.open();
        xBee.addIOSampleListener(listener);
    }

    public void preDestroy() {
        xBee.close();
    }

    public void setSerialPort(String serialPort) {
        this.serialPort = serialPort;
    }

    public void setSerialBaudRate(int serialBaudRate) {
        this.serialBaudRate = serialBaudRate;
    }
}
