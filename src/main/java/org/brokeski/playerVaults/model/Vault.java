package org.brokeski.playerVaults.model;

import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class Vault {

    private final UUID owner;
    private final Inventory inventory;
    private long lastOpened;

    public Vault(UUID owner, Inventory inventory) {
        this.owner = owner;
        this.inventory = inventory;
        this.lastOpened  = System.currentTimeMillis();
    }

    public UUID getOwner() {
        return owner;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public long getLastOpened(){
        return lastOpened;
    }

    public void updateLastOpened() {
        this.lastOpened = System.currentTimeMillis();
    }
}
