package Timmus02.ownBOOK.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.WritableBookMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.w3c.dom.Text;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.awt.Color.RED;

public class createBook {
    public void createBookForPlayer(Player _player, String _titel, String _playerName) { //Handles the create Command
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
    public void getWritableBook(Player _player, String _titel) { //Handles the getWritableBookCommand
        ItemStack book = new ItemStack(Material.WRITABLE_BOOK);
        if (getBookMeta(_titel) != null) {
            WritableBookMeta meta = (WritableBookMeta) book.getItemMeta();
            meta.setPages(getBookMeta(_titel).getPages());
            book.setItemMeta(meta);
            _player.getInventory().addItem(book);
        }
        else  {_player.sendMessage(ChatColor.RED + "book not found");}
    }
    public BookMeta getBookMeta(String _titel){ //gives Back a BookMeta of a book from the yml file
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
                for (int i = 1;  i <= pages.size(); i++) {
                    meta.addPages(GsonComponentSerializer.gson().deserialize(pages.get(i-1)));
                }
                //meta.setPages(pages);
                return meta;
            } else {
                return null;
            }
        }
        return null;
    }
    public void saveBookInHand(Player _player, String _author, String _title) throws IOException { //handels saving of book to yml file
        if (_player.getInventory().getItemInMainHand() instanceof ItemStack) {
            ItemStack bookInHand = _player.getInventory().getItemInMainHand();
            if (bookInHand.getType() == Material.WRITABLE_BOOK) {
                BookMeta meta = (BookMeta) bookInHand.getItemMeta();
                File file = new File("plugins/ownBOOK/books.yml");

                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

                String path = "books." + _title.replace(" ", "_");
                config.set(path + ".author", _author);
                config.set(path + ".title", _title);
                List<String> pages = new ArrayList<>();
                for (int i = 1; i <= meta.getPageCount(); i++) {
                    pages.add(GsonComponentSerializer.gson().serialize( meta.page(i)));
                }
                config.set(path + ".pages", pages); //überschreibt alles mit neuem*/
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
