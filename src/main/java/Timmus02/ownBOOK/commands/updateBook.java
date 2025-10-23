package Timmus02.ownBOOK.commands;

import Timmus02.ownBOOK.OwnBOOK;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BookMeta;

import java.awt.print.Book;
import java.util.ArrayList;

public class updateBook {
    public void updateBookPlayer(Player _player, String _bookTitle) {
        PlayerInventory inventory = _player.getInventory();
        boolean foundBook = false;
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for(int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if(item != null) {
                if(item.getType() == Material.WRITTEN_BOOK) {
                    BookMeta meta = (BookMeta) item.getItemMeta();

                    if(meta.getTitle().equals(_bookTitle)) {
                        _player.sendMessage("Found BOOK");
                        indexes.add(i);
                        foundBook = true;
                    }
                }
            }
        }
        if(!foundBook) {
            _player.sendMessage("No book found");
            return;
        }
        if(foundBook) {
            for(int i = 0; i <indexes.size(); i++) {
                updateBook(_player, indexes.get(i),_bookTitle);
            }
        }
    }
    private void updateBook(Player _player, int _index, String _title) {
        BookMeta newMeta = OwnBOOK.getInstance().getCreateBook().getBookMeta(_title);
        _player.getInventory().getItem(_index).setItemMeta(newMeta);
    }
}
