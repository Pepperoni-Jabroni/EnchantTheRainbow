package pepjebs.enchant_the_rainbow.mixin;

import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.BakedModel;
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

    @Inject(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", at = @At("HEAD"))
    private void setColorRuneTargetStack(ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model, CallbackInfo callbackInfo) {
        ColorRunesModule.setTargetStack(stack);
    }

    @Redirect(method = "getArmorGlintConsumer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/RenderLayer;getArmorGlint()Lnet/minecraft/client/render/RenderLayer;"))
    private static RenderLayer getArmorGlint() {
        return ColorRunesModule.getArmorGlint();
    }

    @Redirect(method = "getArmorGlintConsumer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/RenderLayer;getArmorEntityGlint()Lnet/minecraft/client/render/RenderLayer;"))
    private static RenderLayer getArmorEntityGlint() {
        return ColorRunesModule.getArmorEntityGlint();
    }

    @Redirect(method = "getItemGlintConsumer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/RenderLayer;getGlint()Lnet/minecraft/client/render/RenderLayer;"))
    private static RenderLayer getGlint() {
        return ColorRunesModule.getGlint();
    }

    @Redirect(method = "getItemGlintConsumer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/RenderLayer;getEntityGlint()Lnet/minecraft/client/render/RenderLayer;"))
    private static RenderLayer getEntityGlint() {
        return ColorRunesModule.getEntityGlint();
    }

    @Redirect(method = "getDirectItemGlintConsumer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/RenderLayer;getDirectGlint()Lnet/minecraft/client/render/RenderLayer;"))
    private static RenderLayer getGlintDirect() {
        return ColorRunesModule.getGlintDirect();
    }

    @Redirect(method = "getDirectItemGlintConsumer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/RenderLayer;getDirectEntityGlint()Lnet/minecraft/client/render/RenderLayer;"))
    private static RenderLayer getEntityGlintDirect() {
        return ColorRunesModule.getEntityGlintDirect();
    }

//    @Redirect(method = "getGlintVertexBuilder", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/RenderLayer;getGlintVertexBuilder()Lnet/minecraft/client/render/RenderLayer;"))
//    private static RenderLayer getGlintVertexBuilder() {
//        return ColorRunesModule.getGlint();
//    }
//
//    @Redirect(method = "getDirectGlintVertexBuilder", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/RenderLayer;getDirectGlintVertexBuilder()Lnet/minecraft/client/render/RenderLayer;"))
//    private static RenderLayer getDirectGlintVertexBuilder() {
//        return ColorRunesModule.getGlintDirect();
//    }

//    @Accessor
//    public abstract ItemColors getColors();

//    @Inject(method = "renderBakedItemQuads", at = @At(value = "HEAD"), cancellable = true)
//    public void renderQuads(MatrixStack ms, VertexConsumer vertices, List<BakedQuad> quads, ItemStack stack, int light, int overlay, CallbackInfo ci) {
//        boolean flag = !stack.isEmpty();
//        MatrixStack.Entry entry = ms.peek();
//
//        for(BakedQuad bakedquad : quads) {
//            int i = flag && bakedquad.hasColor() ? getColors().getColor(stack, bakedquad.getColorIndex()) : -1;
//
//            float r = (i >> 16 & 255) / 255.0F;
//            float g = (i >> 8 & 255) / 255.0F;
//            float b = (i & 255) / 255.0F;
//            // quad(Entry matrixEntry, BakedQuad quad, float[] brightnesses, float red, float green, float blue, int[] lights, int overlay, boolean useQuadColorData)
//            vertices.quad(entry, bakedquad, new float[]{1.0F, 1.0F, 1.0F, 1.0F}, r, g, b, new int[]{light, light, light, light}, overlay, true);
//        }
//        ci.cancel();
//    }
}