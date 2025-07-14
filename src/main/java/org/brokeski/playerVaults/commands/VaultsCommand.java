package org.brokeski.playerVaults.commands;

import org.brokeski.playerVaults.PlayerVaults;
import org.brokeski.playerVaults.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class VaultsCommand implements org.bukkit.command.CommandExecutor{

    private final PlayerVaults plugin;

    public VaultsCommand(PlayerVaults plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {

        if(!(sender instanceof Player player)){
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if(!player.hasPermission("playervaults.use")){
            player.sendMessage(ChatUtil.color("&cYou don't have permissions to use vaults."));
        }

        int maxVaults = plugin.getConfig().getInt("vault.max-vaults", 1);
        Inventory gui = Bukkit.createInventory(null, 27, "Your Vaults");

        for(int i=1; i<=maxVaults; i++){
            ItemStack vaultItem = new ItemStack(Material.CHEST);
            ItemMeta meta = vaultItem.getItemMeta();
            meta.setDisplayName(ChatUtil.color("&6Vault #" + i));
            vaultItem.setItemMeta(meta);

            gui.setItem(i - 1, vaultItem);
        }

        player.openInventory(gui);

        return true;
    }
}
