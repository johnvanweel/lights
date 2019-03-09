package nl.johnvanweel.iot.lights.kodi.service;

import nl.johnvanweel.iot.light.api.LightDimControllerApi;
import nl.johnvanweel.iot.lights.kodi.service.model.Player;
import nl.johnvanweel.iot.lights.kodi.service.model.PlayerInfo;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class KodiPlayStateService {
    private final Logger log = LoggerFactory.getLogger(KodiPlayStateService.class);
    private final KodiConnector connector;
    private final LightDimControllerApi api;

    @Autowired
    public KodiPlayStateService(final KodiConnector connector, final LightDimControllerApi api) {
        this.connector = connector;
        this.api = api;
    }

    @Scheduled(fixedRate = 1000L)
    public void checkPlayState() {
        try {
            double dimValue = connector.retrievePlayers().stream()
                    .filter(videoPlayers())
                    .map(connector::getPlayInfo)
                    .map(this::toDimFactor)
                    .findFirst()
                    .orElse(1.0D);

            log.info("Dimming lights to {}", dimValue);

            api.dimLightsUsingPOST(dimValue);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private double toDimFactor(PlayerInfo playerInfo) {
        return playerInfo.getSpeed() == 1 ? .2D : 1.0D;
    }

    @NotNull
    private Predicate<Player> videoPlayers() {
        return player -> "video".equals(player.getType());
    }
}
