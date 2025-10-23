package Timmus02.ownBOOK.commands;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class createBook {
    public createBook(Player _player) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();
        meta.setAuthor("Admin");
        meta.setTitle("Random Book");
        meta.setPages(String.valueOf(System.currentTimeMillis()));
        book.setItemMeta(meta);
        _player.getInventory().addItem(book);
    }
}
