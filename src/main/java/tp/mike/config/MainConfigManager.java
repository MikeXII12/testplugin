package tp.mike.config;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import tp.mike.TestPlugin;

public class MainConfigManager {

    private CustomConfig configFile;
    private TestPlugin plugin;
    private String preventBlockBreak;
    private Boolean welcomeMessageEnabled;
    private List<String> welcomeMessageMessage;


    public MainConfigManager(TestPlugin plugin){
        this.plugin = plugin;
        configFile = new CustomConfig("config.yml", null, plugin);
        configFile.registerConfig();
        loadConfig();
    }

    public void loadConfig(){
        FileConfiguration config = configFile.getConfig();
        preventBlockBreak = config.getString("messages.prevent_world_break");
        welcomeMessageEnabled = config.getBoolean("config.welcome_message.enabled");
        welcomeMessageMessage = config.getStringList("config.welcome_message.message");
    }

    public void reloadConfig(){

        configFile.reloadConfig();
        loadConfig();
    }

    public String getPreventBlockBreak(){

        return preventBlockBreak;
    }

    public Boolean IsWelcomeMessageEnabled(){

        return welcomeMessageEnabled;
    }

    public List<String> getWelcomeMessageMessage(){

        return welcomeMessageMessage;
    }
}
