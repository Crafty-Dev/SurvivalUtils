package de.crafty.survivalutils.event.listener;

import de.crafty.survivalutils.SurvivalUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GlowberryTweak implements Listener {


    @EventHandler
    public void onGlowberryEat(FoodLevelChangeEvent event) {

        Player player = (Player) event.getEntity();
        ItemStack stack = event.getItem();

        if (stack == null)
            return;

        if (stack.getType() == Material.GLOW_BERRIES) {

            if (SurvivalUtils.get().glowOnGlowberry())
                player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, SurvivalUtils.get().glowberryGlowTime() * 20, 0, false, false));

        }
    }
}
