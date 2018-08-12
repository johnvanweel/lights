package nl.johnvanweel.iot.light.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import proto.Message;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Service
public class HyperionService {
    private final Socket mSocket;

    @Autowired
    public HyperionService(@Value("${server}") final String server) throws IOException {
        mSocket = new Socket(server, 19445);
    }

    public void setColor(final Color color) {
        Message.ColorRequest colorRequest = Message.ColorRequest.newBuilder()
                .setRgbColor(color.getRGB())
                .setPriority(50)
                .setDuration(-1)
                .build();

        Message.HyperionRequest request = Message.HyperionRequest.newBuilder()
                .setCommand(Message.HyperionRequest.Command.COLOR)
                .setExtension(Message.ColorRequest.colorRequest, colorRequest)
                .build();

        try {
            sendRequest(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clear(final int priority) throws IOException {
        Message.ClearRequest clearRequest = Message.ClearRequest.newBuilder()
                .setPriority(priority)
                .build();

        Message.HyperionRequest request = Message.HyperionRequest.newBuilder()
                .setCommand(Message.HyperionRequest.Command.CLEAR)
                .setExtension(Message.ClearRequest.clearRequest, clearRequest)
                .build();

        sendRequest(request);
    }


    private void sendRequest(Message.HyperionRequest request) throws IOException {
        int size = request.getSerializedSize();

        // create the header
        byte[] header = new byte[4];
        header[0] = (byte) ((size >> 24) & 0xFF);
        header[1] = (byte) ((size >> 16) & 0xFF);
        header[2] = (byte) ((size >> 8) & 0xFF);
        header[3] = (byte) ((size) & 0xFF);

        // write the data to the socket
        OutputStream output = mSocket.getOutputStream();
        output.write(header);
        request.writeTo(output);
        output.flush();

        Message.HyperionReply reply = receiveReply();
        if (!reply.getSuccess()) {
            System.out.println(reply.toString());
        }
    }

    private Message.HyperionReply receiveReply() throws IOException {
        InputStream input = mSocket.getInputStream();

        byte[] header = new byte[4];
        input.read(header, 0, 4);
        int size = (header[0] << 24) | (header[1] << 16) | (header[2] << 8) | (header[3]);
        byte[] data = new byte[size];
        input.read(data, 0, size);
        Message.HyperionReply reply = Message.HyperionReply.parseFrom(data);

        return reply;
    }
}
