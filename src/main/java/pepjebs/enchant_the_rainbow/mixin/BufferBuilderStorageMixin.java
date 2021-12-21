package pepjebs.enchant_the_rainbow.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.render.RenderLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import pepjebs.enchant_the_rainbow.client.GlintRenderLayer;

@Mixin(BufferBuilderStorage.class)
@Environment(EnvType.CLIENT)
public class BufferBuilderStorageMixin {

    @Inject(method = "assignBufferBuilder", at = @At("HEAD"))
    private static void addGlintTypes(Object2ObjectLinkedOpenHashMap<RenderLayer, BufferBuilder> mapBuildersIn, RenderLayer renderTypeIn, CallbackInfo callbackInfo) {
        GlintRenderLayer.addGlintTypes(mapBuildersIn);
    }
}