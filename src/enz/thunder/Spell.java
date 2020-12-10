package enz.thunder;
import org.bukkit.entity.Player;

public abstract class Spell {
    public abstract Spell getSpell();
    public abstract void Start() throws Exception;
    public abstract void Start(int delay, Player player) throws Exception;
}
