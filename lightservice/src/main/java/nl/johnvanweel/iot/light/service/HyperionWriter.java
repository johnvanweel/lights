package nl.johnvanweel.iot.light.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import proto.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Component
public class HyperionWriter {
    public static final int PORT = 19445;

    private final Logger log = LoggerFactory.getLogger(HyperionWriter.class);
    private final String serverAddress;

    private Socket hyperionSocket;

    @Scheduled(fixedRate = 5000L)
    public void reconnect() {
        if (!hasSocketConnection()) {
            try {
                log.info("Attempting to connect ot {}:{}", serverAddress, PORT);
                hyperionSocket = new Socket(serverAddress, PORT);
            } catch (IOException e) {
                log.error("Cannot connect to hyperion service.", e);
            }
        }
    }

    private boolean hasSocketConnection() {
        return hyperionSocket != null && hyperionSocket.isConnected();
    }

    @Autowired
    public HyperionWriter(@Value("${server}") final String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public synchronized void sendRequest(Message.HyperionRequest request) throws IOException {
        if (!hasSocketConnection()) {
            log.warn("Cannot process request, no socket connection present");
            return;
        }

        OutputStream output = hyperionSocket.getOutputStream();
        output.write(createHeader(request.getSerializedSize()));
        request.writeTo(output);
        output.flush();

        if (!receiveReply().getSuccess()) {
            log.error("Did not get success reply from hyperion.");
        }
    }

    private byte[] createHeader(int size) {
        byte[] header = new byte[4];
        header[0] = (byte) ((size >> 24) & 0xFF);
        header[1] = (byte) ((size >> 16) & 0xFF);
        header[2] = (byte) ((size >> 8) & 0xFF);
        header[3] = (byte) ((size) & 0xFF);
        return header;
    }

    private Message.HyperionReply receiveReply() throws IOException {
        InputStream input = hyperionSocket.getInputStream();

        byte[] header = new byte[4];
        input.read(header, 0, 4);
        int size = (header[0] << 24) | (header[1] << 16) | (header[2] << 8) | (header[3]);
        byte[] data = new byte[size];
        input.read(data, 0, size);
        Message.HyperionReply reply = Message.HyperionReply.parseFrom(data);

        return reply;
    }
}
