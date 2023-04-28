package de.crafty.survivalutils.command;

import de.crafty.survivalutils.SurvivalUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CMD_permission implements TabExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!"permission".equalsIgnoreCase(cmd.getName()))
            return false;

        if (args.length != 3)
            return false;

        if (args[0].equalsIgnoreCase("add")) {

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!player.getName().equalsIgnoreCase(args[1]))
                    continue;

                player.addAttachment(SurvivalUtils.get()).setPermission(args[2], true);
                sender.sendMessage(SurvivalUtils.PREFIX + "Permission \u00a7b" + args[2] + " \u00a77has been added to \u00a7b" + player.getName());
                break;
            }

            return true;
        }

        if (args[0].equalsIgnoreCase("remove")) {

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!player.getName().equalsIgnoreCase(args[1]))
                    continue;

                for (PermissionAttachmentInfo info : player.getEffectivePermissions()) {
                    if (!info.getPermission().equalsIgnoreCase(args[2]))
                        continue;

                    player.removeAttachment(info.getAttachment());
                    sender.sendMessage(SurvivalUtils.PREFIX + "Permission \u00a7b" + info.getPermission() + " \u00a77has been removed from \u00a7b" + player.getName());
                    break;
                }

                break;
            }

            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> ac = new ArrayList<>();

        if (args.length == 1) {
            ac.addAll(Arrays.asList("add", "remove").stream().filter(s -> s.toUpperCase().startsWith(args[0].toUpperCase())).toList());
        }

        if (args.length == 2) {
            Bukkit.getOnlinePlayers().stream().filter(player -> player.getName().toUpperCase().startsWith(args[1].toUpperCase())).forEach(player -> ac.add(player.getName()));
        }

        if (args.length == 3 && args[0].equalsIgnoreCase("remove")) {

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!player.getName().equalsIgnoreCase(args[1]))
                    continue;

                player.getEffectivePermissions().stream().filter(info -> info.getPermission().toUpperCase().startsWith(args[2].toUpperCase())).forEach(info -> {
                    ac.add(info.getPermission());
                });
                break;
            }

        }

        return ac;
    }
}
