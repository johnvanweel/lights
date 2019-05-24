package nl.johnvanweel.iot.lights.kodi.service.model;

public class Player {
    private Integer playerid;
    private String type;
    private String playertype;

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

    public String getPlayertype() {
        return playertype;
    }

    public void setPlayertype(String playertype) {
        this.playertype = playertype;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerid=" + playerid +
                ", type='" + type + '\'' +
                ", playertype='" + playertype + '\'' +
                '}';
    }
}
