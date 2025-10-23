package Timmus02.ownBOOK;

import Timmus02.ownBOOK.commands.createBook;
import Timmus02.ownBOOK.commands.updateBook;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.Arrays;
import java.util.List;

public class bookCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player =(Player) sender;

        if(args.length >1){
            return false;
        }
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("create")){
                player.sendMessage("Started Creation of Book");
                OwnBOOK.getInstance().getCreateBook().createBookForPlayer(player);
                return true;
            }
            if(args[0].equalsIgnoreCase("update")) {
                OwnBOOK.getInstance().getUpdateBook().updateBookPlayer(player, "Admin", "Random Book");
                return true;
            }
        }
        return false;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1)
            return Arrays.asList("create", "update");
        return Arrays.asList();
    }
}
