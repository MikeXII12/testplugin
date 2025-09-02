package tp.mike.listeners;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
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
        plugin.getPlayerDataManager().updateName(player);;

        MainConfigManager mainConfigManager = plugin.getMainConfigManager();
        if(mainConfigManager.IsWelcomeMessageEnabled()){

            List<String> message = mainConfigManager.getWelcomeMessageMessage();

            for(String m : message){

                player.sendMessage(MessageColors.coloredMessage(m.replace("%player%", player.getName())));
            }
        }

        Location location = new Location(Bukkit.getWorld("World"), 2.5, 78, 79.5, 90, 0);
        player.teleport(location);

        location.getWorld().playSound(location, Sound.BLOCK_CHAIN_BREAK, 10, 1.5f);
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

    @EventHandler
    public void onZombieDeath(EntityDeathEvent event){
        if(!event.getEntity().getType().equals(EntityType.ZOMBIE)){
            return;
        }

        Player player = event.getEntity().getKiller();
        if(player != null){
            int num = new Random().nextInt(10)+ 1;
            plugin.getPlayerDataManager().addCoin(player, num);
            player.sendMessage(MessageColors.coloredMessage("&7You just received &a"+num+" coins"));
        }
    }
}
