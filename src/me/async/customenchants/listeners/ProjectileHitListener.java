package me.async.customenchants.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.async.customenchants.Main;
import me.async.customenchants.enchantment.LeveledEnchant;
import me.async.customenchants.playerstore.EffectStore;

public class ProjectileHitListener implements Listener
{
	@EventHandler
	public void onProjectileHit(final ProjectileHitEvent ev)
	{
		if (ev.getEntityType() == EntityType.ARROW)
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
        if(ev.getEntity().getShooter() instanceof Player)
        new BukkitRunnable()
        {
			@Override
			public void run() {
				Player player = (Player)ev.getEntity().getShooter();
				for(LeveledEnchant enc : EffectStore.TOOL.getEffects(player))
					enc.handleProjHitEvent(ev);
			}
        }.runTaskLaterAsynchronously(Main.plugin, 0);
	}
}
