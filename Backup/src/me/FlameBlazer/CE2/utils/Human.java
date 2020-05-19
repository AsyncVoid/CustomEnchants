package me.FlameBlazer.CE2.utils;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.HttpAuthenticationService;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;
import com.mojang.authlib.yggdrasil.response.MinecraftProfilePropertiesResponse;
import com.mojang.util.UUIDTypeAdapter;

import net.minecraft.server.v1_8_R3.DataWatcher;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.WorldServer;
 
public class Human {
    String name;
    World world;
    public int id;
    Location l;
    int itemInHand;
 
    private List<Integer> ids = new ArrayList<Integer>();
 
    private static void setPrivateField(@SuppressWarnings("rawtypes") Class type, Object object, String name, Object value) {
        try {
            Field f = type.getDeclaredField(name);
            f.setAccessible(true);
            f.set(object, value);
            f.setAccessible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Human(World w, String name, int id, Location l, int itemInHand) {
        this.name = name;
        this.world = w;
        this.id = id;
        this.l = l;
        this.itemInHand = itemInHand;
        DataWatcher d = new DataWatcher(null);
        d.a(0, (Object) (byte) 0);
        d.a(1, (Object) (short) 0);
        d.a(8, (Object) (byte) 0);
        PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn();
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "a", id);
        //TODO
        String npcName ="Notch";
        GameProfile gameProfile = new GameProfile(UUID.fromString("069a79f4-44e9-4726-a5be-fca90e38aaf5"), npcName);
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "b", gameProfile);
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "c", ((int) l.getX() * 32));
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "d", ((int) l.getY() * 32));
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "e", ((int) l.getZ() * 32));
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "f", getCompressedAngle(l.getYaw()));
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "g", getCompressedAngle(l.getPitch()));
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "h", itemInHand);
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "i", d);
 
        PacketPlayOutEntityTeleport tp = new PacketPlayOutEntityTeleport();
        setPrivateField(PacketPlayOutEntityTeleport.class, tp, "a", id);
        setPrivateField(PacketPlayOutEntityTeleport.class, tp, "b", ((int) l.getX() * 32));
        setPrivateField(PacketPlayOutEntityTeleport.class, tp, "c", ((int) l.getY() * 32));
        setPrivateField(PacketPlayOutEntityTeleport.class, tp, "d", ((int) l.getZ() * 32));
        setPrivateField(PacketPlayOutEntityTeleport.class, tp, "e", getCompressedAngle(l.getYaw()));
        setPrivateField(PacketPlayOutEntityTeleport.class, tp, "f", getCompressedAngle(l.getPitch()));
 
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(spawn);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(tp);
        }
        ids.add(id);
    }
 
    public Human(EntityHuman h) {
        PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn(h);
        int id = new Random().nextInt(4000) + 1000;
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "a", id);
        this.id = id;
        PacketPlayOutEntityEquipment armor1 = new PacketPlayOutEntityEquipment(id, 1, h.inventory.getArmorContents()[0]);
        PacketPlayOutEntityEquipment armor2 = new PacketPlayOutEntityEquipment(id, 2, h.inventory.getArmorContents()[1]);
        PacketPlayOutEntityEquipment armor3 = new PacketPlayOutEntityEquipment(id, 3, h.inventory.getArmorContents()[2]);
        PacketPlayOutEntityEquipment armor4 = new PacketPlayOutEntityEquipment(id, 4, h.inventory.getArmorContents()[3]);
        PacketPlayOutEntityEquipment sword = new PacketPlayOutEntityEquipment(id, 0, h.inventory.getItem(0));
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(spawn);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(armor1);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(armor2);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(armor3);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(armor4);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(sword);
        }
    }
 
    public void remove() {
        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(id);
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
        }
    }
    
    public void teleport(Location loc) {
        PacketPlayOutEntityTeleport tp = new PacketPlayOutEntityTeleport();
        setPrivateField(PacketPlayOutEntityTeleport.class, tp, "a", id);
        setPrivateField(PacketPlayOutEntityTeleport.class, tp, "b", ((int) (loc.getX() * 32)));
        setPrivateField(PacketPlayOutEntityTeleport.class, tp, "c", ((int) (loc.getY() * 32)));
        setPrivateField(PacketPlayOutEntityTeleport.class, tp, "d", ((int) (loc.getZ() * 32)));
        setPrivateField(PacketPlayOutEntityTeleport.class, tp, "e", getCompressedAngle(loc.getYaw()));
        setPrivateField(PacketPlayOutEntityTeleport.class, tp, "f", getCompressedAngle(loc.getPitch()));
        this.l = loc;
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(tp);
        }
    }
 
    private static byte getCompressedAngle(float value) {
        return (byte) ((value * 256.0F) / 360.0F);
    }
 
    
 
    public void updateItems(ItemStack hand, ItemStack boots, ItemStack legs, ItemStack chest, ItemStack helmet) {
 
        PacketPlayOutEntityEquipment[] ps = new PacketPlayOutEntityEquipment[]{
            new PacketPlayOutEntityEquipment(id, 1, CraftItemStack.asNMSCopy(boots)),
            new PacketPlayOutEntityEquipment(id, 2, CraftItemStack.asNMSCopy(legs)),
            new PacketPlayOutEntityEquipment(id, 3, CraftItemStack.asNMSCopy(chest)),
            new PacketPlayOutEntityEquipment(id, 4, CraftItemStack.asNMSCopy(helmet)),
            new PacketPlayOutEntityEquipment(id, 0, CraftItemStack.asNMSCopy(hand))
        };
        for (PacketPlayOutEntityEquipment pack : ps) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(pack);
            }
        }
    }
 
    @Deprecated
    public void setName(String s) {
        DataWatcher d = new DataWatcher(null);
        d.a(0, (Object) (byte) 0);
        d.a(1, (Object) (short) 0);
        d.a(8, (Object) (byte) 0);
        d.a(10, (Object) (String) s);
        //d.a(11, (Object) (byte) 0);
        PacketPlayOutEntityMetadata packet40 = new PacketPlayOutEntityMetadata(id, d, true);
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet40);
        }
    }
 
    public void hideForPlayer(Player p) {
        DataWatcher d = new DataWatcher(null);
        d.a(0, (Object) (byte) 32);
        d.a(1, (Object) (short) 0);
        d.a(8, (Object) (byte) 0);
        PacketPlayOutEntityMetadata packet40 = new PacketPlayOutEntityMetadata(id, d, true);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet40);
    }
 
    public void showForPlayer(Player p) {
        DataWatcher d = new DataWatcher(null);
        d.a(0, (Object) (byte) 0);
        d.a(1, (Object) (short) 0);
        d.a(8, (Object) (byte) 0);
        PacketPlayOutEntityMetadata packet40 = new PacketPlayOutEntityMetadata(id, d, true);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet40);
    }
 
    public void addPotionColor(Color r) {
        int color = r.asBGR();
        final DataWatcher dw = new DataWatcher(null);
        dw.a(7, Integer.valueOf(color));
        PacketPlayOutEntityMetadata packet40 = new PacketPlayOutEntityMetadata(id, dw, true);
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet40);
        }
    }
 
    public void addPotionColor(int color) {
        final DataWatcher dw = new DataWatcher(null);
        dw.a(7, Integer.valueOf(color));
        PacketPlayOutEntityMetadata packet40 = new PacketPlayOutEntityMetadata(id, dw, true);
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet40);
        }
    }
 
    public void sendtoplayer(Player who) {
        DataWatcher d = new DataWatcher(null);
        d.a(0, (Object) (byte) 0);
        d.a(1, (Object) (short) 0);
        d.a(8, (Object) (byte) 0);
        PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn();
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "a", id);
        //setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "b", new GameProfile("", name));
        //TODO
        String npcName ="Notch";
        GameProfile gameProfile = new GameProfile(UUID.fromString("069a79f4-44e9-4726-a5be-fca90e38aaf5"), npcName);
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "b", gameProfile);
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "c", ((int) (l.getX() * 32)));
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "d", ((int) (l.getY() * 32)));
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "e", ((int) (l.getZ() * 32)));
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "f", getCompressedAngle(l.getYaw()));
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "g", getCompressedAngle(l.getPitch()));
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "h", itemInHand);
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "i", d);
 
        PacketPlayOutEntityTeleport tp = new PacketPlayOutEntityTeleport();
        setPrivateField(PacketPlayOutEntityTeleport.class, tp, "a", id);
        setPrivateField(PacketPlayOutEntityTeleport.class, tp, "b", ((int) (l.getX() * 32)));
        setPrivateField(PacketPlayOutEntityTeleport.class, tp, "c", ((int) (l.getY() * 32)));
        setPrivateField(PacketPlayOutEntityTeleport.class, tp, "d", ((int) (l.getZ() * 32)));
        setPrivateField(PacketPlayOutEntityTeleport.class, tp, "e", getCompressedAngle(l.getYaw()));
        setPrivateField(PacketPlayOutEntityTeleport.class, tp, "f", getCompressedAngle(l.getPitch()));
 
        ((CraftPlayer) who).getHandle().playerConnection.sendPacket(spawn);
        ((CraftPlayer) who).getHandle().playerConnection.sendPacket(tp);
    }
 
    public static int CloneHuman(Player clone, Player target)
    {
    	int id = new Random().nextInt(4000) + 1000;
    	Location l = clone.getLocation();
    	
    	MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
    	WorldServer nmsWorld = ((CraftWorld) target.getWorld()).getHandle();
    	String npcName = clone.getDisplayName();
    	UUID uuid = clone.getUniqueId();
        GameProfile gameProfile = new GameProfile(uuid, npcName);
        PlayerInteractManager playerInteractManager = new PlayerInteractManager(nmsWorld);
    	EntityPlayer ep = new EntityPlayer(nmsServer, nmsWorld, gameProfile, playerInteractManager);
        
    	PacketPlayOutPlayerInfo addplayer = new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, ep);
    	
    	DataWatcher d = new DataWatcher(null);
        d.a(0, (Object) (byte) 0);
        d.a(1, (Object) (short) 0);
        d.a(8, (Object) (byte) 0);
        
        PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn();
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "a", id);
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "b", uuid);
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "c", ((int) (l.getX() * 32)));
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "d", ((int) (l.getY() * 32)));
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "e", ((int) (l.getZ() * 32)));
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "f", getCompressedAngle(l.getYaw()));
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "g", getCompressedAngle(l.getPitch()));
        //setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "h", itemInHand);
        setPrivateField(PacketPlayOutNamedEntitySpawn.class, spawn, "i", d);
        
        PacketPlayOutEntityHeadRotation rotation = new PacketPlayOutEntityHeadRotation(ep, getCompressedAngle(clone.getEyeLocation().getYaw()));
        
        EntityPlayer h = ((CraftPlayer) clone).getHandle();
        PacketPlayOutEntityEquipment armor1 = new PacketPlayOutEntityEquipment(id, 1, h.inventory.getArmorContents()[0]);
        PacketPlayOutEntityEquipment armor2 = new PacketPlayOutEntityEquipment(id, 2, h.inventory.getArmorContents()[1]);
        PacketPlayOutEntityEquipment armor3 = new PacketPlayOutEntityEquipment(id, 3, h.inventory.getArmorContents()[2]);
        PacketPlayOutEntityEquipment armor4 = new PacketPlayOutEntityEquipment(id, 4, h.inventory.getArmorContents()[3]);
        PacketPlayOutEntityEquipment sword = new PacketPlayOutEntityEquipment(id, 0, h.inventory.getItem(0));
        
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
    
    public static GameProfile fillProfileProperties(GameProfile profile,
			boolean requireSecure) throws Exception {
			
			//if (!Bukkit.isPrimaryThread())
			//throw new IllegalStateException("NMS.fillProfileProperties cannot be invoked from the main thread.");
			
			MinecraftSessionService sessionService = ((CraftServer) Bukkit.getServer()).getServer().aD();
			
			YggdrasilAuthenticationService auth = ((YggdrasilMinecraftSessionService) sessionService)
			.getAuthenticationService();
			
			URL url = HttpAuthenticationService.constantURL(
			"https://sessionserver.mojang.com/session/minecraft/profile/" +
			UUIDTypeAdapter.fromUUID(profile.getId()));
			
			url = HttpAuthenticationService.concatenateURL(url, "unsigned=" + !requireSecure);
			java.lang.reflect.Method MAKE_REQUEST = null;
			try {
			    MAKE_REQUEST = YggdrasilAuthenticationService.class.getDeclaredMethod("makeRequest", URL.class,
			            Object.class, Class.class);
			    MAKE_REQUEST.setAccessible(true);
			} catch (Exception ex) {
			    ex.printStackTrace();
			}
			MinecraftProfilePropertiesResponse response = (MinecraftProfilePropertiesResponse)
			MAKE_REQUEST.invoke(auth, url, null, MinecraftProfilePropertiesResponse.class);
			if (response == null)
			return profile;
			
			GameProfile result = new GameProfile(profile.getId(), profile.getName());
			result.getProperties().putAll(response.getProperties());
			profile.getProperties().putAll(response.getProperties());
			
			return result;
    }
    
    public void setInvisible() {
        DataWatcher d = new DataWatcher(null);
        d.a(0, (Object) (byte) 32);
        d.a(1, (Object) (short) 0);
        d.a(8, (Object) (byte) 0);
        PacketPlayOutEntityMetadata packet40 = new PacketPlayOutEntityMetadata(id, d, true);
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet40);
        }
    }
 
    public void setCrouched() {
        DataWatcher d = new DataWatcher(null);
        d.a(0, (Object) (byte) 2);
        d.a(1, (Object) (short) 0);
        d.a(8, (Object) (byte) 0);
        PacketPlayOutEntityMetadata packet40 = new PacketPlayOutEntityMetadata(id, d, true);
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet40);
        }
    }
 
    public void reset() {
        DataWatcher d = new DataWatcher(null);
        d.a(0, (Object) (byte) 0);
        d.a(1, (Object) (short) 0);
        d.a(8, (Object) (byte) 0);
        PacketPlayOutEntityMetadata packet40 = new PacketPlayOutEntityMetadata(id, d, true);
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet40);
        }
    }
 
    public void sprint() {
        DataWatcher d = new DataWatcher(null);
        d.a(0, (Object) (byte) 8);
        d.a(1, (Object) (short) 0);
        d.a(8, (Object) (byte) 0);
        PacketPlayOutEntityMetadata packet40 = new PacketPlayOutEntityMetadata(id, d, true);
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet40);
        }
    }
 
    @Deprecated
    public void block() {
        DataWatcher d = new DataWatcher(null);
        d.a(0, (Object) (byte) 16);
        d.a(1, (Object) (short) 0);
        d.a(6, (Object) (byte) 0);
        PacketPlayOutEntityMetadata packet40 = new PacketPlayOutEntityMetadata(id, d, true);
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet40);
        }
    }
 
    public void damage() {
        PacketPlayOutAnimation packet18 = new PacketPlayOutAnimation();
        setPrivateField(PacketPlayOutAnimation.class, packet18, "a", id);
        setPrivateField(PacketPlayOutAnimation.class, packet18, "b", 2);
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet18);
        }
    }
 
    public void swingArm() {
        PacketPlayOutAnimation packet18 = new PacketPlayOutAnimation();
        setPrivateField(PacketPlayOutAnimation.class, packet18, "a", id);
        setPrivateField(PacketPlayOutAnimation.class, packet18, "b", 0);
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet18);
        }
    }
 
    public double getX() {
        return l.getX();
    }
 
    public double getY() {
        return l.getY();
    }
 
    public double getZ() {
        return l.getZ();
    }
 
    public Location getLocation() {
        return l;
    }
 
}