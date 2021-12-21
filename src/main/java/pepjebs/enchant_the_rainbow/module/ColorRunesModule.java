package pepjebs.enchant_the_rainbow.module;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemStack;
import pepjebs.enchant_the_rainbow.EnchantTheRainbowMod;
import pepjebs.enchant_the_rainbow.mixin.GlintRenderLayer;


/**
 * @author WireSegal
 * Hacked by svenhjol
 * Ported to Fabric 1.18 by Pepperoni-Jabroni
 * Created at 1:52 PM on 8/17/19.
 */
// This class lovingly yoinked (& updated to 1.18) from
// https://github.com/VazkiiMods/Quark/blob/master/src/main/java/vazkii/quark/content/tools/module/ColorRunesModule.java
// Published under the "CC BY-NC-SA 3.0" Creative Commons License
public class ColorRunesModule {

    public static final int RUNE_TYPES = 16;

    private static final ThreadLocal<ItemStack> targetStack = new ThreadLocal<>();

    public static void setTargetStack(ItemStack stack) {
        targetStack.set(stack);
    }

    public static int changeColor() {
        ItemStack target = targetStack.get();

        if (target == null ||
                target.isEmpty() ||
                target.getNbt() == null ||
                !target.getNbt().contains(EnchantTheRainbowMod.GLINT_COLOR_NBT_TAG))
            return -1;

        return target.getNbt().getInt(EnchantTheRainbowMod.GLINT_COLOR_NBT_TAG);
    }

    @Environment(EnvType.CLIENT)
    public static RenderLayer getGlint() {
        int color = changeColor();
        return color >= 0 && color <= RUNE_TYPES ? GlintRenderLayer.glintColor.get(color) : RenderLayer.getGlint();
    }

    @Environment(EnvType.CLIENT)
    public static RenderLayer getEntityGlint() {
        int color = changeColor();
        return color >= 0 && color <= RUNE_TYPES ? GlintRenderLayer.entityGlintColor.get(color) : RenderLayer.getEntityGlint();
    }

    @Environment(EnvType.CLIENT)
    public static RenderLayer getGlintDirect() {
        int color = changeColor();
        return color >= 0 && color <= RUNE_TYPES ? GlintRenderLayer.glintDirectColor.get(color) : RenderLayer.getDirectGlint();
    }

    @Environment(EnvType.CLIENT)
    public static RenderLayer getEntityGlintDirect() {
        int color = changeColor();
        return color >= 0 && color <= RUNE_TYPES ? GlintRenderLayer.entityGlintDirectColor.get(color) : RenderLayer.getDirectEntityGlint();
    }

    @Environment(EnvType.CLIENT)
    public static RenderLayer getArmorGlint() {
        int color = changeColor();
        return color >= 0 && color <= RUNE_TYPES ? GlintRenderLayer.armorGlintColor.get(color) : RenderLayer.getArmorGlint();
    }

    @Environment(EnvType.CLIENT)
    public static RenderLayer getArmorEntityGlint() {
        int color = changeColor();
        return color >= 0 && color <= RUNE_TYPES ? GlintRenderLayer.armorEntityGlintColor.get(color) : RenderLayer.getArmorEntityGlint();
    }

    private static boolean canHaveRune(ItemStack stack) {
        return stack.hasEnchantments();
    }
}