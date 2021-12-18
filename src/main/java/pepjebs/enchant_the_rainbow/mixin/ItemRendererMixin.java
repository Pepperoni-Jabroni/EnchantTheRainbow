package pepjebs.enchant_the_rainbow.mixin;

import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pepjebs.enchant_the_rainbow.module.ColorRunesModule;

import java.util.List;

// This class lovingly yoinked (& updated to 1.18) from
// https://github.com/VazkiiMods/Quark/blob/master/src/main/java/vazkii/quark/mixin/client/ItemRendererMixin.java
// Published under the "CC BY-NC-SA 3.0" Creative Commons License
@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Inject(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/model/ItemCameraTransforms$TransformType;ZLcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;IILnet/minecraft/client/renderer/model/IBakedModel;)V", at = @At("HEAD"))
    private void setColorRuneTargetStack(ItemStack itemStackIn, ModelTransformation.Mode transformTypeIn, boolean leftHand, MatrixStack matrixStackIn, VertexConsumerProvider vertexConsumers, int combinedLightIn, int combinedOverlayIn, IBakedModel modelIn, CallbackInfo callbackInfo) {
        ColorRunesModule.setTargetStack(itemStackIn);
    }

    @Redirect(method = "getArmorGlintConsumer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderType;getArmorGlint()Lnet/minecraft/client/renderer/RenderType;"))
    private static RenderLayer getArmorGlint() {
        return ColorRunesModule.getArmorGlint();
    }

    @Redirect(method = "getArmorGlintConsumer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderType;getArmorEntityGlint()Lnet/minecraft/client/renderer/RenderType;"))
    private static RenderLayer getArmorEntityGlint() {
        return ColorRunesModule.getArmorEntityGlint();
    }

    @Redirect(method = "getBuffer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderType;getGlint()Lnet/minecraft/client/renderer/RenderType;"))
    private static RenderLayer getGlint() {
        return ColorRunesModule.getGlint();
    }

    @Redirect(method = "getBuffer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderType;getEntityGlint()Lnet/minecraft/client/renderer/RenderType;"))
    private static RenderLayer getEntityGlint() {
        return ColorRunesModule.getEntityGlint();
    }

    @Redirect(method = "getEntityGlintVertexBuilder", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderType;getGlintDirect()Lnet/minecraft/client/renderer/RenderType;"))
    private static RenderLayer getGlintDirect() {
        return ColorRunesModule.getGlintDirect();
    }

    @Redirect(method = "getEntityGlintVertexBuilder", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderType;getEntityGlintDirect()Lnet/minecraft/client/renderer/RenderType;"))
    private static RenderLayer getEntityGlintDirect() {
        return ColorRunesModule.getEntityGlintDirect();
    }

    @Redirect(method = "getGlintVertexBuilder", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderType;getGlint()Lnet/minecraft/client/renderer/RenderType;"))
    private static RenderLayer getGlintVertexBuilder() {
        return ColorRunesModule.getGlint();
    }

    @Redirect(method = "getDirectGlintVertexBuilder", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderType;getGlintDirect()Lnet/minecraft/client/renderer/RenderType;"))
    private static RenderLayer getDirectGlintVertexBuilder() {
        return ColorRunesModule.getGlintDirect();
    }

    @Accessor
    public abstract ItemColors getItemColors();

    @Inject(method = "renderBakedItemQuads", at = @At(value = "HEAD"), cancellable = true)
    public void renderQuads(MatrixStack ms, VertexConsumer vertices, List<BakedQuad> quads, ItemStack stack, int lightmap, int overlay, CallbackInfo ci) {
        if (ItemSharingModule.alphaValue != 1.0F) {
            boolean flag = !stack.isEmpty();
            MatrixStack.Entry entry = ms.peek();

            for(BakedQuad bakedquad : quads) {
                int i = flag && bakedquad.hasColor() ? getItemColors().getColor(stack, bakedquad.getColorIndex()) : -1;

                float r = (i >> 16 & 255) / 255.0F;
                float g = (i >> 8 & 255) / 255.0F;
                float b = (i & 255) / 255.0F;
                vertices.quad(entry, bakedquad, r, g, b, ItemSharingModule.alphaValue, lightmap, overlay, true);
            }
            ci.cancel();
        }
    }
}