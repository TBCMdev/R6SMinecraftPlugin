package me.R6SMC.plugin.Books;

import me.R6SMC.plugin.Chat.GameChat;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import org.bukkit.inventory.meta.BookMeta;

public class CustomBooks {
    public static String Abilities = ChatColor.GRAY +  "-Abilities\n";
    public static String Weapons = ChatColor.GRAY + "-Weapons\n";
    private static ArrayList<BaseComponent[]> BlueBookPages = new ArrayList<>();
    private static ArrayList<BaseComponent[]> RedBookPages = new ArrayList<>();

    public static ItemStack CreateBlueTeamBook(){
        BlueBookPages.removeAll(BlueBookPages);
        ItemStack Book = new ItemStack(Material.WRITTEN_BOOK,1);
        BookMeta bookMeta = (BookMeta)Book.getItemMeta();
        bookMeta.setTitle("BLUEOPERATORS");
        bookMeta.setAuthor("TBCM");
        TextComponent title = GameChat.CreateTextComponent("__PICK AN OPERATOR__");
        ComponentBuilder page1 = new ComponentBuilder("__PICK AN OPERATOR__\n");
        ComponentBuilder page2 = new ComponentBuilder("__DOCS ABILITIES__\n");
        ComponentBuilder page3 = new ComponentBuilder("__ROOKS ABILITIES__\n");
        ComponentBuilder page4 = new ComponentBuilder("__TACHANKAS ABILITIES__\n");
        BaseComponent DOC = new TextComponent(ChatColor.BLUE + "DOC\n");
        DOC.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("CLICK TO USE DOC")));
        DOC.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/defoperator doc"));
        page1.append(DOC);

        BaseComponent ROOK = new TextComponent(ChatColor.BLUE + "ROOK\n");
        ROOK.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("CLICK TO USE ROOK")));
        ROOK.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/defoperator rook"));
        BaseComponent TACHANKA = new TextComponent(ChatColor.BLUE + "TACHANKA\n");
        TACHANKA.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("CLICK TO USE TACHANKA")));
        TACHANKA.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/defoperator tachanka"));

        TextComponent DocAbilInfo = GameChat.CreateTextComponent(Abilities);
        DocAbilInfo.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text(("Info on Docs Abilities!"))));
        DocAbilInfo.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/showPageBlue doc 2"));
        page1.append(ROOK);
        TextComponent RookAbilInfo = GameChat.CreateTextComponent(Abilities);
        RookAbilInfo.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("Info on Rooks Abilities!")));
        RookAbilInfo.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/showPageBlue rook 3"));
        ROOK.addExtra(RookAbilInfo);
        TextComponent TachankaAbilInfo = GameChat.CreateTextComponent(Abilities);
        TachankaAbilInfo.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("Info on tachankas Abilities!")));
        TachankaAbilInfo.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/showPageBlue tachanka 4"));
        TACHANKA.addExtra(TachankaAbilInfo);
        //PAGE 1 COMPLETE
        BaseComponent DocAbilityInfo = new TextComponent(ChatColor.GRAY + "Doc has an ability that Heals himself by 40hp,\n this is useful for clutch situations but \n be careful! he only has 3 of them...\n\n\n");
        BaseComponent DocAbilityReturn = new TextComponent(ChatColor.DARK_BLUE + "Return to Operator Select");
        DocAbilityReturn.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("Click to return")));
        DocAbilityReturn.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/showPageBlue doc 1"));
        DocAbilInfo.addExtra(DocAbilityReturn);
        page2.append(DocAbilityInfo);

        //CREATE THE PAGES AND STORE THEM FOR OTHER CLASSES
        BaseComponent[] P1 = page1.create();
        BaseComponent[] P2 = page2.create();
        BlueBookPages.add(P1);
        BlueBookPages.add(P2);
        bookMeta.spigot().setPages(P1);
        Book.setItemMeta(bookMeta);
        return Book;
    }
    public static boolean CheckForValidPage(int Page,int Team){
        switch (Team){
            case 1:
                if(BlueBookPages.toArray()[Page] != null) {
                    return true;
                }else{
                    return false;
                }
            case 2:
                if(RedBookPages.toArray()[Page] != null) {
                    return true;
                }else{
                    return false;
                }
        }
        return false;
    }
    public static BaseComponent[] GetPage(int page,int team){
        switch (team){
            case 1:
                return BlueBookPages.get(page);
            case 2:
                return RedBookPages.get(page);
        }
        return null;
    }
    public static ItemStack CreateRedTeamBook() {
        ItemStack Book = new ItemStack(Material.WRITTEN_BOOK);
        return Book;
    }


}
