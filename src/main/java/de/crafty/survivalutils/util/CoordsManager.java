package de.crafty.survivalutils.util;

import de.crafty.survivalutils.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CoordsManager {


    private static final File FILE = new File("/plugins/SurvivalUtils/coords.yml");
    private static final FileConfiguration CONFIG = YamlConfiguration.loadConfiguration(FILE);


    private static final ArrayList<StaticCoordinate> BASE_COORDS = new ArrayList<>();
    private static final ArrayList<StaticCoordinate> PUBLIC_COORDS = new ArrayList<>();
    private static final ArrayList<StaticCoordinate> PRIVATE_COORDS = new ArrayList<>();


    public static void saveConfig() {
        try {
            CONFIG.save(FILE);
        } catch (IOException e) {
            Logger.error("Failed to save Coords");
        }
    }


    public static void saveBaseCoords() {
        CONFIG.set("base", BASE_COORDS);
        saveConfig();
    }

    public static void savePublicCoords() {
        CONFIG.set("public", PUBLIC_COORDS);
        saveConfig();
    }

    public static void savePrivateCoords() {
        CONFIG.set("private", PRIVATE_COORDS);
        saveConfig();
    }


    public static void loadBaseCoords() {

        List<String> data = (List<String>) CONFIG.getList("base");

        if (data == null)
            return;

        data.forEach(s -> BASE_COORDS.add(StaticCoordinate.load(s)));

    }

    public static void loadPublicCoords() {

        List<String> data = (List<String>) CONFIG.getList("public");

        if (data == null)
            return;

        data.forEach(s -> PUBLIC_COORDS.add(StaticCoordinate.load(s)));

    }

    public static void loadPrivateCoords() {

        List<String> data = (List<String>) CONFIG.getList("private");

        if (data == null)
            return;

        data.forEach(s -> PRIVATE_COORDS.add(StaticCoordinate.load(s)));

    }

    public static void addPublic(StaticCoordinate coordinate) {
        PUBLIC_COORDS.add(coordinate);
        saveConfig();
    }

    public static void addPrivate(StaticCoordinate coordinate) {
        PRIVATE_COORDS.add(coordinate);
        saveConfig();
    }

    public static void removePublic(StaticCoordinate coordinate) {
        PUBLIC_COORDS.remove(coordinate);
        saveConfig();
    }

    public static void removePrivate(StaticCoordinate coordinate) {
        PRIVATE_COORDS.remove(coordinate);
        saveConfig();
    }


    public static List<StaticCoordinate> getBaseCoords() {
        return BASE_COORDS;
    }

    public static List<StaticCoordinate> getPublicCoords() {
        return PUBLIC_COORDS;
    }

    public static List<StaticCoordinate> getPrivateCoords() {
        return PRIVATE_COORDS;
    }

    public static List<StaticCoordinate> getPrivateCoordsFor(Player player) {
        return PRIVATE_COORDS.stream().filter(coordinate -> coordinate.creator().equals(player.getUniqueId())).toList();
    }
}
