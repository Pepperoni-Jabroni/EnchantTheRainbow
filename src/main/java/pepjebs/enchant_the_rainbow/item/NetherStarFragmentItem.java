package pepjebs.enchant_the_rainbow.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import pepjebs.enchant_the_rainbow.EnchantTheRainbowMod;

public class NetherStarFragmentItem extends Item {
    public NetherStarFragmentItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.getNbt() != null && stack.getNbt().contains(EnchantTheRainbowMod.GLINT_COLOR_NBT_TAG);
    }
}
