package me.async.customenchants.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.async.customenchants.Main;
import me.async.customenchants.enchantment.LeveledEnchant;
import me.async.customenchants.utils.AnvilUtil;
import me.async.customenchants.utils.ExpUtil;
import net.minecraft.server.v1_8_R3.ContainerAnvil;

public class AnvilUpdateListener implements Listener
{
	private static final String cost = "Cost: %s levels";
	
	@EventHandler
	public void onInventoryClick(final InventoryClickEvent ev)
	{
		if(ev.getInventory().getType() == InventoryType.ANVIL)
		{
			final Player p = (Player)ev.getWhoClicked();
			
			onAnvilResult(ev);
						
			ItemStack result = ev.getInventory().getItem(2);
			if(ev.getSlot() == 2 && result != null)
			{
				int lvlCost = calculateCost(result);
				int xpcost = ExpUtil.getExperienceToLevel(lvlCost);
				int totalxp = ExpUtil.getTotalExperience(p);
				if(totalxp >= xpcost)
				{
					ev.getInventory().setItem(0, null);
					ev.getInventory().setItem(1, null);
					ItemMeta im = result.getItemMeta();
					if(im.hasLore())
					{
						List<String> lore = im.getLore();
						if(lore.get(lore.size() - 1).equals(getCostLore(lvlCost)))
						{
							lore.remove(lore.size() - 1);
							im.setLore(lore);
							result.setItemMeta(im);
						}
						else
						{
							ev.setCancelled(true);
							return;
						}
					}
					p.getInventory().addItem(result);
					ExpUtil.setTotalExperience(p, totalxp - xpcost);
					p.sendMessage(ChatColor.GREEN + "Cost " + xpcost + " xp");
					p.getWorld().playSound(p.getLocation(), Sound.ANVIL_USE, 5, 1);
				}
				else
				{
					int needed = xpcost - totalxp;
					ev.setCancelled(true);
					ev.setResult(Result.DENY);
					p.sendMessage(ChatColor.RED + "You need " + needed + " more xp to do this");
				}
			}
		}
	}
	
	private static void onAnvilResult(final InventoryClickEvent ev)
	{
		new BukkitRunnable()
		{
			@Override
			public void run() {
				
				ItemStack left = ev.getInventory().getItem(0);
				ItemStack right = ev.getInventory().getItem(1);
				
				if(left == null || right == null) return;
				//TODO: Proper repairs
				ItemStack result = new ItemStack(left.getType());
				
				List<LeveledEnchant> encs = me.async.customenchants.enchantment.Enchantment.getEnchants(left);
				encs.addAll(me.async.customenchants.enchantment.Enchantment.getEnchants(right));
				for(LeveledEnchant enc : encs)
					if(enc.getEnchantment().canEnchant(result.getType()))
						enc.applyToItem(result);
				
				Map<Enchantment, Integer> enchants = new HashMap<Enchantment, Integer>();
				enchants.putAll(left.getEnchantments());
				addEnchants(enchants, right.getEnchantments());
				
				if(right.getType() == Material.ENCHANTED_BOOK)
				{
					EnchantmentStorageMeta meta = (EnchantmentStorageMeta)right.getItemMeta();
					addEnchants(enchants, meta.getStoredEnchants());
				}
				
				if(left.getType() == Material.ENCHANTED_BOOK)
				{
					EnchantmentStorageMeta meta = (EnchantmentStorageMeta)left.getItemMeta();
					addEnchants(enchants, meta.getStoredEnchants());
					for(Entry<Enchantment, Integer> enc : enchants.entrySet())
					{
						try {
							meta.addEnchant(enc.getKey(), enc.getValue(), true);
						} catch (Exception ex) {
							//ex.printStackTrace();
						}
					}
					result.setItemMeta(meta);
				}
				else
				{
					for(Entry<Enchantment, Integer> enc : enchants.entrySet())
					{
						try {
							result.addEnchantment(enc.getKey(), enc.getValue());
						} catch (Exception ex) {
							//ex.printStackTrace();
						}
					}
				}
				
				int xp = calculateCost(result);
				
				ItemMeta im = result.getItemMeta();
				List<String> lore = im.hasLore() ? im.getLore() : new ArrayList<String>();
				
				if(lore.isEmpty() || !lore.get(lore.size() - 1).contains("Cost: "))
				{
					lore.add(getCostLore(xp));
					im.setLore(lore);
					result.setItemMeta(im);
				}
						
				Player p = (Player)ev.getWhoClicked();
				ContainerAnvil anv = AnvilUtil.getFromCurrent(p);
				AnvilUtil.setCost(anv, xp);
				AnvilUtil.update(anv, p);
				
				//Bukkit.broadcastMessage(anv.a + " " + anv.getSlot(0).inventory.getName());
				ev.getInventory().setItem(2, result);
				anv.b();
			}
		}.runTaskLaterAsynchronously(Main.plugin, 1);
	}
	
	private static String getCostLore(int lvlCost)
	{
		return ChatColor.RESET + "" + ChatColor.YELLOW + String.format(cost, lvlCost);
	}
	
	private static int calculateCost(ItemStack is)
	{
		int result = 0;
		for(LeveledEnchant enc : me.async.customenchants.enchantment.Enchantment.getEnchants(is))
		{
			result += enc.getLevel();
			result += 1;
		}
		for(Entry<Enchantment, Integer> enc : is.getEnchantments().entrySet())
		{
			result += enc.getValue();
			result += 1;
		}
		return result;
	}
	
	private static void addEnchants(Map<Enchantment, Integer> dest, Map<Enchantment, Integer> source)
	{
		for(Entry<Enchantment, Integer> enc : source.entrySet())
		{
			if(dest.containsKey(enc.getKey()))
			{
				if(dest.get(enc.getKey()) < enc.getValue())
					dest.put(enc.getKey(), enc.getValue());
			}
			else
				dest.put(enc.getKey(), enc.getValue());
		}
	}
}
