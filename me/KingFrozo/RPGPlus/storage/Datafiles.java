package me.KingFrozo.RPGPlus.storage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.KingFrozo.RPGPlus.Main;
import me.KingFrozo.RPGPlus.utils.JoinGUI;

public class Datafiles implements Listener {
	String Default = "None";

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		createFile(p);
		if(getClass(p.getName()).equals(Default)){
			Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(Main.getInstance(), new Runnable() {

				@Override
				public void run() {
					JoinGUI.openJoinGUI(p);
					
				}
				}, 100L);
	}
	}

	public void createFile(Player p) {
		File pFileDir = new File(Main.getInstance().getDataFolder(), "Players");
		if (!pFileDir.exists()) {
			pFileDir.mkdir();
		}
		File pFile = new File(Main.getInstance().getDataFolder(), "Players/" + p.getName().toLowerCase() + ".yml");
		if (!pFile.exists())
			try {
				pFile.createNewFile();
				List<String> combo = new ArrayList<String>();
				List<String> guilds = new ArrayList<String>();
				combo.add(0, "???");
				combo.add(1, "???");
				combo.add(2, "???");
				FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
				pConfig.set("User", p.getName());
				pConfig.set("Class", Default);
				pConfig.set("Race", Default);
				pConfig.set("Guilds", guilds);
				pConfig.set("Kills", Integer.valueOf(0));
				pConfig.set("Deaths", Integer.valueOf(0));

				pConfig.set("Counter", Integer.valueOf(1));
				pConfig.set("Combo", combo);

				// pConfig.set("Reader", Boolean[].class);
				/*
				 * pConfig.set("ObservationHakiLevel", Integer.valueOf(0));
				 * pConfig.set("ObservationHakiXP", Integer.valueOf(0));
				 * pConfig.set("ConquerorsHaki", Boolean.valueOf(false));
				 * pConfig.set("ConquerorHakiLevel", Integer.valueOf(0));
				 * pConfig.set("ConquerorHakiXP", Integer.valueOf(0));
				 */
				pConfig.save(pFile);
			} catch (Exception e) {
			}
	}

	public static String getClass(String p) {
		File pFile = new File(Main.getInstance().getDataFolder(), "Players/" + p.toLowerCase() + ".yml");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
		String className = pConfig.getString("Class");
		return className;
	}

	// ****************************//

	public String getPlayerName(String p) {
		File pFile = new File(Main.getInstance().getDataFolder(), "Players/" + p.toLowerCase() + ".yml");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
		String name = pConfig.getString("User");
		return name;
	}

	// ****************************//

	public boolean fileExists(String p) {
		File pFile = new File(Main.getInstance().getDataFolder(), "Players/" + p.toLowerCase() + ".yml");
		if (pFile.exists()) {
			return true;
		}
		return false;
	}

	// ****************************//

	public static Integer getKills(String p) {
		File pFile = new File(Main.getInstance().getDataFolder(), "Players/" + p.toLowerCase() + ".yml");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
		int kills = pConfig.getInt("Kills");
		return Integer.valueOf(kills);
	}

	// ****************************//

	public static void addKill(String p) {
		File pFile = new File(Main.getInstance().getDataFolder(), "Players/" + p.toLowerCase() + ".yml");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
		pConfig.set("Kills", Integer.valueOf(pConfig.getInt("Kills") + 1));
		try {
			pConfig.save(pFile);
		} catch (Exception e) {
		}
	}

	// ****************************//

	public static Integer getDeaths(String p) {
		File pFile = new File(Main.getInstance().getDataFolder(), "Players/" + p.toLowerCase() + ".yml");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
		int deaths = pConfig.getInt("Deaths");
		return Integer.valueOf(deaths);
	}

	// ****************************//

	public static void addDeath(String p) {
		File pFile = new File(Main.getInstance().getDataFolder(), "Players/" + p.toLowerCase() + ".yml");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
		pConfig.set("Deaths", Integer.valueOf(pConfig.getInt("Deaths") + 1));
		try {
			pConfig.save(pFile);
		} catch (Exception e) {
		}
	}

	// ****************************//

	public Integer getPoints(String p) {
		File pFile = new File(Main.getInstance().getDataFolder(), "Players/" + p.toLowerCase() + ".yml");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
		int Points = pConfig.getInt("Points");
		return Integer.valueOf(Points);
	}

	// ****************************//

	public void setPoints(String p, int newAmount) {
		File pFile = new File(Main.getInstance().getDataFolder(), "Players/" + p.toLowerCase() + ".yml");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
		pConfig.set("Points", Integer.valueOf(newAmount));
		try {
			pConfig.save(pFile);
		} catch (Exception e) {
		}
	}

	// ****************************//

	public void addPoints(String p, int amountAdded) {
		File pFile = new File(Main.getInstance().getDataFolder(), "Players/" + p.toLowerCase() + ".yml");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
		pConfig.set("Points", Integer.valueOf(pConfig.getInt("Points") + amountAdded));
		try {
			pConfig.save(pFile);
		} catch (Exception e) {
		}
	}

	// ****************************//

	public void takePoints(String p, int amountTaken) {
		File pFile = new File(Main.getInstance().getDataFolder(), "Players/" + p.toLowerCase() + ".yml");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
		int PointsCurrent = pConfig.getInt("Points");
		if (PointsCurrent - amountTaken >= 0) {
			int newAmount = PointsCurrent - amountTaken;
			pConfig.set("Points", Integer.valueOf(newAmount));
		}
		try {
			pConfig.save(pFile);
		} catch (Exception e) {
		}
	}

	public static void setClass(String p, String className) {
		File pFile = new File(Main.getInstance().getDataFolder(), "Players/" + p.toLowerCase() + ".yml");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
		pConfig.set("Class", className);
		try {
			pConfig.save(pFile);
		} catch (Exception e) {
		}
	}

	public static int getCounter(String p) {
		File pFile = new File(Main.getInstance().getDataFolder(), "Players/" + p.toLowerCase() + ".yml");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
		int className = pConfig.getInt("counter");
		return className;
	}

	public static void incrementCounter(String p) {
		File pFile = new File(Main.getInstance().getDataFolder(), "Players/" + p.toLowerCase() + ".yml");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
		pConfig.set("counter", Integer.valueOf(pConfig.getInt("counter") + 1));
		try {
			pConfig.save(pFile);
		} catch (Exception e) {
		}
	}

	public static List<String> getCombos(String p) {
		File pFile = new File(Main.getInstance().getDataFolder(), "Players/" + p.toLowerCase() + ".yml");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
		List<String> combos = pConfig.getStringList("Combo");
		return combos;
	}

	public static void setCombos(String p, int index, String click) {
		File pFile = new File(Main.getInstance().getDataFolder(), "Players/" + p.toLowerCase() + ".yml");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
		List<String> combos = pConfig.getStringList("Combo");
		combos.remove(index);
		combos.add(index, click);
		pConfig.set("Combo", combos);
		try {
			pConfig.save(pFile);
		} catch (Exception e) {
		}
	}

	public static void resetCounter(String p) {
		File pFile = new File(Main.getInstance().getDataFolder(), "Players/" + p.toLowerCase() + ".yml");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
		pConfig.set("counter", Integer.valueOf(1));
		try {
			pConfig.save(pFile);
		} catch (Exception e) {
		}
	}

	public static String getRace(String p) {
		File pFile = new File(Main.getInstance().getDataFolder(), "Players/" + p.toLowerCase() + ".yml");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
		String raceName = pConfig.getString("Race");
		return raceName;
	}

}
