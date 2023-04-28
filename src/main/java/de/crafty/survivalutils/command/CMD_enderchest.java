package de.crafty.survivalutils.command;

import de.crafty.survivalutils.SurvivalUtils;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_enderchest implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!"enderchest".equalsIgnoreCase(cmd.getName()) || !(sender instanceof Player player))
            return false;

        if(!SurvivalUtils.get().remoteEnderchest()){
            player.sendMessage(SurvivalUtils.PREFIX + "\u00a7cThis Feature is not enabled");
            return true;
        }

        if(args.length > 0)
            return false;

        if(player.getStatistic(Statistic.CRAFT_ITEM, Material.ENDER_CHEST) > 0)
            player.openInventory(player.getEnderChest());
        else
            player.sendMessage(SurvivalUtils.PREFIX + "\u00a7cYou have to craft at least one enderchest");

        return true;
    }
}
