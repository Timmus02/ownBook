package Timmus02.ownBOOK;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class bookCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player =(Player) sender;

        if(args.length >3){
            return false;
        }
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("update")) {
                OwnBOOK.getInstance().getUpdateBook().updateBookPlayer(player, "Admin", "Random Book");
                return true;
            }
            //Bukkit.getLogger().info(args.length + "")
        }
        if(args.length == 2) {
            if(args[0].equalsIgnoreCase("create")){
                player.sendMessage("Started Creation of Book");
                OwnBOOK.getInstance().getCreateBook().createBookForPlayer(player, args[1]);
                return true;
            }
        }
        if(args.length == 3) {
            if (args[0].equalsIgnoreCase("saveBookInHand")) {
                Bukkit.getLogger().info(args[1] + " " + args[2]);
                try {
                    OwnBOOK.getInstance().getCreateBook().saveBookInHand(player, args[1], args[2]);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }
        }
        return false;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1)
            return Arrays.asList("update", "saveBookInHand", "create");
        if (args.length == 2)  {
            File file = new File("plugins/ownBOOK/books.yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            ArrayList<String> bookNames = new ArrayList<>();
            for (String key : config.getConfigurationSection("books").getKeys(false)) {
                String path = "books." + key;
                bookNames.add(config.getString(path + ".title"));
            }
            //Bukkit.getLogger().info(config.getString("books."));
            return bookNames;
        }
        return Arrays.asList();
    }
}
