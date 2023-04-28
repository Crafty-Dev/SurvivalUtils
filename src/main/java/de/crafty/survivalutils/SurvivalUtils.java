package de.crafty.survivalutils;

import de.crafty.survivalutils.command.*;
import de.crafty.survivalutils.event.PlayerJoinHandler;
import de.crafty.survivalutils.event.listener.*;
import de.crafty.survivalutils.logging.Logger;
import de.crafty.survivalutils.util.CoordsManager;
import jline.internal.Log;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class SurvivalUtils extends JavaPlugin {

    public static final String PREFIX = "\u00a77[\u00a7aSurvivalUtils\u00a77] ";
    public static final ArrayList<Inventory> OPEN_INVS = new ArrayList<>();

    private static SurvivalUtils instance;

    @Override
    public void onEnable() {
        instance = this;

        this.saveDefaultConfig();

        Logger.info("Registering Commands...");
        this.getCommand("ping").setExecutor(new CMD_ping());
        this.getCommand("playtime").setExecutor(new CMD_playtime());
        this.getCommand("invsee").setExecutor(new CMD_invsee());
        this.getCommand("enderchest").setExecutor(new CMD_enderchest());
        this.getCommand("permission").setExecutor(new CMD_permission());
        this.getCommand("coords").setExecutor(new CMD_coords());

        Logger.info("Registering Event Handler...");
        Bukkit.getPluginManager().registerEvents(new PlayerJoinHandler(), this);

        Logger.info("Registering Tweak Handler...");
        Bukkit.getPluginManager().registerEvents(new PlayerNameTweak(), this);
        Bukkit.getPluginManager().registerEvents(new MessageTweak(), this);
        Bukkit.getPluginManager().registerEvents(new SimpleCropHarvest(), this);
        Bukkit.getPluginManager().registerEvents(new GlowberryTweak(), this);
        Bukkit.getPluginManager().registerEvents(new DeathTweak(), this);
        Bukkit.getPluginManager().registerEvents(new DimensionPrefixTweak(), this);
        Bukkit.getPluginManager().registerEvents(new AnvilUseTweak(), this);
        Bukkit.getPluginManager().registerEvents(new InvseeTweak(), this);
        Bukkit.getPluginManager().registerEvents(new EnderChestTweak(), this);
        Bukkit.getPluginManager().registerEvents(new EditSignsTweak(), this);

        Log.info("Loading saved coords...");
        CoordsManager.loadBaseCoords();
        CoordsManager.loadPublicCoords();
        CoordsManager.loadPrivateCoords();

        Logger.important("Plugin enabled");
    }


    @Override
    public void onDisable() {
        Logger.important("Plugin disabled");
    }

    public static SurvivalUtils get() {
        return instance;
    }



    //Config


    public String joinMessage(){
        return this.getConfig().getString("joinMsg");
    }

    public String quitMessage(){
        return this.getConfig().getString("quitMsg");
    }

    public String message(){
        return this.getConfig().getString("msg");
    }

    public boolean simpleCropHarvest(){
        return this.getConfig().getBoolean("simpleCropHarvest");
    }

    public boolean glowOnGlowberry(){
        return this.getConfig().getBoolean("glowOnGlowberry");
    }

    public int glowberryGlowTime(){
        return this.getConfig().getInt("glowberryGlowTime");
    }

    public boolean deathChests(){
        return this.getConfig().getBoolean("deathChests");
    }

    public boolean deathCoords(){
        return this.getConfig().getBoolean("deathCoords");
    }

    public String deathMsgColor(){
        return this.getConfig().getString("deathMsgColor");
    }

    public String deathMsgPlayerName(){
        return this.getConfig().getString("deathMsgPlayerName");
    }

    public boolean dimensionPrefix(){
        return this.getConfig().getBoolean("dimensionPrefix");
    }

    public boolean anvilUses(){
        return this.getConfig().getBoolean("anvilUses");
    }

    public boolean remoteEnderchest(){
        return this.getConfig().getBoolean("remoteEnderchest");
    }

    public boolean editSigns(){
        return this.getConfig().getBoolean("editSigns");
    }

    public boolean shulkerSwap(){
        return this.getConfig().getBoolean("shulkerSwap");
    }

    public HashMap<String, String> customPlayerNames(){
        HashMap<String, String> map = new HashMap<>();

        this.getConfig().getConfigurationSection("playerNames").getKeys(false).forEach(key -> {
            map.put(key, this.getConfig().getString("playerNames." + key));
        });

        return map;
    }

    public HashMap<String, String> customPlayerListNames(){
        HashMap<String, String> map = new HashMap<>();

        this.getConfig().getConfigurationSection("playerListNames").getKeys(false).forEach(key -> {
            map.put(key, this.getConfig().getString("playerListNames." + key));
        });

        return map;
    }
}
