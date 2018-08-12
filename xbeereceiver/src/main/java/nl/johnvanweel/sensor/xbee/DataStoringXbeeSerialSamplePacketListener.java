package nl.johnvanweel.sensor.xbee;

import com.digi.xbee.api.RemoteXBeeDevice;
import com.digi.xbee.api.io.IOLine;
import com.digi.xbee.api.io.IOSample;
import com.digi.xbee.api.listeners.IDataReceiveListener;
import com.digi.xbee.api.listeners.IIOSampleReceiveListener;
import com.digi.xbee.api.listeners.IPacketReceiveListener;
import com.digi.xbee.api.models.XBeeMessage;
import com.digi.xbee.api.packet.XBeePacket;
import com.digi.xbee.api.utils.HexUtils;


/**
 * Listener for Xbee serial connection packets.
 *
 * Stores the received data on the network.
 */
public class DataStoringXbeeSerialSamplePacketListener implements IIOSampleReceiveListener {
	@Override
	public void ioSampleReceived(RemoteXBeeDevice remoteXBeeDevice, IOSample ioSample) {
        long analogValue = ioSample.getAnalogValue(IOLine.DIO1_AD1);
		double percentage = (analogValue) / (double) (1023);
        System.out.println(percentage*100 + "%");
//		ioSample.getAnalogValues().forEach((ioLine, integer) -> System.out.println(ioLine + " - " + integer));
	}
}
