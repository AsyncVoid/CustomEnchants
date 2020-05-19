package me.async.customenchants.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.async.customenchants.Main;
import me.async.customenchants.listeners.EnchantTableUpdateListener.TablePerks;
import me.async.customenchants.utils.EnchantUtil;
import me.async.customenchants.utils.ExpUtil;

public class EnchantItemListener implements Listener
{
	@EventHandler
	public void onEnchantItem(final EnchantItemEvent ev)
	{
		Player p = ev.getEnchanter();
		int level = ev.getExpLevelCost();
		//ev.getEnchantsToAdd().clear();
		if(ev.getEnchantsToAdd().isEmpty())
			return;
		//ContainerEnchantTable enct = EnchantUtil.getFromCurrent(p);
		EnchantUtil.CustomEnchant(ev.getItem(), level);
		//EnchantUtil.RegularEnchant(ev.getItem(), level);
		
		int totalXp = ExpUtil.getTotalExperience(p);
		int xpToLvl = ExpUtil.getExperienceToLevel(level);
		if(totalXp >= xpToLvl)
		{
			ExpUtil.setTotalExperience(p, totalXp - xpToLvl);
			ev.setExpLevelCost(0);
			
			ItemStack preIs = ev.getInventory().getItem(1);
			final int preAmount = preIs == null ? 0 : preIs.getAmount();
			
			final TablePerks perks = EnchantTableUpdateListener.perksMap.get(p);
			new BukkitRunnable()
			{
				@Override
				public void run() {
					ItemStack postIs = ev.getInventory().getItem(1);
					int postAmount = postIs == null ? 0 : postIs.getAmount();
					int dif = preAmount - postAmount;
					
					int amount = perks.getSupply() >= dif ? preAmount : preAmount + perks.getSupply() - dif;
					
					ItemStack lapis = new ItemStack(Material.INK_SACK, amount);
					lapis.setDurability((short) 4);
					ev.getInventory().setItem(1, lapis);
				}
			}.runTaskLaterAsynchronously(Main.plugin, 1);
			
		}
		else {
			p.sendMessage(ChatColor.RED + "You need to be level " + level + " to do this!");
			ev.setCancelled(true);
		}
	}
}
