package pepjebs.enchant_the_rainbow.module;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.CompassItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DyeColor;
import pepjebs.enchant_the_rainbow.api.IRuneColorProvider;
import pepjebs.enchant_the_rainbow.client.GlintRenderLayer;


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

    public static final int RUNE_TYPES = 17;

    private static final ThreadLocal<ItemStack> targetStack = new ThreadLocal<>();
    public static ITag<Item> runesTag, runesLootableTag;
    public static Item blank_rune;

    public static int itemQuality = 0;
    public static int applyCost = 5;

    public static void setTargetStack(ItemStack stack) {
        targetStack.set(stack);
    }

    public static int changeColor() {
        ItemStack target = targetStack.get();

        if (target == null)
            return -1;

        LazyOptional<IRuneColorProvider> cap = get(target);


        if (cap.isPresent())
            return cap.orElse((s) -> -1).getRuneColor(target);
        if (!ItemNBTHelper.getBoolean(target, TAG_RUNE_ATTACHED, false))
            return -1;

        ItemStack proxied = ItemStack.read(ItemNBTHelper.getCompound(target, TAG_RUNE_COLOR, false));
        LazyOptional<IRuneColorProvider> proxyCap = get(proxied);
        int color = proxyCap.orElse((s) -> -1).getRuneColor(target);
        return color;
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

    @Override
    public void construct() {
        for(DyeColor color : DyeColor.values())
            new RuneItem(color.getString() + "_rune", this, color.getId(), true);
        new RuneItem("rainbow_rune", this, 16, true);
        blank_rune =  new RuneItem("blank_rune", this, 17, false);
    }

    @Override
    public void setup() {
        runesTag = ItemTags.createOptional(new ResourceLocation(Quark.MOD_ID, "runes"));
        runesLootableTag = ItemTags.createOptional(new ResourceLocation(Quark.MOD_ID, "runes_lootable"));
    }

    private static boolean canHaveRune(ItemStack stack) {
        return stack.isEnchanted() || (stack.getItem() == Items.COMPASS && CompassItem.func_234670_d_(stack)); // func_234670_d_ = is lodestone compass
    }

    @SuppressWarnings("ConstantConditions")
    private static LazyOptional<IRuneColorProvider> get(ICapabilityProvider provider) {
        return provider.getCapability(QuarkCapabilities.RUNE_COLOR);
    }

}