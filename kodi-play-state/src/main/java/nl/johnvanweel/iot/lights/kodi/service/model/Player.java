package nl.johnvanweel.iot.lights.kodi.service.model;

public class Player {
    private Integer playerid;
    private String type;

    public Integer getPlayerid() {
        return playerid;
    }

    public void setPlayerid(Integer playerid) {
        this.playerid = playerid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerid='" + playerid + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
