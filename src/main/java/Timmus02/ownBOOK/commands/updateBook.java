package Timmus02.ownBOOK.commands;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BookMeta;

public class updateBook {
    public updateBook(Player _player, String _bookAuthor, String _bookTitle) {
        PlayerInventory inventory = _player.getInventory();
        boolean foundBook = false;
        for(int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if(item != null) {
                if(item.getType() == Material.WRITTEN_BOOK) {
                    BookMeta meta = (BookMeta) item.getItemMeta();
                    if(meta.getAuthor().equals(_bookAuthor)) {
                        if(meta.getTitle().equals(_bookTitle)) {
                            _player.sendMessage("Found BOOK");
                            foundBook = true;
                        }
                    }
                }
            }
        }
        if(!foundBook) {
            _player.sendMessage("No book found");
        }
    }
}
