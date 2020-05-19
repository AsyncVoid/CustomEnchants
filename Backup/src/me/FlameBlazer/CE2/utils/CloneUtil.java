package me.FlameBlazer.CE2.utils;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.v1_8_R3.DataWatcher;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.WorldServer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;

public class CloneUtil
{
	private static Field f_id;
	private static Field f_id2;
	private static Field f_uuid;
	private static Field f_x;
	private static Field f_y;
	private static Field f_z;
	private static Field f_yaw;
	private static Field f_pitch;
	private static Field f_hand;
	private static Field f_watcher;
	
	static
	{
		try {
			f_id = PacketPlayOutNamedEntitySpawn.class.getDeclaredField("a");
			f_id2 = PacketPlayOutEntityHeadRotation.class.getDeclaredField("a");
			f_uuid = PacketPlayOutNamedEntitySpawn.class.getDeclaredField("b");
			f_x = PacketPlayOutNamedEntitySpawn.class.getDeclaredField("c");
			f_y = PacketPlayOutNamedEntitySpawn.class.getDeclaredField("d");
			f_z = PacketPlayOutNamedEntitySpawn.class.getDeclaredField("e");
			f_yaw = PacketPlayOutNamedEntitySpawn.class.getDeclaredField("f");
			f_pitch = PacketPlayOutNamedEntitySpawn.class.getDeclaredField("g");
			f_hand = PacketPlayOutNamedEntitySpawn.class.getDeclaredField("h");
			f_watcher = PacketPlayOutNamedEntitySpawn.class.getDeclaredField("i");
			
			f_id.setAccessible(true);
			f_id2.setAccessible(true);
			f_uuid.setAccessible(true);
			f_x.setAccessible(true);
			f_y.setAccessible(true);
			f_z.setAccessible(true);
			f_yaw.setAccessible(true);
			f_pitch.setAccessible(true);
			f_hand.setAccessible(true);
			f_watcher.setAccessible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static int CloneHuman(Player clone, Player target)
	{
		EntityPlayer craftClone = ((CraftPlayer) clone).getHandle();
		
		int id = new Random().nextInt(4000) + 1000;
		Location l = clone.getLocation();
		String npcName = clone.getDisplayName();
	    UUID uuid = clone.getUniqueId();
		
	    MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
	    WorldServer nmsWorld = ((CraftWorld) target.getWorld()).getHandle();
	    GameProfile gameProfile = new GameProfile(uuid, npcName);
	    PlayerInteractManager playerInteractManager = new PlayerInteractManager(nmsWorld);
	    
	    EntityPlayer ep = new EntityPlayer(nmsServer, nmsWorld, gameProfile, playerInteractManager);
	    
	    PacketPlayOutPlayerInfo addplayer = new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, ep);
	    	
	    DataWatcher d = new DataWatcher(null);
	    d.a(0, (Object) (byte) 0);
	    d.a(1, (Object) (short) 0);
	    d.a(8, (Object) (byte) 0);
	        
	    PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn();
	    setField(spawn, f_id, id);
	    setField(spawn, f_uuid, uuid);
	    setField(spawn, f_x, (int) (l.getX() * 32));
	    setField(spawn, f_y, (int) (l.getY() * 32));
	    setField(spawn, f_z, (int) (l.getZ() * 32));
	    setField(spawn, f_yaw, getCompressedAngle(l.getYaw()));
	    setField(spawn, f_pitch, getCompressedAngle(l.getPitch()));
	    setField(spawn, f_watcher, d);
	    
	    PacketPlayOutEntityHeadRotation rotation = new PacketPlayOutEntityHeadRotation(ep, getCompressedAngle(clone.getEyeLocation().getYaw()));
	    setField(rotation, f_id2, id);
	    
	    PacketPlayOutEntityEquipment armor1 = new PacketPlayOutEntityEquipment(id, 1, craftClone.inventory.getArmorContents()[0]);
	    PacketPlayOutEntityEquipment armor2 = new PacketPlayOutEntityEquipment(id, 2, craftClone.inventory.getArmorContents()[1]);
	    PacketPlayOutEntityEquipment armor3 = new PacketPlayOutEntityEquipment(id, 3, craftClone.inventory.getArmorContents()[2]);
	    PacketPlayOutEntityEquipment armor4 = new PacketPlayOutEntityEquipment(id, 4, craftClone.inventory.getArmorContents()[3]);
	    PacketPlayOutEntityEquipment sword = new PacketPlayOutEntityEquipment(id, 0, craftClone.inventory.getItem(0));
	        
	    ((CraftPlayer) target).getHandle().playerConnection.sendPacket(addplayer);
	    ((CraftPlayer) target).getHandle().playerConnection.sendPacket(spawn);
	    ((CraftPlayer) target).getHandle().playerConnection.sendPacket(rotation);
	    ((CraftPlayer) target).getHandle().playerConnection.sendPacket(armor1);
	    ((CraftPlayer) target).getHandle().playerConnection.sendPacket(armor2);
	    ((CraftPlayer) target).getHandle().playerConnection.sendPacket(armor3);
	    ((CraftPlayer) target).getHandle().playerConnection.sendPacket(armor4);
	    ((CraftPlayer) target).getHandle().playerConnection.sendPacket(sword);
	      
	    return id;
	}
	
	private static void setField(Object o, Field f, Object v)
	{
		try {
			f.set(o, v);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	private static byte getCompressedAngle(float value)
	{
		return (byte) ((value * 256.0F) / 360.0F);
	}
}
