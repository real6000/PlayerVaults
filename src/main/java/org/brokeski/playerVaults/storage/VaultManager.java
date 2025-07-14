package org.brokeski.playerVaults.storage;

import org.brokeski.playerVaults.PlayerVaults;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.UUID;

public class VaultManager {

    private final PlayerVaults plugin;
    private final HashMap<UUID, Inventory> openVaults = new HashMap<>();
    private final VaultDataHandler dataHandler;


    public VaultManager(PlayerVaults plugin, VaultDataHandler dataHandler) {
        this.plugin = plugin;
        this.dataHandler = new VaultDataHandler(plugin);
    }

    public void openVault(Player player){
        UUID uuid = player.getUniqueId();
        Inventory vault = dataHandler.loadVault(uuid);

        openVaults.put(uuid, vault);
        player.openInventory(vault);
    }

    public void handleClose(Player player, Inventory inv){
        UUID uuid = player.getUniqueId();
        if(openVaults.containsKey(uuid)){
            dataHandler.saveVault(uuid, inv);
            openVaults.remove(uuid);
        }
    }
}
