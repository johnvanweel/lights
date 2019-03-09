package nl.johnvanweel.iot.lights.kodi.service;

import com.github.arteam.simplejsonrpc.client.JsonRpcClient;
import nl.johnvanweel.iot.lights.kodi.service.model.Player;
import nl.johnvanweel.iot.lights.kodi.service.model.PlayerInfo;
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
        return Arrays.asList(client.createRequest().method("Player.GetActivePlayers").id(1L).returnAsArray(Player.class).execute());
    }

    public PlayerInfo getPlayInfo(final Player player) {
        return client.createRequest().method("Player.GetProperties").id(1L)
                .param("playerid", player.getPlayerid())
                .param("properties", new String[]{"speed"})
                .returnAs(PlayerInfo.class)
                .execute();
    }
}
