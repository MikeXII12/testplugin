package tp.mike.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tp.mike.TestPlugin;
import tp.mike.model.InventoryPlayer;
import tp.mike.tools.MessageColors;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// This class is intended to handle fly command for the TestPlugin.
public class MenuCommand implements CommandExecutor {

    private TestPlugin plugin;

    public MenuCommand(TestPlugin plugin) {

        // Constructor can be used for initialization if needed
        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args){
        
        if(!(sender instanceof Player)){
            
            sender.sendMessage(MessageColors.coloredMessage("&cYou can only use this command from a player"));
            return true;
        }

        Player player = (Player)sender;
        plugin.getMenuInventoryManager().openMainInventory(new InventoryPlayer(player, null));
      
        return true;
    } 
}