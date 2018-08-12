package nl.johnvanweel.sensor.xbee;

import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.XBeeException;

public class Main {
    private static String serialPort = "/dev/ttyUSB0";
//    private static String serialPort = "COM3";
    private static int serialBaudRate = 9600;

    public static final void main (String...args) throws InterruptedException, XBeeException {

        new XbeeSensorReader(new XBeeDevice(serialPort, serialBaudRate), new DataStoringXbeeSerialSamplePacketListener()).startSensorReceiverThread();

        Thread.sleep(90000000000L);
    }
}
