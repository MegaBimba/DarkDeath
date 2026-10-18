package ru.dark.darkdeath;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class DarkDeathPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("DarkDeath включён. Тьма ждёт.");
    }

    @Override
    public void onDisable() {
        getLogger().info("DarkDeath выключен.");
    }

    /**
     * LOWEST + ignoreCancelled=false, чтобы перехватить ВСЕ смерти,
     * даже если другой плагин отменил событие или уже что-то записал.
     * А затем ставим максимальный приоритет для перезаписи — но проще: 
     * ставим MONITOR, чтобы быть последними.
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDeath(PlayerDeathEvent event) {
        String name = event.getEntity().getName();

        // Adventure-компонент: имя белым, "навечно поглотила тьма." — красным
        Component msg = Component.text("«")
                .append(Component.text(name, NamedTextColor.WHITE))
                .append(Component.text("» навечно поглотила "))
                .append(Component.text("тьма", NamedTextColor.RED))
                .append(Component.text("."));

        event.deathMessage(msg);
    }
}