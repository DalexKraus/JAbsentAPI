package at.thejano.absentapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin implements Listener {

    HashMap<UUID, Integer> allAfkPlayers = new HashMap<>();

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);

        new JAbsentAPI(this);

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {

            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                Integer afkSeconds = allAfkPlayers.get(player.getUniqueId());
                if (afkSeconds == null) afkSeconds = 0;

                afkSeconds++;
                allAfkPlayers.put(player.getUniqueId(), afkSeconds);
            }

        }, 0, 20L);

        System.out.println("[AbsentAPI] API geladen!");
    }

    @Override
    public void onDisable() {
        System.out.println("[AbsentAPI] API deaktiviert!");
    }

    @EventHandler
    public void onMoveEvent(PlayerMoveEvent e) {
        allAfkPlayers.remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent e) {
        allAfkPlayers.remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {
        allAfkPlayers.remove(e.getPlayer().getUniqueId());
    }
}
