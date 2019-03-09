package nl.johnvanweel.iot.lights.kodi.service;

import com.github.arteam.simplejsonrpc.client.JsonRpcClient;
import com.github.arteam.simplejsonrpc.client.builder.RequestBuilder;
import nl.johnvanweel.iot.lights.kodi.service.model.Player;
import nl.johnvanweel.iot.lights.kodi.service.model.PlayerInfo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class KodiConnector {
    private final JsonRpcClient client;

    @Autowired
    public KodiConnector(final JsonRpcClient client) {
        this.client = client;
    }

    public List<Player> retrievePlayers() {
        return Arrays.asList(createRequest("Player.GetActivePlayers").returnAsArray(Player.class).execute());
    }

    public PlayerInfo getPlayInfo(final Player player) {
        return createRequest("Player.GetProperties")
                .param("playerid", player.getPlayerid())
                .param("properties", new String[]{"speed"})
                .returnAs(PlayerInfo.class)
                .execute();
    }

    @NotNull
    private RequestBuilder<Object> createRequest(String method) {
        return client.createRequest().method(method).id(1L);
    }
}
