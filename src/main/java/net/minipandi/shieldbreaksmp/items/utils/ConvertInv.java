package net.minipandi.shieldbreaksmp.items.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ConvertInv {

    public static String InvToString(Inventory inventory){
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

    public static Inventory StringToInv(String string){

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
    }

}
