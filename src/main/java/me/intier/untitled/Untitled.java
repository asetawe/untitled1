package me.intier.untitled;

import me.intier.untitled.commands.MenuCommand;
import me.intier.untitled.commands.MenuCommandInventoryListener;
import me.intier.untitled.mobs.PigSpawnListener;
import me.intier.untitled.stats.AttackListener;
import me.intier.untitled.stats.EntityStatsManager;
import me.intier.untitled.system.MenuInteractionListener;
import me.intier.untitled.system.PlayerJoinListener;
import me.intier.untitled.system.PlayerQuitListener;
import me.intier.untitled.system.PlayerStatsDisplay;
import org.bukkit.plugin.java.JavaPlugin;

public final class Untitled extends JavaPlugin {

    private EntityStatsManager entityStatsManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[[[[[[[[[[[[[[[[[[Server ONLINE.]]]]]]]]]]]]]]]]]]]]");
        this.entityStatsManager = new EntityStatsManager(this);

        new PlayerStatsDisplay(this, this.entityStatsManager);



        //
        this.getCommand("menu").setExecutor(new MenuCommand(this));

        //
        getServer().getPluginManager().registerEvents(new MenuCommandInventoryListener(this), this);
        getServer().getPluginManager().registerEvents(new MenuInteractionListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new AttackListener(this), this);
        getServer().getPluginManager().registerEvents(new PigSpawnListener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[[[[[[[[[[[[[[[[[[Server OFFLINE.]]]]]]]]]]]]]]]]]]]]");

        entityStatsManager.saveStats();
    }
    public EntityStatsManager getEntityStatsManager() {
        return this.entityStatsManager;
    }
}
