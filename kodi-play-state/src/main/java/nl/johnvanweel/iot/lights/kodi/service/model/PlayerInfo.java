package nl.johnvanweel.iot.lights.kodi.service.model;

public class PlayerInfo {
    private Double speed;

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "speed='" + speed + '\'' +
                '}';
    }
}
