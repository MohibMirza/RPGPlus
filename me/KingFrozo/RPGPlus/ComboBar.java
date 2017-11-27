package me.KingFrozo.RPGPlus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

import me.KingFrozo.RPGPlus.Effects;
import me.KingFrozo.RPGPlus.storage.Datafiles;

public class ComboBar implements Listener{
	// Boolean secondRightClick, thirdRightClick;

	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		getCombo(e);
	}

	public void getCombo(PlayerInteractEvent e) {
		final Player p = (Player) e.getPlayer();
		int i = Datafiles.getCounter(p.getName());
		switch (i) {
		case 1:
			if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_AIR)) {
				Datafiles.setCombos(p.getName(), 0, "Right");
				Datafiles.setCombos(p.getName(), 1, "???");			
				Datafiles.setCombos(p.getName(), 2, "???");
				ActionBarAPI.sendActionBar(p, "§a" + Datafiles.getCombos(p.getName()).get(0) + " §7- §7§n" + Datafiles.getCombos(p.getName()).get(1) + "§7 - " + Datafiles.getCombos(p.getName()).get(2));
				Datafiles.incrementCounter(p.getName());
			}else if((e.getAction() == Action.LEFT_CLICK_BLOCK) || (e.getAction() == Action.LEFT_CLICK_AIR)){
				Datafiles.setCombos(p.getName(), 0, "Left");
				Datafiles.setCombos(p.getName(), 1, "???");			
				Datafiles.setCombos(p.getName(), 2, "???");
				ActionBarAPI.sendActionBar(p, "§a" + Datafiles.getCombos(p.getName()).get(0) + " §7- §7§n" + Datafiles.getCombos(p.getName()).get(1) + "§7 - " + Datafiles.getCombos(p.getName()).get(2));
			}
			break;
		case 2:
			if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_AIR)) {
				Datafiles.setCombos(p.getName(), 1, "Right");
				Datafiles.incrementCounter(p.getName());
			} else if ((e.getAction() == Action.LEFT_CLICK_BLOCK) || (e.getAction() == Action.LEFT_CLICK_AIR)) {
				Datafiles.setCombos(p.getName(), 1, "Left");
				Datafiles.incrementCounter(p.getName());
			}
			ActionBarAPI.sendActionBar(p, "§a" + Datafiles.getCombos(p.getName()).get(0) + " §7- §a" + Datafiles.getCombos(p.getName()).get(1) + "§7 - §7§n" + Datafiles.getCombos(p.getName()).get(2));
			break;
		case 3:
			if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_AIR)) {
				Datafiles.setCombos(p.getName(), 2, "Right");
			} else if ((e.getAction() == Action.LEFT_CLICK_BLOCK) || (e.getAction() == Action.LEFT_CLICK_AIR)) {
				Datafiles.setCombos(p.getName(), 2, "Left");
			}
			ActionBarAPI.sendActionBar(p, "§a" + Datafiles.getCombos(p.getName()).get(0) + " §7- §a" + Datafiles.getCombos(p.getName()).get(1) + "§7 - §a" + Datafiles.getCombos(p.getName()).get(2));
			
			Datafiles.resetCounter(p.getName());
			break;
		default:
			Datafiles.resetCounter(p.getName());
			break;
		}
		
	}
}
