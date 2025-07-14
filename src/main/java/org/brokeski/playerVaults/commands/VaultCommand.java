package org.brokeski.playerVaults.commands;

import org.brokeski.playerVaults.PlayerVaults;
import org.brokeski.playerVaults.utils.ChatUtil;
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
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if (!player.hasPermission("playervaults.use")) {
            player.sendMessage(ChatUtil.color("&cYou don't have permission."));
            return true;
        }

        int vaultNumber = 1;
        if (args.length >= 1) {
            try {
                vaultNumber = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                player.sendMessage(ChatUtil.color(plugin.getConfig().getString("vault.messages.invalid-number")));
                return true;
            }
        }

        int maxVaults = plugin.getConfig().getInt("vault.max-vaults", 1);
        if (vaultNumber < 1 || vaultNumber > maxVaults) {
            player.sendMessage(ChatUtil.color(plugin.getConfig().getString("vault.messages.too-many")));
            return true;
        }

        plugin.getVaultManager().openVault(player, vaultNumber);
        String msg = plugin.getConfig().getString("vault.messages.open", "&aOpening vault #%number%...");
        player.sendMessage(ChatUtil.color(msg.replace("%number%", String.valueOf(vaultNumber))));
        return true;
    }
}