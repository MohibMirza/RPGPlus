package me.KingFrozo.RPGPlus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import me.KingFrozo.RPGPlus.storage.Datafiles;

public class Combos {
	public void checkCombos(PlayerInteractEvent e){
		Player p = (Player) e.getPlayer();
		List<String> parry = new ArrayList<String>();
		parry.add(0, "Right");
		parry.add(1, "Left");
		parry.add(2, "Right");
		if (Datafiles.getCombos(p.getName()).equals(parry)) {
		Effects.heartBeam(e);
		}
		List<String> watercombo = new ArrayList<String>();
		watercombo.add(0, "Right");
		watercombo.add(1, "Left");
		watercombo.add(2, "Left");
		if (Datafiles.getCombos(p.getName()).equals(watercombo)) {
		Effects.fireBarrier(e);
		}
	}
}
