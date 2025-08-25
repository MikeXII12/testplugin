package tp.mike.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import tp.mike.TestPlugin;
import tp.mike.model.InventoryPlayer;

public class InventoryListener implements Listener{

    private TestPlugin plugin;

    public InventoryListener(TestPlugin plugin){

        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){

        Player player = (Player)event.getWhoClicked();
        InventoryPlayer inventoryPlayer = plugin.getMenuInventoryManager().getInventoryPlayer(player);

        if(inventoryPlayer != null){

            event.setCancelled(true);
            if(event.getCurrentItem() != null && event.getClickedInventory().equals(player.getOpenInventory().getTopInventory())){

                plugin.getMenuInventoryManager().inventoryClick(inventoryPlayer, event.getSlot(), event.getClick());
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){

        Player player = (Player)event.getPlayer();
        plugin.getMenuInventoryManager().removePlayer(player);
    }
}
