package com.myplugin.wall;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class Wall extends JavaPlugin {
	public static int wall_z = 0;
	public static int wall_x = 0;
	public static char coord = 'x';
	public static boolean active = true;
	
	public static String smaller_x_team = "oldempire";
	public static String bigger_x_team = "newempire";
	public static String smaller_z_team = "oldempire";
	public static String bigger_z_team = "newempire";
	
	private YamlConfiguration yaml;
	
    @Override
    public void onEnable() {
    	 getServer().getPluginManager().registerEvents(new MyListener(), this);
    	 readConfig();
    }
   
    @Override
    public void onDisable() {
    	writeConfig();
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command,
    		String label, String[] args) {
    	String cmd = command.getName();
    	if(cmd.equalsIgnoreCase("setwallz")) {
	    	Player p = (Player)sender;
	    	wall_z = p.getLocation().getBlockZ();
	    	coord = 'z';
	    	sender.sendMessage("You set the wall z coordinate to : " 
	    		+ wall_z);
    	}
    	if(cmd.equalsIgnoreCase("setwallx")) {
	    	Player p = (Player)sender;
	    	wall_x = p.getLocation().getBlockX();
	    	coord = 'x';
	    	sender.sendMessage("You set the wall x coordinate to : " 
	    		+ wall_x);
    	}
    	if(cmd.equalsIgnoreCase("activatewall")) {
	    	active = true;
	    	sender.sendMessage("The wall is now active");
    	}
    	if(cmd.equalsIgnoreCase("deactivatewall")) {
	    	active = false;
	    	sender.sendMessage("The wall is now inactive");
    	}
    	
    	return super.onCommand(sender, command, label, args);
    
    }
    
    private void initConfig() {
        yaml = new YamlConfiguration();
        yaml.createSection("active");
        yaml.createSection("x");
        yaml.createSection("z");
        yaml.createSection("coord");
        yaml.createSection("smaller_x_team");
        yaml.createSection("bigger_x_team");
        yaml.createSection("smaller_z_team");
        yaml.createSection("bigger_z_team");
        yaml.set("active", active);
        yaml.set("x", wall_x);
        yaml.set("z", wall_z);
        yaml.set("coord", "" + coord);
        yaml.set("smaller_x_team", smaller_x_team);
        yaml.set("bigger_x_team", bigger_x_team);
        yaml.set("smaller_z_team", smaller_z_team);
        yaml.set("bigger_z_team", bigger_z_team);
        try {
            yaml.save("plugins/Wall/settings.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void readConfig() {
    	File f = new File("plugins/Wall/settings.yml");
    	if(!f.exists())
    		initConfig();
    	else 
    		yaml = YamlConfiguration.loadConfiguration(f);
    	active = yaml.getBoolean("active");
    	wall_x = yaml.getInt("x");
    	wall_z = yaml.getInt("z");
    	coord = yaml.getString("coord").charAt(0);
    	smaller_x_team = yaml.getString("smaller_x_team");
    	bigger_x_team = yaml.getString("bigger_x_team");
    	smaller_z_team = yaml.getString("smaller_z_team");
    	bigger_z_team = yaml.getString("bigger_z_team");
    }
    
    private void writeConfig() {
    	yaml.set("active", active);
        yaml.set("x", wall_x);
        yaml.set("z", wall_z);
        yaml.set("coord", "" + coord);
    	try {
            yaml.save("plugins/Wall/settings.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
