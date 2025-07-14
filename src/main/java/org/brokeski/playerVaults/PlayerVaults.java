package org.brokeski.playerVaults;

import org.brokeski.playerVaults.commands.VaultCommand;
import org.brokeski.playerVaults.commands.VaultsCommand;
import org.brokeski.playerVaults.commands.VaultsGUIListener;
import org.brokeski.playerVaults.listeners.InventoryListener;
import org.brokeski.playerVaults.storage.VaultManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerVaults extends JavaPlugin {

    private static PlayerVaults instance;
    private VaultManager vaultManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        this.vaultManager = new VaultManager(this);

        //both of these are different btw
        getCommand("vault").setExecutor(new VaultCommand(this));
        getCommand("vaults").setExecutor(new VaultsCommand(this));

        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
        getServer().getPluginManager().registerEvents(new VaultsGUIListener(this), this);

        getLogger().info("PlayerVaults enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("PlayerVaults disabled.");
    }

    public VaultManager getVaultManager() {
        return vaultManager;
    }

    public static PlayerVaults getInstance() {
        return instance;
    }

    public int getMaxVaults(Player player) {
        for (int i = 10; i >= 1; i--) { // Adjust max as needed
            if (player.hasPermission("playervaults.maxvaults." + i)) {
                return i;
            }
        }
        return getConfig().getInt("vault.max-vaults", 1);
    }
}
