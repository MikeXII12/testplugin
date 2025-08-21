package tp.mike.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tp.mike.TestPlugin;
import tp.mike.tools.MessageColors;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// This class is intended to handle fly command for the TestPlugin.
public class FlyCommand implements CommandExecutor {

    private TestPlugin plugin;

    public FlyCommand(TestPlugin plugin) {

        // Constructor can be used for initialization if needed
        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args){
        
        if(!sender.hasPermission("testplugin.fly")){
            
            sender.sendMessage(MessageColors.coloredMessage("&cYou do not have permission to use this command"));
            return true;
        }

        Player player = null;

        if(args.length == 0){
            if(sender instanceof Player){
                player = (Player)sender;
            }
            else{
                sender.sendMessage(MessageColors.coloredMessage("&cYou need to use /fly and a player"));
                return true;
            }
        }
        else{
            player = Bukkit.getPlayer(args[1]);

            if(player == null){
                sender.sendMessage(MessageColors.coloredMessage("&cPlayer &c"+args[1]+" &cis not online"));
                return true;
            }
        }

        if(player.getAllowFlight()){
            player.setAllowFlight(false);
            sender.sendMessage(MessageColors.coloredMessage("&cFly Disabled"));
            if(!player.equals(sender)){
                sender.sendMessage(MessageColors.coloredMessage("&cFly Disabled for &c"+player.getName()+""));
            }
        }
        else{
            player.setAllowFlight(true);
            sender.sendMessage(MessageColors.coloredMessage("&cFly Enabled"));
            if(!player.equals(sender)){
                sender.sendMessage(MessageColors.coloredMessage("&cFly Disabled for &c"+player.getName()+""));
            }
        }
      
        return true;
    } 
}