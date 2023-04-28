package de.crafty.survivalutils.command;

import de.crafty.survivalutils.SurvivalUtils;
import de.crafty.survivalutils.util.CoordsManager;
import de.crafty.survivalutils.util.StaticCoordinate;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutBlockChange;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.craftbukkit.v1_19_R3.block.CraftBlock;
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CMD_coords implements TabExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!"coords".equalsIgnoreCase(cmd.getName()) || !(sender instanceof Player player))
            return false;


        if (args.length < 3)
            return false;

        if (args[0].equalsIgnoreCase("create"))
            this.performCreation(player, args);

        if (args[0].equalsIgnoreCase("retrieve"))
            this.performQuery(player, args);

        if (args[0].equalsIgnoreCase("remove"))
            this.performRemove(player, args);


        return true;
    }


    private void performCreation(Player sender, String[] args) {

        boolean isPublic = args[1].equals("public");
        String name = args[2];

        int x = sender.getLocation().getBlockX();
        int y = sender.getLocation().getBlockY();
        int z = sender.getLocation().getBlockZ();

        World world = sender.getWorld();

        if (args.length >= 4)
            x = Integer.parseInt(args[3]);

        if (args.length >= 5)
            y = Integer.parseInt(args[4]);

        if (args.length >= 6)
            z = Integer.parseInt(args[5]);

        if (args.length == 7)
            world = Bukkit.getWorld(args[6]);

        if (isPublic) {
            CoordsManager.addPublic(new StaticCoordinate(world, name, x, y, z, sender.getUniqueId()));
            sender.sendMessage(SurvivalUtils.PREFIX + "Public Position \u00a7b" + name + " \u00a77has been created");
            return;
        }


        CoordsManager.addPrivate(new StaticCoordinate(world, name, x, y, z, sender.getUniqueId()));
        sender.sendMessage(SurvivalUtils.PREFIX + "Private Position \u00a7b" + name + " \u00a77has been created");

    }

    private void performQuery(Player sender, String[] args) {

        if (args.length > 3)
            return;

        boolean isPublic = args[1].equals("public");

        List<StaticCoordinate> relevant_coords = isPublic ? CoordsManager.getPublicCoords() : CoordsManager.getPrivateCoords();

        for (StaticCoordinate coordinate : relevant_coords) {

            if (!coordinate.name().equalsIgnoreCase(args[2]))
                continue;

            StringBuilder coordinateBuilder = new StringBuilder("\u00a77Coordinate: \u00a7b" + coordinate.name() + " \u00a77by \u00a7a" + Bukkit.getOfflinePlayer(coordinate.creator()).getName());
            coordinateBuilder.append("\n").append("\u00a77X: \u00a7b" + coordinate.x());
            coordinateBuilder.append("\n").append("\u00a77Y: \u00a7b" + coordinate.y());
            coordinateBuilder.append("\n").append("\u00a77Z: \u00a7b" + coordinate.z());
            coordinateBuilder.append("\n").append("\u00a77Dimension: \u00a7b" + coordinate.world().getEnvironment());

            sender.sendMessage(coordinate.toString());
            return;
        }

        sender.sendMessage(SurvivalUtils.PREFIX + "There is no Position named \u00a74" + args[2]);

    }

    private void performRemove(Player sender, String[] args) {

        if (args.length > 3)
            return;

        boolean isPublic = args[1].equals("public");

        List<StaticCoordinate> relevant_coords = isPublic ? CoordsManager.getPublicCoords() : CoordsManager.getPrivateCoords();

        for (StaticCoordinate coordinate : relevant_coords) {

            if (!coordinate.name().equalsIgnoreCase(args[2]))
                continue;

            if (isPublic) {
                CoordsManager.removePublic(coordinate);
                sender.sendMessage(SurvivalUtils.PREFIX + "Public Position \u00a7b" + coordinate.name() + " \u00a77has been removed");
                return;
            }

            CoordsManager.removePrivate(coordinate);
            sender.sendMessage(SurvivalUtils.PREFIX + "Private Position \u00a7b" + coordinate.name() + " \u00a77has been removed");
            return;
        }

        sender.sendMessage(SurvivalUtils.PREFIX + "There is no Position named \u00a74" + args[2]);

    }

    private void performShow(Player sender, String[] args){

        if(args.length > 3)
            return;

        boolean isPublic = args[1].equals("public");

        List<StaticCoordinate> relevant_coords = isPublic ? CoordsManager.getPublicCoords() : CoordsManager.getPrivateCoords();

        for (StaticCoordinate coordinate : relevant_coords) {

            if (!coordinate.name().equalsIgnoreCase(args[2]))
                continue;


            World world = coordinate.world();
            Block block = world.getHighestBlockAt(coordinate.x(), coordinate.z());
            
            break;
        }

    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> ac = new ArrayList<>();


        if(!(sender instanceof Player player))
            return null;

        if (args.length == 1) {
            List.of("create", "retrieve", "remove", "show").stream().filter(s -> s.toUpperCase().startsWith(args[0].toUpperCase())).forEach(ac::add);
        }

        if (args.length == 2) {
            List.of("private", "public").stream().filter(s -> s.toUpperCase().startsWith(args[1].toUpperCase())).forEach(ac::add);
        }

        if (args.length == 3 && (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("retrieve") || args[0].equalsIgnoreCase("show"))) {

            if(args[1].equalsIgnoreCase("public"))
                CoordsManager.getPublicCoords().stream().filter(coordinate -> coordinate.name().toUpperCase().startsWith(args[2].toUpperCase())).forEach(coordinate -> ac.add(coordinate.name()));
            else if(args[1].equalsIgnoreCase("private"))
                CoordsManager.getPrivateCoordsFor(player).stream().filter(coordinate -> coordinate.name().toUpperCase().startsWith(args[2].toUpperCase())).forEach(coordinate -> ac.add(coordinate.name()));
        }

        return ac;
    }
}
