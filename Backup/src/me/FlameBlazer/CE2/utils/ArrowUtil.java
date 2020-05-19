package me.FlameBlazer.CE2.utils;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArrow;

import net.minecraft.server.v1_8_R3.EntityArrow;

public class ArrowUtil {
	
	private static Field fieldX;
    private static Field fieldY;
    private static Field fieldZ;
    
    static {
    	try {
    		fieldX = EntityArrow.class.getDeclaredField("d");
    		fieldY = EntityArrow.class.getDeclaredField("e");
    		fieldZ = EntityArrow.class.getDeclaredField("f");
    	} catch (Exception ex) 
    	{
    		ex.printStackTrace();
    	}
    	fieldX.setAccessible(true);
        fieldY.setAccessible(true);
        fieldZ.setAccessible(true);
    }
    
    public static int[] getPos(CraftArrow a)
    {
    	try {
    		EntityArrow entityArrow = a.getHandle();
			return new int[] { fieldX.getInt(entityArrow), fieldY.getInt(entityArrow), fieldZ.getInt(entityArrow) };
		} catch (Exception ex) {
			ex.printStackTrace();
			return new int[] { -1, -1, -1 };
		}
    }
}
