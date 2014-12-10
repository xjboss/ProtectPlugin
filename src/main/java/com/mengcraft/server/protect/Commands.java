package com.mengcraft.server.protect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if (args.length < 1) {
			sender.sendMessage(getPluginInfo());
		} else if (args.length < 2) {
			
		} else if (args.length < 3) {
			if (args[0].equals("info")) {
				if (args[1].equals("entity")) {
					sender.sendMessage(getEntityInfo());
				}
			}
		}
		return true;
	}

	private String[] getEntityInfo() {
		Map<EntityType, Integer> entityMap = new HashMap<>();
		int total = 0;
		for (World world : Bukkit.getWorlds()) {
			for (Entity entity : world.getEntities()) {
				EntityType type = entity.getType();
				if (entityMap.get(type) != null) {
					int value = entityMap.remove(type);
					entityMap.put(type, value + 1);
				} else {
					entityMap.put(type, 1);
				}
				total = total + 1;
			}
		}
		List<String> messages = new ArrayList<>();
		for (EntityType type : entityMap.keySet()) {
			messages.add(ChatColor.GOLD + type.name() + ": " + entityMap.get(type));
		}
		messages.add(ChatColor.GOLD + "TOTAL: " + total);
		int size = messages.size();
		return messages.toArray(new String[size]);
	}
	
	private String[] getPluginInfo() {
		String[] strings = new String[] {
				ChatColor.GOLD + "/protect info entity"
		};
		return strings;
	}

}