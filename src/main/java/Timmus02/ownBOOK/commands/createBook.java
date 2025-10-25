package Timmus02.ownBOOK.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.WritableBookMeta;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class createBook {
    public void createBookForPlayer(Player _player, String _titel, String _playerName) {
        if(_playerName != null) {
            _player = Bukkit.getPlayer(_playerName);
        }
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        if (getBookMeta(_titel) != null) {
            book.setItemMeta(getBookMeta(_titel));
            _player.getInventory().addItem(book);
        }
        else  {_player.sendMessage(ChatColor.RED + "book not found");}

    }
    public void getWritableBook(Player _player, String _titel) {
        ItemStack book = new ItemStack(Material.WRITABLE_BOOK);
        if (getBookMeta(_titel) != null) {
            WritableBookMeta meta = (WritableBookMeta) book.getItemMeta();
            meta.setPages(getBookMeta(_titel).getPages());
            book.setItemMeta(meta);
            _player.getInventory().addItem(book);
        }
        else  {_player.sendMessage(ChatColor.RED + "book not found");}
    }
    public BookMeta getBookMeta(String _titel){
        File file = new File("plugins/ownBOOK/books.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (file.length() > 0) {
            Object Obook = config.get("books." + _titel.replace(" ", "_"));
            if (Obook != null) {
                String path = "books." + _titel.replace(" ", "_");
                String title = config.getString(path + ".title");
                String author = config.getString(path + ".author");
                List<String> pages = config.getStringList(path + ".pages");

                ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                BookMeta meta = (BookMeta) book.getItemMeta();
                meta.setAuthor(author);
                meta.setTitle(title);
                meta.setPages(pages);
                return meta;
            } else {
                return null;
            }
        }
        return null;
    }
    public void saveBookInHand(Player _player, String _author, String _title) throws IOException {
        if (_player.getInventory().getItemInMainHand() instanceof ItemStack) {
            ItemStack bookInHand = _player.getInventory().getItemInMainHand();
            if (bookInHand.getType() == Material.WRITABLE_BOOK) {
                BookMeta meta = (BookMeta) bookInHand.getItemMeta();
                File file = new File("plugins/ownBOOK/books.yml");

                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

                String path = "books." + _title.replace(" ", "_");
                config.set(path + ".author", _author);
                config.set(path + ".title", _title);
                config.set(path + ".pages", meta.getPages()); //überschreibt alles mit neuem
                config.save(file);

                _player.sendMessage(ChatColor.GREEN + "Book Saved!");
            }
            else {
                _player.sendMessage(ChatColor.RED + "You are not holding a Book&Quill");
            }
        }
        else {
            _player.sendMessage(ChatColor.RED + "You are not holding an item in your Hand!");
        }
    }
}
