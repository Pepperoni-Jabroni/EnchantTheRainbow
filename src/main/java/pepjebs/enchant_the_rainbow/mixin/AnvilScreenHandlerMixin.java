package pepjebs.enchant_the_rainbow.mixin;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.*;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pepjebs.enchant_the_rainbow.EnchantTheRainbowMod;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler {

    @Shadow
    @Final
    private Property levelCost;

    @Shadow
    private int repairItemUsage;

    public AnvilScreenHandlerMixin(
            @Nullable ScreenHandlerType<?> type,
            int syncId,
            PlayerInventory playerInventory,
            ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    @Inject(method = "updateResult", at = @At(value = "RETURN"))
    private void updateNetherStarFragmentColor(CallbackInfo ci) {
        // Do Nether Star Fragment + Dye check
        ItemStack leftStack = this.input.getStack(0).copy();
        ItemStack rightStack = this.input.getStack(1).copy();
        String glintColorStr = null;
        if (leftStack.getItem() == EnchantTheRainbowMod.NETHER_STAR_FRAGMENT
                && rightStack.getItem() instanceof DyeItem) {
            glintColorStr =  ((DyeItem) rightStack.getItem()).getColor().getName();
        }
        // Do Enchanted + Nether Star Fragment check
        if (leftStack.hasGlint()
                && rightStack.getItem() == EnchantTheRainbowMod.NETHER_STAR_FRAGMENT
                && rightStack.getOrCreateNbt().contains(EnchantTheRainbowMod.GLINT_COLOR_NBT_TAG)) {
            glintColorStr =  rightStack.getNbt().getString(EnchantTheRainbowMod.GLINT_COLOR_NBT_TAG);
        }
        if (glintColorStr != null) {
            NbtCompound tag = leftStack.getOrCreateNbt();
            tag.putString(EnchantTheRainbowMod.GLINT_COLOR_NBT_TAG, glintColorStr);
            leftStack.setNbt(tag);
            this.output.setStack(0, leftStack);
            this.levelCost.set(1);
            this.repairItemUsage = 1;
        }
    }
}
