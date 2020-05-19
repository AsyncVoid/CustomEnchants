package me.FlameBlazer.CE2.enchantment;

public enum EnchantRarity
{
    COMMON(10),
    UNUSUAL(5),
    RARE(2),
    VERY_RARE(1),
	LEGENDARY(0);
	/*
	 * Noteworthy
	 * Extraordinary
	 * Remarkable
	 * Unique
	 * Abnormal
	 * Bizare
	 * Mythical
	 * Exceptional
	 * Odd
	 * Peculiar
	 * Uncommon
	 * Strange
	 * Anomalous
	 * Arcane
	 * Mysterious
	 * Exotic
	 * Irregular
	 * Uncanny
	 */
    private final int weight;

    private EnchantRarity(int rarityWeight)
    {
        this.weight = rarityWeight;
    }

    public int getWeight()
    {
        return this.weight;
    }
}