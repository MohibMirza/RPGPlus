package me.KingFrozo.RPGPlus.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;

import me.KingFrozo.RPGPlus.storage.Datafiles;

public class StatsGUI implements CommandExecutor, Listener{
	Inventory inv;
	  public void openStatsGUI(Player p){
		  inv = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Stats");
		  /*List<String> armamentLore = new ArrayList();
		  armamentLore.add(ChatColor.BLUE + "Level: "+ ChatColor.GREEN + getArmamentHakiLevel(p.getName()));
		  armamentLore.add(ChatColor.BLUE + "XP: " + ChatColor.GREEN + getArmamentHakiXP(p.getName()));
		  armamentLore.add(ChatColor.BLUE + "XP Needed: " + ChatColor.GREEN +  ((armamentHakiLevels[getArmamentHakiLevel(p.getName()) + 1])- (getArmamentHakiXP(p.getName()))));
		  
		  List<String> observationLore = new ArrayList();
		  observationLore.add(ChatColor.BLUE + "Level: "+ ChatColor.GREEN + getObservationHakiLevel(p.getName()));
		  observationLore.add(ChatColor.BLUE + "XP: " + ChatColor.GREEN + getObservationHakiXP(p.getName()));
		  observationLore.add(ChatColor.BLUE + "XP Needed: " + ChatColor.GREEN +  ((armamentHakiLevels[getObservationHakiLevel(p.getName()) + 1])- (getObservationHakiXP(p.getName()))));
		  
		  List<String> conquerorLore = new ArrayList();
		  conquerorLore.add(ChatColor.BLUE + "Level: "+ ChatColor.GREEN + getConquerorHakiLevel(p.getName()));
		  conquerorLore.add(ChatColor.BLUE + "XP: " + ChatColor.GREEN + getConquerorHakiXP(p.getName()));
		  conquerorLore.add(ChatColor.BLUE + "XP Needed: " + ChatColor.GREEN +  ((armamentHakiLevels[getConquerorHakiLevel(p.getName()) + 1])- (getConquerorHakiXP(p.getName()))));
		  */
		  ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		  ItemStack className = new ItemStack(Material.BOOK);
		  ItemMeta classNameMeta = className.getItemMeta();
		  ItemStack kills = new ItemStack(Material.IRON_SWORD);
		  ItemMeta killsMeta = kills.getItemMeta();
		  ItemStack deaths = new ItemStack(Material.BONE);
		  ItemMeta deathsMeta = deaths.getItemMeta();
		  ItemStack armament = new ItemStack(Material.IRON_INGOT);
		  ItemMeta armamentMeta = armament.getItemMeta();
		  ItemStack observation = new ItemStack(Material.NETHER_STAR);
		  ItemMeta observationMeta = observation.getItemMeta();
		  ItemStack conqueror = new ItemStack(Material.BLAZE_ROD);
		  ItemMeta conquerorMeta = observation.getItemMeta();

		  SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
		  skullMeta.setOwner(p.getName());
		  skullMeta.setDisplayName(ChatColor.GOLD + p.getName() + "'s Stats");
		  skull.setItemMeta(skullMeta);
		  
		  classNameMeta.setDisplayName(ChatColor.GOLD + "Class: " + ChatColor.BLUE + Datafiles.getClass(p.getName()));
		  className.setItemMeta(classNameMeta);
		  
		  killsMeta.setDisplayName(ChatColor.GOLD + "Kills: " + ChatColor.BLUE + Datafiles.getKills(p.getName()));
		  kills.setItemMeta(killsMeta);
		  
		  deathsMeta.setDisplayName(ChatColor.GOLD + "Deaths: " + ChatColor.BLUE + Datafiles.getDeaths(p.getName()));
		  deaths.setItemMeta(deathsMeta);
		  
		  
		  armamentMeta.setDisplayName(ChatColor.GOLD + "Armament Haki");
		 // armamentMeta.setLore(armamentLore);
		  armament.setItemMeta(armamentMeta);
		  
		  observationMeta.setDisplayName(ChatColor.GOLD + "Observation Haki");
		  //observationMeta.setLore(observationLore);
		  observation.setItemMeta(observationMeta);
		  
		  conquerorMeta.setDisplayName(ChatColor.GOLD + "Conqueror Haki");
		  // conquerorMeta.setLore(conquerorLore);
		  conqueror.setItemMeta(conquerorMeta);
		  
		  inv.setItem(0, skull);
		  inv.setItem(9, className);
		  inv.setItem(4, kills);
		  inv.setItem(13, deaths);
		  inv.setItem(8, armament);
		  inv.setItem(17, observation);
		  inv.setItem(26, conqueror);

		  
		  p.openInventory(inv);
	  }
	  
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		  Player p = (Player) sender;
		  if(label.equalsIgnoreCase("stats")){
	    if (args.length == 0)
	    {
	    openStatsGUI(p);
	    
	    }else if(args.length == 1){
	    	Player playerStat = (Player) Bukkit.getPlayer(args[0]);
	    	if(playerStat != null){
	    		// openStatsGUIOtherPlayer(playerStat, p);
	    	}else{
	    		p.sendMessage(ChatColor.RED + args[0] + " is not a valid player. You can only view stats of online players.");
	    	}
	    }
		return false;
	      
		  }
		return false;
	  }
	  
	  @EventHandler
	  public void playerDeath(PlayerDeathEvent e) {
	      Player player = (Player) e.getEntity();   
	      if (player.getKiller() instanceof Player) { 
	          Bukkit.broadcastMessage(ChatColor.GREEN + player.getKiller().getName() + ChatColor.DARK_GRAY + " has killed " + ChatColor.GREEN + player.getName());
	          for (PotionEffect effect : player.getActivePotionEffects())
	              player.removePotionEffect(effect.getType());	   
	          Datafiles.addDeath(player.getName());
	          Datafiles.addKill(player.getKiller().getName());
	          }else{
	        	  Datafiles.addDeath(player.getName());
	          
	      }
	      }
	  
}
