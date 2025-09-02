package tp.mike.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tp.mike.model.InventoryPlayer;
import tp.mike.model.InventorySection;
import tp.mike.tools.MessageColors;

public class MenuInventoryManager {

    private ArrayList<InventoryPlayer> players;

    public MenuInventoryManager(){
        this.players = new ArrayList<>();
    }

    public InventoryPlayer getInventoryPlayer(Player player){

        for(InventoryPlayer inventoryPlayer : players){

            if(inventoryPlayer.getPlayer().equals(player)){
                return inventoryPlayer;
            }
        }
        return null;
    }

    public void removePlayer(Player player){

        players.removeIf(inventoryPlayer -> inventoryPlayer.getPlayer().equals(player));
    }

    public void openMainInventory(InventoryPlayer inventoryPlayer){
        
        inventoryPlayer.setSection(InventorySection.MENU_MAIN);
        Inventory inventory = Bukkit.createInventory(null, 54, MessageColors.coloredMessage("&4Inventory"));
        Player player = inventoryPlayer.getPlayer();

        // decorative items

        ItemStack item = new ItemStack(Material.BAMBOO, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Bambool");
        item.setItemMeta(meta);

        for(int i = 45; i <= 53; i++){
            inventory.setItem(i, item);
        }

        //informative items

        item = new ItemStack(Material.ARROW);
        meta = item.getItemMeta();
        meta.setDisplayName(MessageColors.coloredMessage("&4Arrow"));
        List<String> lore = new ArrayList<>();
        lore.add(MessageColors.coloredMessage("&cIts an arrow"));
        lore.add(MessageColors.coloredMessage("&cArrow That shows your stats"));
        lore.add(MessageColors.coloredMessage("&7Level: &a"+ player.getLevel()));
        lore.add(MessageColors.coloredMessage("&7XP: &a"+ player.getTotalExperience()));
        lore.add(MessageColors.coloredMessage("&7Ping: &a"+ player.getPing()));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inventory.setItem(19, item);

        //sub inventory
        item = new ItemStack(Material.POTION);
        meta = item.getItemMeta();
        meta.setDisplayName(MessageColors.coloredMessage("&4POTION EFFECT"));
        lore = new ArrayList<>();
        lore.add(MessageColors.coloredMessage("&cIDK"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_POTION_CONTENTS);
        item.setItemMeta(meta);
        inventory.setItem(21, item);


        player.openInventory(inventory);
        players.add(inventoryPlayer);
    }

    public void openEffectInventory(InventoryPlayer inventoryPlayer){
        
        inventoryPlayer.setSection(InventorySection.MENU_EFFECTS);
        Inventory inventory = Bukkit.createInventory(null, 27, MessageColors.coloredMessage("&4Inventory"));
        Player player = inventoryPlayer.getPlayer();

        // decorative items

        ItemStack item = new ItemStack(Material.BAMBOO, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Bambool");
        item.setItemMeta(meta);

        for(int i = 19; i <= 26; i++){
            inventory.setItem(i, item);
        }

        //Potion Effects

        item = new ItemStack(Material.POTION);
        PotionMeta metaPotion = (PotionMeta)item.getItemMeta();
        metaPotion.setDisplayName(MessageColors.coloredMessage("&aJUMP EFFECT"));
        List<String> lore = new ArrayList<>();
        lore.add(MessageColors.coloredMessage("&cClick here:"));
        lore.add(MessageColors.coloredMessage("&c   JUMP 5 for 10 seconds"));
        metaPotion.setLore(lore);
        metaPotion.setColor(Color.fromRGB(236, 236, 2));
        metaPotion.addItemFlags(ItemFlag.HIDE_POTION_CONTENTS);
        item.setItemMeta(metaPotion);
        inventory.setItem(11, item);

        item = new ItemStack(Material.POTION);
        PotionMeta metaPotion1 = (PotionMeta)item.getItemMeta();
        metaPotion1.setDisplayName(MessageColors.coloredMessage("&MIX EFFECT"));
        List<String> mixLore = new ArrayList<>();
        mixLore.add(MessageColors.coloredMessage("&cClick here:"));
        mixLore.add(MessageColors.coloredMessage("&   cSTRENGHT 5 for 10 seconds"));
        mixLore.add(MessageColors.coloredMessage("&   cREGENERATION 5 for 10 seconds"));
        mixLore.add(MessageColors.coloredMessage("&   cNIGHT VISION 5 for 10 seconds"));
        metaPotion1.setLore(mixLore);
        metaPotion.setColor(Color.fromRGB(100, 7, 249));
        metaPotion1.addItemFlags(ItemFlag.HIDE_POTION_CONTENTS);
        item.setItemMeta(metaPotion1);
        inventory.setItem(15, item);

        //back button

        item = new ItemStack(Material.LIGHTNING_ROD);
        meta = item.getItemMeta();
        meta.setDisplayName(MessageColors.coloredMessage("Back"));
        item.setItemMeta(meta);
        inventory.setItem(18, item);

        player.openInventory(inventory);
        players.add(inventoryPlayer);
    }

    public void inventoryClick(InventoryPlayer inventoryPlayer, int slot, ClickType clickType){
        Player player = inventoryPlayer.getPlayer();
        InventorySection section = inventoryPlayer.getSection();
        if(section.equals(InventorySection.MENU_MAIN)){
            if(slot == 21){
                if(!player.hasPermission("testplugin.inventory.effects")){
                    player.sendMessage(MessageColors.coloredMessage("&c You dont have permission to enter this menu"));
                    player.playSound(player.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1.0f, 1.5f);
                    return;
                }
                openEffectInventory(inventoryPlayer);
            }
        }
        else if(section.equals(InventorySection.MENU_EFFECTS)){
            if(slot == 18){
                openMainInventory(inventoryPlayer);
            }
            else if(slot == 11){
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 200, 6, false, false, true));
                player.sendMessage(MessageColors.coloredMessage("&c You just got Jump boost 5 for 10 seconds"));

                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.5f);
            }
            else if(slot == 15){
                player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 200, 6, false, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 6, false, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 200, 6, false, false, true));
                player.sendMessage(MessageColors.coloredMessage("&c You just got the Mix Effects"));

                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.5f);
            }
        }
    }
}