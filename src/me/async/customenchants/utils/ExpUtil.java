package me.async.customenchants.utils;

import java.math.BigDecimal;

import org.bukkit.entity.Player;

public class ExpUtil {
	public static int getTotalExperience(Player p) {
		int experience = 0;
	    int level = p.getLevel();
	    if(level >= 0 && level <= 15) {
	    	experience = (int) Math.ceil(Math.pow(level, 2) + (6 * level));
	        int requiredExperience = 2 * level + 7;
	        double currentExp = Double.parseDouble(Float.toString(p.getExp()));
	        experience += Math.ceil(currentExp * requiredExperience);
	        return experience;
	    } else if(level > 15 && level <= 30) {
	        experience = (int) Math.ceil((2.5 * Math.pow(level, 2) - (40.5 * level) + 360));
	        int requiredExperience = 5 * level - 38;
	        double currentExp = Double.parseDouble(Float.toString(p.getExp()));
	        experience += Math.ceil(currentExp * requiredExperience);
	        return experience;
	    } else {
	        experience = (int) Math.ceil(((4.5 * Math.pow(level, 2) - (162.5 * level) + 2220)));
	        int requiredExperience = 9 * level - 158;
	        double currentExp = Double.parseDouble(Float.toString(p.getExp()));
	        experience += Math.ceil(currentExp * requiredExperience);
	        return experience;      
	    }
	}
	   
	public static void setTotalExperience(Player p, int xp) {
		//Levels 0 through 15
	    if(xp >= 0 && xp < 351) {
	    	//Calculate Everything
	        int a = 1; int b = 6; int c = -xp;
	        int level = (int) (-b + Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);
	        int xpForLevel = (int) (Math.pow(level, 2) + (6 * level));
	        int remainder = xp - xpForLevel;
	        int experienceNeeded = (2 * level) + 7;
	        float experience = (float) remainder / (float) experienceNeeded;
	        experience = round(experience, 2);
	        p.setLevel(level);
	        p.setExp(experience);
	        //Levels 16 through 30
	    } else if(xp >= 352 && xp < 1507) {
	        //Calculate Everything
	        double a = 2.5; double b = -40.5; int c = -xp + 360;
	        double dLevel = (-b + Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);
	        int level = (int) Math.floor(dLevel);
	        int xpForLevel = (int) (2.5 * Math.pow(level, 2) - (40.5 * level) + 360);
	        int remainder = xp - xpForLevel;
 	        int experienceNeeded = (5 * level) - 38;
 	        float experience = (float) remainder / (float) experienceNeeded;
	        experience = round(experience, 2);
	        //Set Everything
	        p.setLevel(level);
	        p.setExp(experience);    
	        //Level 31 and greater
	    } else {
	       //Calculate Everything
	        double a = 4.5; double b = -162.5; int c = -xp + 2220;
	        double dLevel = (-b + Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);
	        int level = (int) Math.floor(dLevel);
	        int xpForLevel = (int) (4.5 * Math.pow(level, 2) - (162.5 * level) + 2220);
	        int remainder = xp - xpForLevel;
	        int experienceNeeded = (9 * level) - 158;
	        float experience = (float) remainder / (float) experienceNeeded;
	        experience = round(experience, 2);
	        //Set Everything
	        p.setLevel(level);
	        p.setExp(experience);      
	    } 
	}
	
	public static int getExperienceToLevel(int level)
	{
	    if(level >= 0 && level <= 15)
	        return (int) Math.ceil(Math.pow(level, 2) + (6 * level));
	    else if(level > 15 && level <= 30) 
	        return (int) Math.ceil((2.5 * Math.pow(level, 2) - (40.5 * level) + 360));
	    else
	        return (int) Math.ceil(((4.5 * Math.pow(level, 2) - (162.5 * level) + 2220)));
	}
	   
	private static float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_DOWN);
		return bd.floatValue();
	}
}