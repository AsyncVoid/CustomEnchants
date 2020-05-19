package me.async.customenchants.listeners;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.async.customenchants.Main;
import me.async.customenchants.utils.EnchantUtil;
import net.minecraft.server.v1_8_R3.ContainerEnchantTable;

public class EnchantTableUpdateListener  implements Listener
{
	public static class TablePerks
	{
		private final int tier;
		private final int supply;
		
		private TablePerks(int tier, int supply)
		{
			this.tier = tier;
			this.supply = supply;
		}
		
		public int getTier()
		{
			return tier;
		}
		
		public int getSupply()
		{
			return supply;
		}
	}
	
	public final static Map<Player, TablePerks> perksMap = new HashMap<Player, TablePerks>();
	
	@EventHandler
	public void onInventoryOpen(final InventoryOpenEvent ev)
	{
		if(ev.getInventory().getType() == InventoryType.ENCHANTING)
		{
			new BukkitRunnable()
			{
				@Override
				public void run() {
					Player p = (Player) ev.getPlayer();
					ContainerEnchantTable enct = EnchantUtil.getFromCurrent(p);
					Block b = EnchantUtil.getPosition(enct);
					int tier = getTier(b);
					int supply = getSupply(b);
					TablePerks perks = new TablePerks(tier, supply);
					perksMap.put(p, perks);
					ItemStack is = new ItemStack(Material.INK_SACK, supply);
					is.setDurability((short) 4);
					ev.getInventory().setItem(1, is);
					//Bukkit.broadcastMessage("Tier: " + tier + ". Supply: " + supply + ". WindowID: " + enct.windowId);
				}
			}.runTaskLaterAsynchronously(Main.plugin, 1);
		}
	}
	
	@EventHandler
	public void onInventoryClick(final InventoryClickEvent ev)
	{
		if (ev.getInventory().getType() == InventoryType.ENCHANTING)
		{
			int supply = perksMap.get((Player)ev.getWhoClicked()).supply;
			if(ev.getRawSlot() == 1 && supply > 0 && !(ev.getAction() == InventoryAction.PLACE_ALL || ev.getAction() == InventoryAction.PLACE_ONE))
			{
				ev.setCancelled(true);
				return;
			}
			if(ev.getRawSlot() == 0 || ev.getRawSlot() == 1 || ev.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY)
				onEnchantingResult(ev);
		}
	}
	
	@EventHandler
	public void onInventoryClose(final InventoryCloseEvent ev)
	{
		if(ev.getInventory().getType() == InventoryType.ENCHANTING)
		{
			TablePerks perks = perksMap.get((Player)ev.getPlayer());
			ItemStack is = ev.getInventory().getItem(1);
			if(perks.supply > 0 && is != null)
			{
				is.setAmount(is.getAmount() - perks.supply);
				ev.getInventory().setItem(1, is);
			}
			perksMap.remove(ev.getPlayer());
		}
	}
	
	private static void onEnchantingResult(final InventoryClickEvent ev)
	{
		new BukkitRunnable()
		{
			@Override
			public void run() {
				ItemStack left = ev.getInventory().getItem(0);
				
				if(left == null)
					return;
				
				if(left.getEnchantments().size() > 0 
						|| left.getType() == Material.ENCHANTED_BOOK
						|| left.getMaxStackSize() > 1)     //////////////////////////////////////////////////////////
						//|| ev.getCurrentItem() != null)
						//|| !me.FlameBlazer.CE2.encs.Enchantment.EquiptmentType.ALL.contains(left.getType()))
					return;
				
				Player p = (Player)ev.getWhoClicked();
				ContainerEnchantTable enct = EnchantUtil.getFromCurrent(p);
				
				TablePerks perks = perksMap.get(p);
				
				EnchantUtil.refresh(enct, p);
				EnchantUtil.setCost(enct, new int[]{ 
						enct.costs[0] + perks.tier,
						enct.costs[1] + perks.tier,
						enct.costs[2] + perks.tier });
				
				EnchantUtil.updateHints(enct, left);
				enct.b();
				EnchantUtil.update(enct, p);
			}
		}.runTaskLaterAsynchronously(Main.plugin, 1);
	}
	
	private static int getTier(Block b)
	{
		int tier = 0;
		for(int x = b.getX() - 1; x < b.getX() + 2; x++)
			for(int z = b.getZ() - 1; z < b.getZ() + 2; z++)
				tier += b.getWorld().getBlockAt(x, b.getY() - 1, z).getType() == Material.EMERALD_BLOCK ? 5 : 0;
		return tier;
	}
	
	private static int getSupply(Block b)
	{
		int tier = 0;
		for(int x = b.getX() - 1; x < b.getX() + 2; x++)
			for(int z = b.getZ() - 1; z < b.getZ() + 2; z++)
				tier += b.getWorld().getBlockAt(x, b.getY() - 1, z).getType() == Material.LAPIS_BLOCK ? 1 : 0;
		return tier;
	}
}
