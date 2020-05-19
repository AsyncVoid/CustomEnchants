package me.FlameBlazer.CE2.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.Async.CustomEnchants.Main;
import me.FlameBlazer.CE2.playerstore.EffectStore;

public class ProjectileHitListener implements Listener
{
	@EventHandler
	public void onProjectileHit(final ProjectileHitEvent ev)
	{
		if (ev.getEntityType() != EntityType.ARROW) return;
        new BukkitRunnable() {
        	public void run() {
        		/*int[] pos = ArrowUtil.getPos((CraftArrow) ev.getEntity());
                if (pos[1] != -1) {
                	Block block = ev.getEntity().getWorld().getBlockAt(pos[0], pos[1], pos[2]);
                    Bukkit.getServer().getPluginManager()
                    	.callEvent(
                    			new ArrowHitBlockEvent((Arrow)ev.getEntity(), block)
                        );
                }*/
                EffectStore.ARROW.clearEffects(ev.getEntity().getEntityId());
        	}
        }.runTaskLaterAsynchronously(Main.plugin, 0);
	}
}
