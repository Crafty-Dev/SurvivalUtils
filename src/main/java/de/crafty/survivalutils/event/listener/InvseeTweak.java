package de.crafty.survivalutils.event.listener;

import de.crafty.survivalutils.SurvivalUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InvseeTweak implements Listener {


    @EventHandler
    public void onInvsee$0(InventoryClickEvent event) {

        if (SurvivalUtils.OPEN_INVS.contains(event.getInventory()))
            event.setCancelled(true);

    }


    @EventHandler
    public void onInvsee$1(InventoryCloseEvent event) {
        SurvivalUtils.OPEN_INVS.remove(event.getInventory());
    }
}
