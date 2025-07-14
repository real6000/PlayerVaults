package org.brokeski.playerVaults.storage;

import org.brokeski.playerVaults.PlayerVaults;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class VaultDataHandler {

    private final PlayerVaults plugin;
    private final File vaultsFolder;

    public VaultDataHandler(PlayerVaults plugin, File vaultsFolder) {
        this.plugin = plugin;
        this.vaultsFolder = new File(plugin.getDataFolder(), "vaults");

        if(!vaultsFolder.exists()){
            vaultsFolder.mkdirs();
        }
    }

    public Inventory loadVault(UUID uuid){
        File file = new File(vaultsFolder, uuid.toString() + ".yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        int rows = plugin.getConfig().getInt("vaults.rows", 6);
        Inventory inv = Bukkit.createInventory(null, rows * 9, "Your Vault");

        if(file.exists()){
            for(int i = 0; i < inv.getSize(); i++){
                ItemStack item = config.getItemStack("slot."+i);
                if(item!=null){
                    inv.setItem(i, item);
                }
            }
        }
        return inv;
    }

    public void saveVault(UUID uuid, Inventory inv){
        File file = new File(vaultsFolder, uuid.toString() +".yml");
        YamlConfiguration config = new YamlConfiguration();

        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);
            if (item != null) {
                config.set("slot." + i, item);
            }
        }

        try {
            config.save(file);
        } catch (IOException e) {
            plugin.getLogger().warning("Failed to save vault for " + uuid);
            e.printStackTrace();
        }
    }
}
