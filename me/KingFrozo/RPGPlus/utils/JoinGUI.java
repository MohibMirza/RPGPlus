package me.KingFrozo.RPGPlus.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.KingFrozo.RPGPlus.Main;
import me.KingFrozo.RPGPlus.storage.Datafiles;

public class JoinGUI implements Listener {
	public static Inventory inv;
	static String title = "Race Chooser";

	public static void openJoinGUI(Player p) {
		inv = Bukkit.createInventory(null, 27, title);
		List<String> WizardLore = new ArrayList<String>();
		
		
		List<String> SwordsmanLore = new ArrayList<String>();

		
		List<String> ArcherLore = new ArrayList<String>();
		
		
		List<String> TopLore = new ArrayList<String>();


		ItemStack WizardChoose = new ItemStack(Material.BLAZE_ROD);
		ItemMeta WizardMeta = WizardChoose.getItemMeta();
		ItemStack SwordsmanChoose = new ItemStack(Material.IRON_SWORD);
		ItemMeta SwordsmanMeta = SwordsmanChoose.getItemMeta();
		ItemStack ArcherChoose = new ItemStack(Material.BOW);
		ItemMeta ArcherMeta = ArcherChoose.getItemMeta();
		ItemStack Top = new ItemStack(Material.WRITTEN_BOOK);
		ItemMeta TopMeta = Top.getItemMeta();
		

		WizardMeta.setDisplayName(Main.colorThis("&9Wizard"));
		WizardMeta.setLore(WizardLore);
		WizardChoose.setItemMeta(WizardMeta);
		
		TopMeta.setDisplayName(Main.colorThis("&c&lRealm of Mythos"));

		SwordsmanMeta.setDisplayName(Main.colorThis("&4Swordsman"));
		SwordsmanMeta.setLore(SwordsmanLore);
		SwordsmanChoose.setItemMeta(SwordsmanMeta);
		ArcherMeta.setDisplayName(Main.colorThis("&cArcher"));
		ArcherMeta.setLore(ArcherLore);
		ArcherChoose.setItemMeta(ArcherMeta);
		TopMeta.setLore(TopLore);
		Top.setItemMeta(TopMeta);
		
		inv.setItem(4, Top);
		inv.setItem(12, WizardChoose);
		inv.setItem(13, SwordsmanChoose);
		inv.setItem(14, ArcherChoose);
		ItemStack block = new ItemStack(Material.STAINED_GLASS_PANE);
		block.getItemMeta().setDisplayName("");
		
		for(int i = 0; i < 27; i++){
			if(i == 4 || i == 12 || i == 13 || i == 14){
				
			}else{
				inv.setItem(i, block);
			}
		}

		p.openInventory(inv);
	}

	@EventHandler
	public void clickManager(InventoryClickEvent e) {
		if (inv == null || !e.getInventory().equals(inv))
			return;
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Wizard")) {
			Datafiles.setClass(e.getWhoClicked().getName(), "Wizard");
			e.getWhoClicked().sendMessage(Main.colorThis("&aCongratulations, you have now become a Wizard."));
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Swordsman")) {
			Datafiles.setClass(e.getWhoClicked().getName(), "Swordsman");
			e.getWhoClicked().sendMessage(Main.colorThis("&aCongratulations, you have now become a Swordsman."));
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Archer")) {
			Datafiles.setClass(e.getWhoClicked().getName(), "Archer");
			e.getWhoClicked().sendMessage(Main.colorThis("&aCongratulations, you have now become an Archer."));
		}
		if(Datafiles.getClass(e.getWhoClicked().getName()) != "None"){
			e.getWhoClicked().closeInventory();
		}
		e.getWhoClicked().sendMessage("test");
		e.setCancelled(true);
	}

	@EventHandler
	public void reopenInv(InventoryCloseEvent e) {
		if (e.getInventory().equals(inv)) {
			openJoinGUI((Player) e.getPlayer());
		}
	}
}
