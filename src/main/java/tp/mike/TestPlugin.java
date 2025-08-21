// Libraries
package tp.mike;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import tp.mike.commands.ExpCommand;
import tp.mike.commands.FlyCommand;
import tp.mike.commands.MainCommand;
import tp.mike.config.MainConfigManager;
import tp.mike.tools.MessageColors;
import tp.mike.listeners.PlayerListener;

// main class or function
public class TestPlugin extends JavaPlugin {

    private String version = getDescription().getVersion();

    private MainConfigManager mainConfigManager;

    // Event handler for plugin when enabled.
    public void onEnable() {

        registerCommands();
        registerEvents();
        mainConfigManager = new MainConfigManager(this);

        Bukkit.getConsoleSender().sendMessage(MessageColors.coloredMessage("&aTestPlugin has been enabled." +version));      
    }
    

    // Event handler for plugin when disabled.
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(MessageColors.coloredMessage("&cTestPlugin has been disabled." +version));
    }
        // register commands
    public void registerCommands(){
        this.getCommand("TestPlugin").setExecutor(new MainCommand(this));
        this.getCommand("exp").setExecutor(new ExpCommand(this));
        this.getCommand("fly").setExecutor(new FlyCommand(this));
    }

    public void registerEvents(){
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    public MainConfigManager getMainConfigManager(){
        return mainConfigManager;
    }
}