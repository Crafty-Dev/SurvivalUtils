package de.crafty.survivalutils.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.LocalDate;
import java.util.Date;

public class PlayerJoinHandler implements Listener {



    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();

        LocalDate date = LocalDate.now();
        if(date.getDayOfMonth() == 9 && date.getMonth().getValue() == 4)
            player.sendMessage("\u00a76\u00a7kAA\u00a7aFrohe Ostern\u00a76\u00a7kAA");


    }
}
