package pepjebs.enchant_the_rainbow.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.TridentEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pepjebs.enchant_the_rainbow.core.EnchantTheRainbowCore;

@Mixin(TridentEntityRenderer.class)
@Environment(EnvType.CLIENT)
public abstract class TridentEntityRendererMixin extends EntityRenderer<TridentEntity>  {

    protected TridentEntityRendererMixin(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void setEnchantTheRainbowItemStack(
            TridentEntity tridentEntity,
            float f,
            float g,
            MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider,
            int i,
            CallbackInfo ci) {
        ItemStack itemStack = tridentEntity.getItemStack();
        EnchantTheRainbowCore.setTargetStack(itemStack);
    }
}
