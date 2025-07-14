package org.brokeski.playerVaults;

import org.brokeski.playerVaults.storage.VaultManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerVaults extends JavaPlugin {

    private static PlayerVaults instance;
    private VaultManager vaultManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();

        this.vaultManager = new VaultManager(this);
        getCommand("vault").setExecutor(new VaultCommand(this));
        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);

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
}
