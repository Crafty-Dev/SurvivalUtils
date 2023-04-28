package de.crafty.survivalutils.event.listener;

import de.crafty.survivalutils.SurvivalUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;

public class PlayerNameTweak implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();


        HashMap<String, String> names = SurvivalUtils.get().customPlayerNames();
        if(names.containsKey(player.getName()))
            player.setDisplayName(names.get(player.getName()).replaceAll("%PLAYER%", player.getName()));

        HashMap<String, String> listNames = SurvivalUtils.get().customPlayerListNames();
        if(listNames.containsKey(player.getName()))
            player.setPlayerListName(listNames.get(player.getName()).replaceAll("%PLAYER%", player.getName()));


    }

}
