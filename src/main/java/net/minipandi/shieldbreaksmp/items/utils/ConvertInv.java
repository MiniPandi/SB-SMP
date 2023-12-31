package net.minipandi.shieldbreaksmp.items.utils;

import net.minipandi.shieldbreaksmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Base64;

public class ConvertInv {

    public static String serializeInventory(Inventory inventory) {
        StringBuilder serializedInventory = new StringBuilder();
        for (ItemStack itemStack : inventory.getContents()) {
            if (itemStack != null) {
                serializedInventory.append(Base64.getEncoder().encodeToString(itemStack.serializeAsBytes()));
                serializedInventory.append(";");
            } else {
                serializedInventory.append("null;");
            }
        }
        return serializedInventory.toString();
    }

    public static Inventory deserializeInventory(String serializedInventory, String title) {
        Inventory inventory = Bukkit.createInventory(null, 9 * 2, title);
        String[] serializedItems = serializedInventory.split(";");
        int i = 0;
        for (String serializedItem : serializedItems) {
            if (!serializedItem.equals("null")) {
                byte[] itemBytes = Base64.getDecoder().decode(serializedItem);
                ItemStack itemStack = ItemStack.deserializeBytes(itemBytes);
                inventory.setItem(i, itemStack);
            }
            i++;
        }
        return inventory;
    }

    public static void saveInventoryToItem(ItemStack itemStack, String serializedInventory) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(Main.main(), "inventory"), PersistentDataType.STRING, serializedInventory);
        itemStack.setItemMeta(meta);
    }

    public static String loadInventoryFromItem(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta.getPersistentDataContainer().has(new NamespacedKey(Main.main(), "inventory"), PersistentDataType.STRING)) {
            return meta.getPersistentDataContainer().get(new NamespacedKey(Main.main(), "inventory"), PersistentDataType.STRING);
        }
        return null;
    }


    /*public static String InvToString(Inventory inventory) {
        String inv = null;
        try {
            // Create a ByteArrayOutputStream to store the compressed inventory
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            GZIPOutputStream gzipStream = new GZIPOutputStream(byteStream);
            BukkitObjectOutputStream dataStream = new BukkitObjectOutputStream(gzipStream);

            // Write the player's inventory to the data stream
            dataStream.writeInt(inventory.getSize());
            for (ItemStack item : inventory.getContents()) {
                dataStream.writeObject(item);
            }

            // Close the streams
            dataStream.close();
            gzipStream.close();
            byteStream.close();

            // Convert the compressed inventory data to a base64-encoded string
            inv = Base64Coder.encodeLines(byteStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inv;
    }

    public static Inventory StringToInv(String string) {

        try {
            byte[] decodedBytes = Base64Coder.decodeLines(string);

            ByteArrayInputStream byteStream = new ByteArrayInputStream(decodedBytes);
            GZIPInputStream gzipStream = new GZIPInputStream(byteStream);
            BukkitObjectInputStream dataStream = new BukkitObjectInputStream(gzipStream);

            int inventorySize = dataStream.readInt();
            Inventory inventory = Bukkit.createInventory(null, inventorySize);
            inventory.clear();
            inventory.setContents(new ItemStack[inventorySize]);

            for (int i = 0; i < inventorySize; i++) {
                ItemStack item = (ItemStack) dataStream.readObject();
                inventory.setItem(i, item);
            }

            dataStream.close();
            gzipStream.close();
            byteStream.close();
            return inventory;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

}
