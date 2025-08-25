package tp.mike.tools;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ItemUtils {

    public static ItemStack generateEmeraldItem(int amount){

        ItemStack item = new ItemStack(Material.EMERALD, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessageColors.coloredMessage("&aEmerald bruh"));

        List<String> lore = new ArrayList<>();
        lore.add(MessageColors.coloredMessage("&cIts Just an Emerald"));
        lore.add(MessageColors.coloredMessage("&cTold ya its Just an Emerald"));
        meta.setLore(lore);

        meta.addEnchant(Enchantment.KNOCKBACK, 100, true);

        item.setItemMeta(meta); 
        
        return item;
    }

    public static ItemStack generatePotionItem(int amount){

        ItemStack item = new ItemStack(Material.POTION, amount);
        PotionMeta meta = (PotionMeta)item.getItemMeta();
        meta.setDisplayName(MessageColors.coloredMessage("&aSPEED POTION"));

        List<String> lore = new ArrayList<>();
        lore.add(MessageColors.coloredMessage("&cIts Just a SPEED POTION"));
        meta.setLore(lore);

        meta.setColor(Color.fromRGB(7, 255, 177));
        meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 200, 6, false, false, true), false);

        item.setItemMeta(meta);

        return item;
    }
}
