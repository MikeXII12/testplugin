package tp.mike.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tp.mike.TestPlugin;
import tp.mike.managers.PlayerDataManager;
import tp.mike.tools.MessageColors;

public class CoinCommand implements CommandExecutor{

    private TestPlugin plugin;

    public CoinCommand(TestPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage(MessageColors.coloredMessage("&cYou can only use this command from a player"));
            return true;
        }

        Player player = (Player)sender;
        PlayerDataManager playerDataManager = plugin.getPlayerDataManager();

        if(args.length == 0){
            int coin = playerDataManager.getCoinByPlayer(player);
            player.sendMessage(MessageColors.coloredMessage("&7Your coins are: &e"+ coin));
        }
        else{
            int coin = playerDataManager.getCoinByName(args[0]);
            player.sendMessage(MessageColors.coloredMessage("&7Coins of " + args[0] + ": &e" + coin));
        }

        return true;
    }
}
