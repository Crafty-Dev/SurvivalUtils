package de.crafty.survivalutils.event.listener;

import de.crafty.survivalutils.SurvivalUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

public class EnderChestTweak implements Listener {


    @EventHandler
    public void onOpen(InventoryOpenEvent event){

        Player player = (Player) event.getPlayer();
        if(event.getInventory().getLocation() == null && event.getInventory().getType() == InventoryType.ENDER_CHEST)
            player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1.0F, 1.0F);
    }


    @EventHandler
    public void onClose(InventoryCloseEvent event){

        Player player = (Player) event.getPlayer();

        if(event.getInventory().getLocation() == null && event.getInventory().getType() == InventoryType.ENDER_CHEST)
            player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_CLOSE, 1.0F, 1.0F);
    }


    @EventHandler
    public void onCraft(CraftItemEvent event){

        Player player = (Player) event.getWhoClicked();

        if(event.getRecipe().getResult().getType() == Material.ENDER_CHEST && player.getStatistic(Statistic.CRAFT_ITEM, Material.ENDER_CHEST) == 0){
            player.sendMessage(SurvivalUtils.PREFIX + "You've unlocked \u00a7aRemote Enderchest");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
        }

    }

}
