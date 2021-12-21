package pepjebs.enchant_the_rainbow.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import pepjebs.enchant_the_rainbow.EnchantTheRainbowMod;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NetherStarFragmentItem extends Item {
    public NetherStarFragmentItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    public static String getTooltipColorForStack(ItemStack stack){
        if (stack.getNbt() == null || !stack.getNbt().contains(EnchantTheRainbowMod.GLINT_COLOR_NBT_TAG))
            return null;
        int color = stack.getNbt().getInt(EnchantTheRainbowMod.GLINT_COLOR_NBT_TAG);
        String dyeColor = DyeColor.byId(color).getName();
        return Arrays.stream(dyeColor
                        .split("_"))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining(" "));
    }
}
