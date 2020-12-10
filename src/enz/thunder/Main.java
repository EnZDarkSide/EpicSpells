package enz.thunder;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    Listener[] eventsListeners = {
            new ThunderSpellListener(this)
    };

    @Override
    public void onEnable() {
        for (Listener listener:
             eventsListeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }
}
