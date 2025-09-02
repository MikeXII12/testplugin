// Libraries
package tp.mike;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import tp.mike.commands.CoinCommand;
import tp.mike.commands.ExpCommand;
import tp.mike.commands.FlyCommand;
import tp.mike.commands.MainCommand;
import tp.mike.commands.MenuCommand;
import tp.mike.config.MainConfigManager;
import tp.mike.config.PlayersConfigManager;
import tp.mike.tools.MessageColors;
import tp.mike.listeners.InventoryListener;
import tp.mike.listeners.PlayerListener;
import tp.mike.managers.MenuInventoryManager;
import tp.mike.managers.PlayerDataManager;

// main class or function
public class TestPlugin extends JavaPlugin {

    private String version = getDescription().getVersion();

    private MainConfigManager mainConfigManager;
    private PlayerDataManager playerDataManager;
    private PlayersConfigManager playersConfigManager;
    private MenuInventoryManager menuInventoryManager;

    // Event handler for plugin when enabled.
    public void onEnable() {

        registerCommands();
        registerEvents();
        mainConfigManager = new MainConfigManager(this);
        menuInventoryManager = new MenuInventoryManager();
        playerDataManager = new PlayerDataManager();
        playersConfigManager = new PlayersConfigManager(this, "players");

        Bukkit.getConsoleSender().sendMessage(MessageColors.coloredMessage("&aTestPlugin has been enabled." +version));      
    }
    

    // Event handler for plugin when disabled.
    public void onDisable() {

        playersConfigManager.saveConfigs();

        Bukkit.getConsoleSender().sendMessage(MessageColors.coloredMessage("&cTestPlugin has been disabled." +version));
    }
        // register commands
    public void registerCommands(){
        this.getCommand("TestPlugin").setExecutor(new MainCommand(this));
        this.getCommand("exp").setExecutor(new ExpCommand(this));
        this.getCommand("fly").setExecutor(new FlyCommand(this));
        this.getCommand("menu").setExecutor(new MenuCommand(this));
        this.getCommand("coin").setExecutor(new CoinCommand(this));
    }

    public void registerEvents(){
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
    }

    public PlayersConfigManager getPlayersConfigManager() {
        return playersConfigManager;
    }


    public MainConfigManager getMainConfigManager(){
        return mainConfigManager;
    }

    public MenuInventoryManager getMenuInventoryManager(){
        return menuInventoryManager;
    }


    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }
}