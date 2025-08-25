package tp.mike.listeners;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import tp.mike.TestPlugin;
import org.bukkit.inventory.ItemStack;
import tp.mike.config.MainConfigManager;
import tp.mike.tools.ItemUtils;
import tp.mike.tools.MessageColors;

public class PlayerListener implements Listener{

    private TestPlugin plugin;

    public PlayerListener(TestPlugin plugin){
        this.plugin = plugin;
    }
    // censor words
    @EventHandler
    public void OnChat(AsyncPlayerChatEvent event){
        
        Player player = event.getPlayer();
        String message = event.getMessage();

        if(message.toLowerCase().contains("blyat")){

            event.setCancelled(true);

            player.sendMessage(MessageColors.coloredMessage( "&cWhat a blyat"));
        }
    }

    @EventHandler
    public void OnJoin(PlayerJoinEvent event){
        // World spawn
        Player player = event.getPlayer();

        MainConfigManager mainConfigManager = plugin.getMainConfigManager();
        if(mainConfigManager.IsWelcomeMessageEnabled()){

            List<String> message = mainConfigManager.getWelcomeMessageMessage();

            for(String m : message){

                player.sendMessage(MessageColors.coloredMessage(m.replace("%player%", player.getName())));
            }
        }

        Location location = new Location(Bukkit.getWorld("World"), 2.5, 78, 79.5, 90, 0);
        player.teleport(location);
    }

    @EventHandler
    public void OnBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();

        if(player.getWorld().getName().equals("World") && !player.hasPermission("testplugin.admin")){
            event.setCancelled(true);
            player.sendMessage(MessageColors.coloredMessage(plugin.getMainConfigManager().getPreventBlockBreak()));
        }

        Block block = event.getBlock();
        if(block.getType().equals(Material.EMERALD_ORE)){

            int num = new Random().nextInt(10);
            if(num >= 6){
                ItemStack item = ItemUtils.generateEmeraldItem(1);
                block.getWorld().dropItemNaturally(block.getLocation(), item);
            }
        }
    }
}
