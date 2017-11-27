package me.KingFrozo.RPGPlus.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.KingFrozo.RPGPlus.Main;
import me.KingFrozo.RPGPlus.storage.Datafiles;

public class SetCommand implements CommandExecutor{
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		if(label.equalsIgnoreCase("setClass")){
			if(sender.hasPermission("mythos.admin")){
			if(args.length < 2){
				sender.sendMessage(ChatColor.RED + "/setclass <Username> <Class>");
			}else{
				//if(Bukkit.getPlayer(args[0]) != null){
					if(args[1].equalsIgnoreCase("Wizard")){
						Datafiles.setClass(args[0], args[1]);
						sender.sendMessage(ChatColor.GREEN + "Class set.");
					}else if(args[1].equalsIgnoreCase("Swordsman")){
						Datafiles.setClass(args[0], args[1]);
						sender.sendMessage(ChatColor.GREEN + "Class set.");
					}else if(args[1].equalsIgnoreCase("Archer")){
						Datafiles.setClass(args[0], args[1]);
						sender.sendMessage(ChatColor.GREEN + "Class set.");
					}else{
						sender.sendMessage(ChatColor.RED + "Invalid class. Choose Brawler or Slasher.");
						Datafiles.setClass(args[0], "Invalid Class (Contact an admin)");
					}
				/*}else{
					sender.sendMessage("Player is not online or invalid.");
				}*/
			}
			}else{
				sender.sendMessage(Main.colorThis("&cYou are not authorized to modify other users classes!"));
			}
		}
		return false;
	  }
	  

}
