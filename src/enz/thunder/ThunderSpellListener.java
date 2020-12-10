package enz.thunder;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import java.util.Map;

public class ThunderSpellListener implements Listener {

    Plugin plugin;

    public Map<String, Spell> spells;

    public ThunderSpellListener(Plugin plugin){
        this.plugin = plugin;
        this.spells = Map.of("MON HABA KEMENBON LEHT", new ThunderSpell(plugin));
    }

    @EventHandler
    public void OnTextingSpells(AsyncPlayerChatEvent event) throws Exception {
        var message = event.getMessage();

        if(spells.containsKey(message)){
            this.spells.get(message).Start(0, event.getPlayer());
        }
    }
}
