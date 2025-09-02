package tp.mike.model;

import java.util.UUID;

public class PlayerData {

    private UUID uuid;

    private String name;

    private int coin;

    public PlayerData(UUID uuid, String name, int coin) {
        this.uuid = uuid;
        this.name = name;
        this.coin = coin;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}
