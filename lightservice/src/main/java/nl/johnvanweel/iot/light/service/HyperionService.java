package nl.johnvanweel.iot.light.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proto.Message;

import java.awt.*;
import java.io.IOException;

@Service
public class HyperionService {
    private final HyperionWriter hyperionWriter;

    @Autowired
    public HyperionService(HyperionWriter hyperionWriter) {
        this.hyperionWriter = hyperionWriter;
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
            hyperionWriter.sendRequest(request);
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

        hyperionWriter.sendRequest(request);
    }



}
