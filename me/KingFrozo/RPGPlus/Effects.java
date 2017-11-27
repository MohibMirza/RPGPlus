package me.KingFrozo.RPGPlus;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

import me.KingFrozo.RPGPlus.Main;
import me.KingFrozo.RPGPlus.storage.Datafiles;
import me.KingFrozo.RPGPlus.utils.ParticleEffect;

public class Effects implements Listener{
	public static void heartBeam(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		List<String> combo = Datafiles.getCombos(event.getPlayer().getName());
			new BukkitRunnable() {
				double t = 0;
				Location loc = player.getLocation();
				Vector direction = loc.getDirection().normalize();
				public void run() {
					t = t + 0.5;
					double x = direction.getX() * t;
					double y = direction.getY() * t + 1.5;
					double z = direction.getZ() * t;
					loc.add(x, y, z);
					ParticleEffect.HEART.display(0, 0, 0, 4, 5, loc, 30);
					
					for(Entity e: loc.getChunk().getEntities()){
						if(e.getLocation().distance(loc) < 1.0){
							if(!e.equals(player)){
								if(e instanceof Player){
									Player p = (Player) e;
									p.setHealth(p.getHealth()-10);
							}
						}
					}
					}
					loc.subtract(x, y, z);
					if(t == 0.5){
						ActionBarAPI.sendActionBar(player, "§c§lHEAL SPELL §7§lACTIVATED");	
						}
					if (t > 30) {
						this.cancel();
						Location loc1 = new Location(player.getWorld(), x, y, z);
						Bukkit.getWorld(player.getWorld().getName()).createExplosion(loc1, 4.0F);
					}
				}
			}.runTaskTimer(Main.getInstance(), 0, 1);

		}
	
	public static void waterBarrier(PlayerInteractEvent event){
		final Player player = (Player) event.getPlayer();
		final Location loc = (Location) player.getLocation();
		new BukkitRunnable() {
			double phi = 0;
			@Override
			public void run() {
				phi += (Math.PI/10);
				for(double theta = 0; theta <= 2*Math.PI; theta += Math.PI/40){
					double r = 1.5;
					double x = r*Math.cos(theta)*Math.sin(phi);
					double y = r* Math.cos(phi) + 1.5;
					double z = r*Math.sin(theta)*Math.sin(phi);
					loc.add(x,y,z);
					ParticleEffect.DRIP_WATER.display(0, 0, 0, 3, 3, loc, 40);
					loc.subtract(x,y,z);
					if(theta == 0){
						ActionBarAPI.sendActionBar(player, "§c§lWATER BARRIER SPELL §7§lACTIVATED");	

					}
				}
				if(phi > Math.PI){
					this.cancel();
				}
				
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	public static void fireBarrier(PlayerInteractEvent event){
		final Player player = (Player) event.getPlayer();
		final Location loc = (Location) player.getLocation();
		new BukkitRunnable() {
			double phi = 0;
			@Override
			public void run() {
				phi += (Math.PI/10);
				for(double theta = 0; theta <= 2*Math.PI; theta += Math.PI/40){
					double r = 1.5;
					double x = r*Math.cos(theta)*Math.sin(phi);
					double y = r* Math.cos(phi) + 1.5;
					double z = r*Math.sin(theta)*Math.sin(phi);
					loc.add(x,y,z);
					ParticleEffect.FLAME.display(0, 0, 0, 3, 3, loc, 40);
					loc.subtract(x,y,z);
					if(theta == 0){
						ActionBarAPI.sendActionBar(player, Main.colorThis("&c&lFire BARRIER SPELL &7&lACTIVATED"));	

					}
				}
				if(phi > 4*Math.PI){
					this.cancel();
				}
				
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
}
