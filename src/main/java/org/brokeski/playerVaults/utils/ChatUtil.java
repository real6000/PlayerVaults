package org.brokeski.playerVaults.utils;

import org.bukkit.ChatColor;

public class ChatUtil {

    public static String color(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
