package pepjebs.enchant_the_rainbow.mixin;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

    @Inject(method = "renderBakedItemModel", at = @At("INVOKE"), cancellable = true)
    private void renderGlintColorItemModel(
            BakedModel model,
            ItemStack stack,
            int light,
            int overlay,
            MatrixStack matrices,
            VertexConsumer vertices,
            CallbackInfo ci) {
        
    }
}
