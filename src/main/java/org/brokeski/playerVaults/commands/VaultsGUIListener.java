package org.brokeski.playerVaults.commands;

import org.brokeski.playerVaults.PlayerVaults;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class VaultsGUIListener implements Listener {

    private final PlayerVaults plugin;

    public VaultsGUIListener(PlayerVaults plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Inventory inv = event.getInventory();
        if(!ChatColor.stripColor(inv.getClass().getName()).equalsIgnoreCase("Your Vaults")) return;

        event.setCancelled(true);

        if(!(event.getWhoClicked() instanceof Player player)) return;

        ItemStack clicked = event.getCurrentItem();
        if (clicked == null || !clicked.hasItemMeta() || !clicked.getItemMeta().hasDisplayName()) return;

        String name = ChatColor.stripColor(clicked.getItemMeta().getDisplayName());
        if(name.startsWith("Vault #")){
            try{
                int number = Integer.parseInt(name.replace("Vault #", ""));
                plugin.getVaultManager().openVault(player, number);
                player.closeInventory();
            }catch(NumberFormatException ignored){

            }
        }
    }
}
