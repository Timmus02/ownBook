package Timmus02.ownBOOK;

import org.bukkit.plugin.java.JavaPlugin;

public final class OwnBOOK extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("[OwnBOOK] Enabled!");
        getCommand("ownBook").setExecutor(new bookCommand());
        //getCommand("onwBook").setTabCompleter(new bookTap());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("[OwnBOOK] Disabled!");
    }
}
