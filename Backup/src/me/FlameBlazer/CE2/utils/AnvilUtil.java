package me.FlameBlazer.CE2.utils;

import java.lang.reflect.Field;

import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.ContainerAnvil;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.World;

public class AnvilUtil
{
	private static Field position;
	private static Field world;
	
	static
	{
		try
		{
			position = ContainerAnvil.class.getDeclaredField("j");
			position.setAccessible(true);
			world = ContainerAnvil.class.getDeclaredField("i");
			world.setAccessible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static ContainerAnvil getFromCurrent(Player player)
	{
		EntityPlayer nmsPlayer = ((CraftPlayer)player).getHandle();
		return ((ContainerAnvil)nmsPlayer.activeContainer);
	}
	
	public static void setCost(ContainerAnvil anvil, int cost)
    {
		anvil.a = cost;
    }
	
	public static void update(ContainerAnvil anvil, Player player)
    {
		((CraftPlayer)player).getHandle().setContainerData(anvil, 0, anvil.a);
    }
	
	public static Block getPosition(ContainerAnvil anvil)
	{
		try {
			BlockPosition pos = (BlockPosition) position.get(anvil);
			World wld = (World) world.get(anvil);
			return wld.getWorld().getBlockAt(pos.getX(), pos.getY(), pos.getZ());
		} catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
}
