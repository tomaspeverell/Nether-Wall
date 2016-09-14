package com.myplugin.wall;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scoreboard.Team;

public class MyListener implements Listener {
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent move) {
    	if(!Wall.active) return;
    	Player p = move.getPlayer();
    	if(p.isOp()) return;
    	Location l = p.getLocation();
    	if(!l.getWorld().getName().endsWith("_nether")) return;
    	String teamName = Bukkit.getServer()
    				   .getScoreboardManager()
    				   .getMainScoreboard()
    				   .getPlayerTeam(p)
    				   .getName();
    	int delta = 0;
    	boolean teleport = false;
    	if(Wall.coord == 'x') {
    		delta = l.getBlockX() - Wall.wall_x;
    		if(delta > 0 && teamName.equalsIgnoreCase(Wall.smaller_x_team)
    				|| delta < 0 && teamName.equalsIgnoreCase(Wall.bigger_x_team))
    			teleport = true;
    			
    		l.setX(Wall.wall_x);
    		
    	}
    	else if(Wall.coord == 'z') {
    		delta = l.getBlockZ() - Wall.wall_z;
    		if(delta > 0 && teamName.equalsIgnoreCase(Wall.smaller_z_team)
        			|| delta < 0 && teamName.equalsIgnoreCase(Wall.bigger_z_team))
    			teleport = true;
    		l.setZ(Wall.wall_z); 
    	}
    	if(teleport) {
	        p.teleport(l);
	        p.sendMessage("You crossed the faction boundary");
	       }
    	}    
    }
