package de.crafty.survivalutils.command;

import de.crafty.survivalutils.SurvivalUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CMD_playtime implements TabExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player player))
            return false;

        if (!"playtime".equalsIgnoreCase(cmd.getName()))
            return false;

        OfflinePlayer target = null;
        if (args.length == 0)
            target = player;

        if (args.length == 1) {

            for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
                if (p.getName().equalsIgnoreCase(args[0]))
                    target = p;
            }

        }
        if (target == null)
            return false;

        player.sendMessage(SurvivalUtils.PREFIX + "\u00a7b" + (player.equals(target) ? "Your\u00a77" : target.getName() + "\u00a77's") + " Playtime: \u00a76" + this.getPlaytime(target));

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> list = new ArrayList<>();

        if (args.length == 1) {
            Arrays.asList(Bukkit.getOfflinePlayers()).stream().filter(player -> player.getName().toUpperCase().startsWith(args[0].toUpperCase())).forEach(player -> list.add(player.getName()));
        }

        return list;
    }


    private String getPlaytime(OfflinePlayer player) {

        long playtime = player.getStatistic(Statistic.TOTAL_WORLD_TIME);

        long seconds = playtime / 20;
        long minutes = seconds / 60;
        long hours = minutes / 60;

        long additionalSeconds = seconds - (minutes * 60);
        long additionalMinutes = minutes - (hours * 60);


        if (seconds < 60)
            return "\u00a76" + seconds + "\u00a77s";

        if (minutes < 60)
            return "\u00a76" + minutes + "\u00a77min " + "\u00a76" + additionalSeconds + "\u00a77s";

        return "\u00a76" + hours + "\u00a77h " + "\u00a76" + additionalMinutes + "\u00a77min";
    }
}
