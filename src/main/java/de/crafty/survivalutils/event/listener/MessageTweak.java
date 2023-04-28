package de.crafty.survivalutils.event.listener;

import de.crafty.survivalutils.SurvivalUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MessageTweak implements Listener {


    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        event.setFormat(SurvivalUtils.get().message().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%MESSAGE%", event.getMessage()));

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();
        event.setJoinMessage(SurvivalUtils.get().joinMessage().replaceAll("%PLAYER%", player.getDisplayName()));

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){

        Player player = event.getPlayer();
        event.setQuitMessage(SurvivalUtils.get().quitMessage().replaceAll("%PLAYER%", player.getDisplayName()));

    }
}
