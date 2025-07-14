package org.brokeski.playerVaults.listeners;

import org.brokeski.playerVaults.PlayerVaults;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryListener implements Listener {

    private final PlayerVaults plugin;

    public InventoryListener(PlayerVaults plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        if(event.getPlayer() instanceof Player player){
            plugin.getVaultManager().handleClose(player, event.getInventory());
        }
    }
}
