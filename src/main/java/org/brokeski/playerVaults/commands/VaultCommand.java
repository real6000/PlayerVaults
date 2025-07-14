package org.brokeski.playerVaults.commands;

import org.brokeski.playerVaults.PlayerVaults;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VaultCommand implements CommandExecutor {

    private final PlayerVaults plugin;

    public VaultCommand(PlayerVaults plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //prevent console usage
        if(!(sender instanceof Player player)){
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        if (!player.hasPermission("playervaults.use")) {
            player.sendMessage(ChatUtil.color("&cYou don't have permission."));
            return true;
        }

        plugin.getVaultManager().openVault(player);
        player.sendMessage(ChatUtil.color("&aOpening your vault..."));
        return true;
    }
}