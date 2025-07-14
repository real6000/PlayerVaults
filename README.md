# 🎒 PlayerVaults

A powerful Minecraft **multi-vault plugin** that lets players create multiple personal vaults — bigger and better than the vanilla Ender Chest! Manage your items across vaults, open them via commands or a sleek GUI, and admins can peek inside too. Perfect for servers looking to enhance player storage with flexibility and control.

---

## ✨ Features

- 🔐 Multiple vaults per player (configurable max vaults)  
- 🧰 Open vaults via `/vault [number]` command  
- 🖱️ Easy-to-use `/vaults` GUI for vault selection  
- 👑 Admins can access other players’ vaults with `/vault <player> [number]`  
- 🔑 Permission-based vault limits (`playervaults.maxvaults.X`)  
- 💾 Vaults auto-saved in YAML files per player and vault  
- 🎨 Color-coded chat messages and customizable config  
- 🛠️ Lightweight and easy to configure  

---

## 📦 Installation

1. Download the latest `PlayerVaults.jar` from the [Releases](https://github.com/yourusername/PlayerVaults/releases) page.  
2. Drop it into your server’s `plugins` folder.  
3. Start your server to generate the config files.  
4. Configure `config.yml` to your liking.  
5. Use `/vault` and `/vaults` commands to get started!  

---

## 📜 Commands

| Command                     | Permission              | Description                                |
|-----------------------------|-------------------------|--------------------------------------------|
| `/vault [number]`            | `playervaults.use`      | Open your vault number 1 (or specified)    |
| `/vault <player> [number]`  | `playervaults.admin`    | Admin: open other player’s vault            |
| `/vaults`                   | `playervaults.use`      | Open the vault selection GUI                |

---

## ⚙️ Permissions

- `playervaults.use` — Allows using vault commands and GUI  
- `playervaults.admin` — Allows accessing other players’ vaults  
- `playervaults.maxvaults.X` — Set max vault count per player (replace `X` with number)  

---

## 🛠 Configuration

Edit `config.yml` to customize vault rows, max vaults, and messages. For example:

```yaml
vault:
  rows: 6
  max-vaults: 3
  messages:
    open: "&aOpening vault #%number%..."
    too-many: "&cYou do not have access to that many vaults."
    invalid-number: "&cInvalid vault number."
