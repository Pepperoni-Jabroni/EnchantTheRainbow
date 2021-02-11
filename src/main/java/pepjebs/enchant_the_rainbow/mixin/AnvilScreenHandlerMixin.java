package pepjebs.enchant_the_rainbow.mixin;

import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pepjebs.enchant_the_rainbow.EnchantTheRainbowMod;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {

    @Inject(method = "updateResult", at = @At(value = "RETURN"))
    private void updateNetherStarFragmentColor(CallbackInfo ci) {
        EnchantTheRainbowMod.LOGGER.info("Here");
    }
}
