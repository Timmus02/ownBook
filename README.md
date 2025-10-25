# ownBook
[![Ask DeepWiki](https://devin.ai/assets/askdeepwiki.png)](https://deepwiki.com/Timmus02/ownBook.git)

ownBook is a simple yet powerful Bukkit plugin for Minecraft servers that allows for the creation, management, and distribution of custom in-game books. Administrators can write books, save them to a central configuration file, and then distribute them to players. The plugin also supports updating books that players already have in their inventories, ensuring everyone has the latest version.

## Features

*   **Save Custom Books:** Save the contents of a `Book and Quill` to a `books.yml` file.
*   **Distribute Books:** Give players a copy of any saved book using a simple command.
*   **Editable Copies:** Get a writable version of a saved book to easily make edits.
*   **Update In-Inventory Books:** Players can update their copies of a book to the latest saved version.
*   **Player-Specific Commands:** Give books to yourself or other online players.
*   **Tab Completion:** Commands support tab completion for book titles and player names to speed up usage.

## Installation

1.  Download the latest `.jar` file. --> build/libs
2.  Place the `ownBook.jar` file into the `/plugins` directory of your Bukkit/Spigot/Paper server.
3.  Restart or reload your server. The plugin will create a `plugins/ownBOOK/` directory.

## Usage Workflow

1.  **Create your book:** Get a `Book and Quill` (`WRITABLE_BOOK`) in-game and write the content you want.
2.  **Save the book:** While holding the `Book and Quill`, use the command:
    `/ownBook saveBookInHand <Author> <Title>`
    *   Replace `<Author>` with the author's name.
    *   Replace `<Title>` with the book's title. Spaces are allowed.
    *   This saves the book to `plugins/ownBOOK/books.yml`.
3.  **Distribute the book:** To give a finalized copy (`WRITTEN_BOOK`) to a player, use:
    `/ownBook create <Title> [PlayerName]`
    *   If `[PlayerName]` is omitted, the book will be given to you.
4.  **Edit an existing book:**
    a. Get an editable version: `/ownBook getWritableBook <Title>`
    b. Make your changes in-game.
    c. Save the book again using the same command as in step 2. This will overwrite the previous version in the configuration file.
5.  **Update books:** To update a book that a player already has, they can run:
    `/ownBook update <Title>`
    *   This will find all books with the matching title in their inventory and update their content to the latest saved version.

## Commands and Permissions

The main command is `/ownBook`.

| Command                                    | Description                                                  | Permission              |
| ------------------------------------------ | ------------------------------------------------------------ | ----------------------- |
| `/ownBook create <title> [player]`         | Gives a player a copy of the specified book.                 | `ownBook.command`       |
| `/ownBook update <title> [player]`         | Updates all copies of a book in a player's inventory.        | `ownBook.command`       |
| `/ownBook getWritableBook <title>`         | Gives the user an editable `Book & Quill` of a saved book.   | `ownBook.getWritableBook` |
| `/ownBook saveBookInHand <author> <title>` | Saves the `Book & Quill` in the user's hand to the config file. | `ownBook.saveBookInHand`  |

## Configuration

The plugin stores all book data in the `plugins/ownBOOK/books.yml` file. While you can manually edit this file, it is recommended to use the in-game commands to prevent formatting errors.

The structure of the file is as follows:

```yaml
books:
  Example_Book_Title:
    author: Notch
    title: Example Book Title
    pages:
    - 'This is the first page of the book. You can use standard Minecraft formatting codes.'
    - 'This is the second page.'
  Another_Book:
    author: Timmus02
    title: Another Book
    pages:
    - 'Content for another book...'
```

*   The top-level key under `books` (e.g., `Example_Book_Title`) is the book's title with spaces replaced by underscores. This is used internally for lookups.
*   `author`: The author's name as a string.
*   `title`: The display title of the book.
*   `pages`: A list of strings, where each string is a page in the book.

## Since Version 2.0 --> Colors over Compoenents
Since Minecrafe 1.21.5 Colors and other Functionalitys are implentet over Compoents. It dosnt work with §!.
For it to work just write your Book and save it. go to `plugins/ownBOOK/books.yml` and edit it with the right format.
Further Information on Components under https://minecraft.wiki/w/Text_component_format

### Example
```yaml
books:
  new:
    author: new
    title: new
    pages:
    - '{text:"test", color:"red"}'
    - '"test"'
```
Generates red text on Page1

GENERATED WITH GitRead --> https://www.gitread.dev
