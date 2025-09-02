package tp.mike.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import tp.mike.model.PlayerData;

public class PlayerDataManager {

    private Map<UUID, PlayerData> players;
    private Map<String, UUID> playerNames;

    public PlayerDataManager(){
        players = new HashMap<>();
    }

    public Map<UUID, PlayerData> getPlayers() {
        return players;
    }

    public void setPlayers(Map<UUID, PlayerData> players) {
        this.players = players;
        for(Map.Entry<UUID, PlayerData> entry : players.entrySet()){
            playerNames.put(entry.getValue().getName(), entry.getKey());
        }
    }

    public void addPlayer(PlayerData p){
        players.put(p.getUuid(), p);
        playerNames.put(p.getName(), p.getUuid());
    }

    public PlayerData getPlayer(Player player, boolean create){
        PlayerData playerData = players.get(player.getUniqueId());
        if(playerData == null && create){
            playerData = new PlayerData(player.getUniqueId(), player.getName(), 0);
            addPlayer(playerData);
        }
        return playerData;
    }

    public PlayerData getPlayerByName(String playerName){
        UUID uuid = playerNames.get(playerName);
        return players.get(uuid);
    }

    public void addCoin(Player player, int amount){
        PlayerData playerData = getPlayer(player, true);
        playerData.setCoin(playerData.getCoin()+ amount);
    }

    public int getCoinByPlayer(Player player){
        PlayerData playerData = getPlayer(player, false);
        if(playerData != null){
            return playerData.getCoin();
        }
        return 0;
    }

    public int getCoinByName(String playerName){
        PlayerData playerData = getPlayerByName(playerName);
        if(playerData != null){
            return playerData.getCoin();
        }
        return 0;
    }

    public void updateName(Player player){
        PlayerData playerData = getPlayer(player, false);
        if(playerData != null){
            String newName = player.getName();
            String oldName = playerData.getName();
            if(!newName.equals(oldName)){
                playerNames.remove(oldName);
                playerNames.put(newName, player.getUniqueId());
            }
        }
    }
}
