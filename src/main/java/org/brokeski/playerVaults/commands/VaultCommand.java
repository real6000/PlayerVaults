package org.brokeski.playerVaults.commands;

import org.brokeski.playerVaults.PlayerVaults;
import org.brokeski.playerVaults.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
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

        // Admin opening someone else's vault: /vault <player> [number]
        if (args.length >= 1 && !isNumeric(args[0])) {
            if (!player.hasPermission("playervaults.admin")) {
                player.sendMessage(ChatUtil.color("&cYou don't have permission to view others' vaults."));
                return true;
            }

            String targetName = args[0];
            int vaultNumber = 1;

            if (args.length >= 2) {
                if (!isNumeric(args[1])) {
                    player.sendMessage(ChatUtil.color("&cInvalid vault number."));
                    return true;
                }
                vaultNumber = Integer.parseInt(args[1]);
            }

            OfflinePlayer target = Bukkit.getOfflinePlayer(targetName);
            if (target == null || (!target.hasPlayedBefore() && !target.isOnline())) {
                player.sendMessage(ChatUtil.color("&cPlayer not found."));
                return true;
            }

            plugin.getVaultManager().openOtherVault(player, target.getUniqueId(), targetName, vaultNumber);
            player.sendMessage(ChatUtil.color("&aOpening " + targetName + "'s vault #" + vaultNumber));
            return true;
        }

        // Self vault: /vault or /vault <number>
        if (!player.hasPermission("playervaults.use")) {
            player.sendMessage(ChatUtil.color("&cYou don't have permission to use vaults."));
            return true;
        }

        int vaultNumber = 1;
        if (args.length == 1) {
            try {
                vaultNumber = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                player.sendMessage(ChatUtil.color(plugin.getConfig().getString("vault.messages.invalid-number", "&cInvalid vault number.")));
                return true;
            }
        }

        int maxVaults = plugin.getMaxVaults(player);
        if (vaultNumber < 1 || vaultNumber > maxVaults) {
            player.sendMessage(ChatUtil.color(plugin.getConfig().getString("vault.messages.too-many", "&cYou do not have access to that many vaults.")));
            return true;
        }

        plugin.getVaultManager().openVault(player, vaultNumber);
        String msg = plugin.getConfig().getString("vault.messages.open", "&aOpening vault #%number%...");
        player.sendMessage(ChatUtil.color(msg.replace("%number%", String.valueOf(vaultNumber))));
        return true;
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
