package me.FlameBlazer.CE2.utils;

import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;

import me.Async.CustomEnchants.Main;

public final class Metadata
{
	public static void setMetadata(Metadatable meta, String key, Object value)
	{
		meta.setMetadata(key, new FixedMetadataValue(Main.plugin, value));
	}
	
	public static MetadataValue getMetadata(Metadatable meta, String key)
	{
		for(MetadataValue value : meta.getMetadata(key))
		{
			if(value.getOwningPlugin() == Main.plugin)
				return value;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Object> T getMetadata(Metadatable meta, String key, T example)
	{
		for(MetadataValue value : meta.getMetadata(key))
		{
			if(value.getOwningPlugin() == Main.plugin)
				return (T)value.value();
		}
		return null;
	}
}
