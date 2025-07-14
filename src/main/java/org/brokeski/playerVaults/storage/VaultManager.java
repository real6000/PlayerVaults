package org.brokeski.playerVaults.storage;

import org.brokeski.playerVaults.PlayerVaults;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.UUID;

public class VaultManager {

    private final PlayerVaults plugin;
    private final HashMap<UUID, Inventory> openVaults = new HashMap<>();
    private final VaultDataHandler dataHandler;


    public VaultManager(PlayerVaults plugin) {
        this.plugin = plugin;
        this.dataHandler = new VaultDataHandler(plugin); // single argument here
    }

    public void openOtherVault(Player viewer, UUID targetUUID, String targetName, int number) {
        Inventory inv = dataHandler.loadVault(targetUUID, number);
        inv = Bukkit.createInventory(viewer, inv.getSize(), targetName + "'s Vault #" + number);
        inv.setContents(dataHandler.loadVault(targetUUID, number).getContents());
        viewer.openInventory(inv);
    }

    public void openVault(Player player, int number) {
        UUID uuid = player.getUniqueId();
        Inventory vault = dataHandler.loadVault(uuid, number);

        openVaults.put(uuid, vault);
        player.openInventory(vault);
    }

    public void handleClose(Player player, Inventory inv) {
        UUID uuid = player.getUniqueId();
        if (openVaults.containsKey(uuid)) {
            int vaultNumber = getVaultNumberFromTitle(inv.getClass().getName());
            dataHandler.saveVault(uuid, inv, vaultNumber);
            openVaults.remove(uuid);
        }
    }

    private int getVaultNumberFromTitle(String title) {
        try {
            if (title.toLowerCase().contains("vault #")) {
                return Integer.parseInt(title.replaceAll("[^0-9]", ""));
            }
        } catch (NumberFormatException ignored) {}
        return 1; // fallback
    }
}
