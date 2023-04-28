package de.crafty.survivalutils.event.listener;

import de.crafty.survivalutils.SurvivalUtils;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EditSignsTweak implements Listener {


    @EventHandler
    public void onSignEdit(PlayerInteractEvent event){

        if(!SurvivalUtils.get().editSigns())
            return;

        Player player = event.getPlayer();

        if(event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        Block block = event.getClickedBlock();
        if(!(block.getState() instanceof Sign sign))
            return;

        player.openSign(sign);

    }

}
