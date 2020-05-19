package me.async.customenchants.utils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.async.customenchants.enchantment.Enchantment;
import me.async.customenchants.enchantment.LeveledEnchant;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.ContainerEnchantTable;
import net.minecraft.server.v1_8_R3.EnchantmentManager;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.IInventory;
import net.minecraft.server.v1_8_R3.WeightedRandomEnchant;
import net.minecraft.server.v1_8_R3.World;

public class EnchantUtil
{
	private static final Random rand = new Random();
	private static Field position;
	private static Field world;
	
	static
	{
		try
		{
			position = ContainerEnchantTable.class.getDeclaredField("position");
			position.setAccessible(true);
			world = ContainerEnchantTable.class.getDeclaredField("world");
			world.setAccessible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static void CustomEnchant(ItemStack is, int level)
	{
		List<Enchantment> enchants = Enchantment.getApplicableEnchants(is.getType());
		
		for(int i = 0; i < level; i+=rand.nextInt(10)+10) 
		{
			LeveledEnchant enc = Enchantment.getRandomEnchant(enchants, level);
			if(enc.getLevel() > 0)
				enc.applyToItem(is);
		}
	}
	
	public static void RegularEnchant(ItemStack is, int level)
	{
		//Iterator<org.bukkit.enchantments.Enchantment> it = is.getEnchantments().keySet().iterator();
		//while(it.hasNext())
		//{
		//	is.removeEnchantment(it.next());
		//}
		net.minecraft.server.v1_8_R3.ItemStack nms = CraftItemStack.asNMSCopy(is);
		EnchantmentManager.a(rand, nms, level);
		ItemStack buk = CraftItemStack.asBukkitCopy(nms);
		is.addEnchantments(buk.getEnchantments());
		/*net.minecraft.server.v1_8_R3.ItemStack nms = CraftItemStack.asNMSCopy(is);
		EnchantmentManager.a(rand, nms, level);    //List b
		ItemStack buk = CraftItemStack.asBukkitCopy(nms);
		for(Entry<org.bukkit.enchantments.Enchantment, Integer> enc : buk.getEnchantments().entrySet())
		{
			if(is.containsEnchantment(enc.getKey()))
			{
				int enclvl = is.getEnchantmentLevel(enc.getKey());
				if(enc.getValue() > enclvl)
					is.removeEnchantment(enc.getKey());
			}
			is.addUnsafeEnchantment(enc.getKey(), enc.getValue());
		}*/
	}
	
	public static void RegularEnchant(ContainerEnchantTable enct, ItemStack is, int level)
	{
		net.minecraft.server.v1_8_R3.ItemStack nms = CraftItemStack.asNMSCopy(is);
		rand.setSeed(enct.f);
		EnchantmentManager.a(rand, nms, level);
		ItemStack buk = CraftItemStack.asBukkitCopy(nms);
		is.addEnchantments(buk.getEnchantments());
	}
	
	public static Map<org.bukkit.enchantments.Enchantment, Integer> RegularEnchants(ItemStack is, int level)
	{
		net.minecraft.server.v1_8_R3.ItemStack nms = CraftItemStack.asNMSCopy(is);
		EnchantmentManager.a(rand, nms, level);
		ItemStack buk = CraftItemStack.asBukkitCopy(nms);
		return buk.getEnchantments();
	}
	
	public static ContainerEnchantTable getFromCurrent(Player player)
	{
		EntityPlayer nmsPlayer = ((CraftPlayer)player).getHandle();
		return ((ContainerEnchantTable)nmsPlayer.activeContainer);
	}
	
	public static void setCost(ContainerEnchantTable enct, int[] costs)
    {
		enct.costs[0] = costs[0];
		enct.costs[1] = costs[1];
		enct.costs[2] = costs[2];
		
    }
	
	public static void setHints(ContainerEnchantTable enct, int[] hints)
    {
		enct.h[0] = hints[0];
		enct.h[1] = hints[1];
		enct.h[2] = hints[2];
    }
	
	public static void updateHints(ContainerEnchantTable enct, ItemStack is)
    {
		net.minecraft.server.v1_8_R3.ItemStack itemstack = CraftItemStack.asNMSCopy(is);
		for (int j = 0; j < 3; j++) {
            if (enct.costs[j] > 0)
            {
             // List<WeightedRandomEnchant> list = enct.a(itemstack, j, enct.costs[j]);
            	rand.setSeed(enct.f + j);
            	List<WeightedRandomEnchant> list = EnchantmentManager.b(rand, itemstack, enct.costs[j]);
            	if ((list != null) && (!list.isEmpty()))
            	{
            		WeightedRandomEnchant weightedrandomenchant = (WeightedRandomEnchant)list.get(rand.nextInt(list.size()));
            		enct.h[j] = (weightedrandomenchant.enchantment.id | weightedrandomenchant.level << 8);
            	}
           }
        }		
    }
	
	public static void refresh(ContainerEnchantTable enct, Player player)
    {
		IInventory i = ((CraftInventory) player.getOpenInventory().getTopInventory()).getInventory();
		enct.a(i);
    }
	
	public static void update(ContainerEnchantTable enct, Player player)
    {
		((CraftPlayer)player).getHandle().setContainerData(enct, 0, 0);
    }
	
	public static Block getPosition(ContainerEnchantTable enct)
	{
		try {
			BlockPosition pos = (BlockPosition) position.get(enct);
			World wld = (World) world.get(enct);
			return wld.getWorld().getBlockAt(pos.getX(), pos.getY(), pos.getZ());
		} catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
}
