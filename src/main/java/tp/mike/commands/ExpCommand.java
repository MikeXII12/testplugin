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
public class ExpCommand implements CommandExecutor {

    private TestPlugin plugin;

    public ExpCommand(TestPlugin plugin) {

        // Constructor can be used for initialization if needed
        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args){
        
        if(!sender.hasPermission("testplugin.exp")){
            
            sender.sendMessage(MessageColors.coloredMessage("&cYou do not have permission to use this command"));
            return true;
        }

        if(args.length == 0){
            sender.sendMessage(MessageColors.coloredMessage("&cYou need to use the command /exp"));
            return true;
        }

        int quantity = 0;

        try{

            quantity = Integer.parseInt(args[0]);
            if(quantity <= 0){
                sender.sendMessage(MessageColors.coloredMessage("&cEnter a valid amount"));
                return true;
            }
        }
        catch(NumberFormatException e){
            sender.sendMessage(MessageColors.coloredMessage("&cEnter a valid amount"));
            return true;
        }

        Player player = null;

        if(args.length == 1){
            if(sender instanceof Player){
                player = (Player)sender;
            }
            else{
                sender.sendMessage(MessageColors.coloredMessage("&cEnter a valid amount"));
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

        player.giveExp(quantity);
        player.sendMessage(MessageColors.coloredMessage("&cYou just received "+quantity+" &cXP"));
        sender.sendMessage(MessageColors.coloredMessage("&cThe player "+player.getName()+" just received "+quantity+" XP"));
       
        return true;
    } 
}