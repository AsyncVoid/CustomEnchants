package me.async.customenchants.listeners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.async.customenchants.Main;
import me.async.customenchants.enchantment.Enchantment;
import me.async.customenchants.enchantment.LeveledEnchant;
import me.async.customenchants.playerstore.EffectStore;

public class PlayerInteractListener implements Listener
{
	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityDamage(EntityDamageByEntityEvent ev)
	{
		if(ev.isCancelled()) return;
		if(ev.getCause() == DamageCause.ENTITY_ATTACK)
		{
			if(ev.getDamager() instanceof Player)
			{
				Player p = (Player)ev.getDamager();
				if(ev.getEntity() instanceof LivingEntity)
				{
					//Bukkit.getServer().broadcastMessage("PLAYER_ATTACK_LIVING");
					for(LeveledEnchant enc : EffectStore.TOOL.getEffects(p))
					{
						Bukkit.getServer().broadcastMessage(enc.getEnchantment().getName());
						enc.handleDamageEvent(ev);
					}
				}
			}
		}
		else if(ev.getCause() == DamageCause.PROJECTILE)
		{
			if(ev.getDamager() instanceof Arrow)
			{
				if(ev.getEntity() instanceof LivingEntity)
				{
					int arrowId = ev.getDamager().getEntityId();
					for(LeveledEnchant enc : EffectStore.ARROW.getEffects(arrowId))
						enc.handleDamageEvent(ev);
					EffectStore.ARROW.clearEffects(arrowId);
				}
			}
		}
		else
			return;
		if(ev.getEntity() instanceof Player) //Handle victims' armour enchants
			for(LeveledEnchant enc : EffectStore.ARMOUR.getEffects((Player)ev.getEntity()))
			{
				Bukkit.getServer().broadcastMessage(enc.getEnchantment().getName());
				enc.handleDamageEvent(ev);
			}
	}
	
	@EventHandler
	public void onItemBreak(PlayerItemBreakEvent ev)
	{
		if(ev.getBrokenItem().hasItemMeta())
		{
			ItemMeta im = ev.getBrokenItem().getItemMeta();
			if(im.hasLore())
			{
				for(String lore : im.getLore())
				{
					LeveledEnchant enc = Enchantment.getFromLore(lore);
					if(enc == null) 
						continue;
					if(enc.getEnchantment() == Enchantment.SHATTERPROOF)
					{
						ev.getBrokenItem().setAmount(1);
						ev.getPlayer().getWorld().dropItemNaturally(ev.getPlayer().getLocation(), ev.getBrokenItem());
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerDeath(final PlayerDeathEvent ev)
	{
		if(!ev.getKeepInventory())
		{
			final List<ItemStack> soulbound = new ArrayList<ItemStack>();
			Iterator<ItemStack> it = ev.getDrops().iterator();
			while(it.hasNext())
			{
				ItemStack drop = it.next();
				for(LeveledEnchant enc : Enchantment.getEnchants(drop))
				{
					if(enc.getEnchantment() == Enchantment.SOULBOUND)
					{
						soulbound.add(drop);
						it.remove();
						break;
					}
				}
			}
			if(!soulbound.isEmpty())
			{
				new BukkitRunnable()
				{
					@Override
					public void run() {
						for(ItemStack is : soulbound)
							ev.getEntity().getInventory().addItem(is);
					}
				}.runTaskLaterAsynchronously(Main.plugin, 1);
			}
		}
	}
}
