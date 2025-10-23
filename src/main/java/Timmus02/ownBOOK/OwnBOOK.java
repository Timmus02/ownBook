package Timmus02.ownBOOK;

import Timmus02.ownBOOK.commands.createBook;
import Timmus02.ownBOOK.commands.updateBook;
import org.bukkit.plugin.java.JavaPlugin;

public final class OwnBOOK extends JavaPlugin {
    createBook createBook = new createBook();
    updateBook updateBook = new updateBook();
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
    public static OwnBOOK getInstance() {
        return getPlugin(OwnBOOK.class);
    }
    public createBook getCreateBook(){
        return createBook;
    }
    public updateBook getUpdateBook(){
        return updateBook;
    }
}
