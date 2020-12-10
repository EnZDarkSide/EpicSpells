package enz.thunder;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class ThunderSpell extends Spell {

    Plugin plugin;
    BukkitRunnable runnable;

    Player player;

    public ThunderSpell(Plugin plugin) {
        this.plugin = plugin;
    }

    public Spell getSpell() {
        return this;
    }

    @Override
    public void Start() throws Exception {
        throw new Exception("please use parametrized Start instead");
    }

    public void Start(int delay, Player player) {
        this.player = player;
        this.runnable = CreateRunnable();
        this.runnable.runTaskLater(plugin, delay);
    }

    public BukkitRunnable CreateRunnable() {
        var locationsOfStrike = GetLocationsOfStrike(10, 12);

        return new BukkitRunnable() {
            @Override
            public void run() {
                player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 4*20, 1));
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (var location: locationsOfStrike) {
                            player.getWorld().strikeLightning(location);
//                            player.getWorld().createExplosion
                            player.getWorld().getBlockAt(location).setType(Material.NETHERRACK);
                        }
                    }
                }.runTaskLater(plugin, 3*20);
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 4*20, 1));
            }
        };
    }

    private ArrayList<Location> GetLocationsOfStrike(int radius, int count) {
        var result = new ArrayList<Location>();

        var radians = Math.toRadians(360.0/count);

        for(int i = 0; i < count; i++) {
            var x = radius*Math.sin(radians*i);
            var y = radius*Math.cos(radians*i);

            result.add(player.getLocation().add(x, 0, y));
        }

        return result;
    }
}
