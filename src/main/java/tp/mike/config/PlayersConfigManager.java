package tp.mike.config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;

import tp.mike.TestPlugin;
import tp.mike.model.PlayerData;

public class PlayersConfigManager extends DataFolderConfigManager{

    public PlayersConfigManager(TestPlugin plugin, String folderName) {
        super(plugin, folderName);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void loadConfigs() {
        // TODO Auto-generated method stub
        
        Map<UUID, PlayerData> players = new HashMap();
        for(CustomConfig customConfig : configFiles){
            FileConfiguration config = customConfig.getConfig();

            UUID uuid = UUID.fromString(customConfig.getPath().replace(".yml", ""));
            String name = config.getString("name");
            int coin = config.getInt("coin");

            PlayerData playerData = new PlayerData(uuid, name, coin);
            players.put(uuid, playerData);
        }
        plugin.getPlayerDataManager().setPlayers(players);
    }

    @Override
    public void saveConfigs() {
        // TODO Auto-generated method stub
        
        Map<UUID, PlayerData> players = plugin.getPlayerDataManager().getPlayers();
        for(Map.Entry<UUID, PlayerData> entry : players.entrySet()){

            PlayerData playerData = entry.getValue();
            String pathName = playerData.getUuid().toString()+".yml";
            CustomConfig customConfig = getConfigFile(pathName);
            if(customConfig == null){

                customConfig = registerConfigFile(pathName);
            }

            FileConfiguration config = customConfig.getConfig();
            config.set("name", playerData.getName());
            config.set("coin", playerData.getCoin());
        }
        saveConfigFiles();
    }

}
