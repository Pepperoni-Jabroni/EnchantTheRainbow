package pepjebs.enchant_the_rainbow.client;

import java.util.ArrayList;
import java.util.List;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.RenderLayer;

// This class lovingly yoinked (& updated to 1.18) from
// https://github.com/VazkiiMods/Quark/blob/master/src/main/java/vazkii/quark/content/tools/client/GlintRenderType.java
// Published under the "CC BY-NC-SA 3.0" Creative Commons License
@Environment(EnvType.CLIENT)
public class GlintRenderLayer {

    public static List<RenderLayer> glintColor = newRenderList(GlintRenderLayer::buildGlintRenderLayer);
    public static List<RenderLayer> entityGlintColor = newRenderList(GlintRenderLayer::buildEntityGlintRenderLayer);
    public static List<RenderLayer> glintDirectColor = newRenderList(GlintRenderLayer::buildGlintDirectRenderLayer);
    public static List<RenderLayer> entityGlintDirectColor = newRenderList(GlintRenderLayer::buildEntityGlintDriectRenderLayer);

    public static List<RenderLayer> armorGlintColor = newRenderList(GlintRenderLayer::buildArmorGlintRenderLayer);
    public static List<RenderLayer> armorEntityGlintColor = newRenderList(GlintRenderLayer::buildArmorEntityGlintRenderLayer);

    public static void addGlintTypes(Object2ObjectLinkedOpenHashMap<RenderLayer, BufferBuilder> map) {
        addGlintTypes(map, glintColor);
        addGlintTypes(map, entityGlintColor);
        addGlintTypes(map, glintDirectColor);
        addGlintTypes(map, entityGlintDirectColor);
        addGlintTypes(map, armorGlintColor);
        addGlintTypes(map, armorEntityGlintColor);
    }

    private static List<RenderLayer> newRenderList(Function<String, RenderLayer> func) {
        ArrayList<RenderLayer> list = new ArrayList<>(ColorRunesModule.RUNE_TYPES + 1);

        for (DyeColor color : DyeColor.values())
            list.add(func.apply(color.getTranslationKey()));
        list.add(func.apply("rainbow"));
        list.add(func.apply("blank"));

        return list;
    }

    private static void addGlintTypes(Object2ObjectLinkedOpenHashMap<RenderLayer, BufferBuilder> map, List<RenderLayer> typeList) {
        for(RenderLayer renderType : typeList)
            if (!map.containsKey(renderType))
                map.put(renderType, new BufferBuilder(renderType.getBufferSize()));
    }

    private static RenderLayer buildGlintRenderLayer(String name) {
        final ResourceLocation res = new ResourceLocation(Quark.MOD_ID, "textures/glint/enchanted_item_glint_" + name + ".png");

        return RenderLayer.makeType("glint_" + name, DefaultVertexFormats.POSITION_TEX, 7, 256, RenderLayer.State.getBuilder()
                .texture(new RenderState.TextureState(res, true, false))
                .writeMask(RenderState.COLOR_WRITE)
                .cull(RenderState.CULL_DISABLED)
                .depthTest(RenderState.DEPTH_EQUAL)
                .transparency(RenderState.GLINT_TRANSPARENCY)
                .target(RenderState.field_241712_U_)
                .texturing(RenderState.GLINT_TEXTURING)
                .build(false));
    }

    private static RenderLayer buildEntityGlintRenderLayer(String name) {
        final ResourceLocation res = new ResourceLocation(Quark.MOD_ID, "textures/glint/enchanted_item_glint_" + name + ".png");

        return RenderLayer.makeType("entity_glint_" + name, DefaultVertexFormats.POSITION_TEX, 7, 256, RenderLayer.State.getBuilder()
                .texture(new RenderState.TextureState(res, true, false))
                .writeMask(RenderState.COLOR_WRITE)
                .cull(RenderState.CULL_DISABLED)
                .depthTest(RenderState.DEPTH_EQUAL)
                .transparency(RenderState.GLINT_TRANSPARENCY)
                .target(RenderState.field_241712_U_)
                .texturing(RenderState.ENTITY_GLINT_TEXTURING)
                .build(false));
    }


    private static RenderLayer buildGlintDirectRenderLayer(String name) {
        final ResourceLocation res = new ResourceLocation(Quark.MOD_ID, "textures/glint/enchanted_item_glint_" + name + ".png");

        return RenderLayer.makeType("glint_direct_" + name, DefaultVertexFormats.POSITION_TEX, 7, 256, RenderLayer.State.getBuilder()
                .texture(new RenderState.TextureState(res, true, false))
                .writeMask(RenderState.COLOR_WRITE)
                .cull(RenderState.CULL_DISABLED)
                .depthTest(RenderState.DEPTH_EQUAL)
                .transparency(RenderState.GLINT_TRANSPARENCY)
                .texturing(RenderState.GLINT_TEXTURING)
                .build(false));
    }


    private static RenderLayer buildEntityGlintDriectRenderLayer(String name) {
        final ResourceLocation res = new ResourceLocation(Quark.MOD_ID, "textures/glint/enchanted_item_glint_" + name + ".png");

        return RenderLayer.makeType("entity_glint_direct_" + name, DefaultVertexFormats.POSITION_TEX, 7, 256, RenderLayer.State.getBuilder()
                .texture(new RenderState.TextureState(res, true, false))
                .writeMask(RenderState.COLOR_WRITE)
                .cull(RenderState.CULL_DISABLED)
                .depthTest(RenderState.DEPTH_EQUAL)
                .transparency(RenderState.GLINT_TRANSPARENCY)
                .texturing(RenderState.ENTITY_GLINT_TEXTURING)
                .build(false));
    }

    private static RenderLayer buildArmorGlintRenderLayer(String name) {
        final ResourceLocation res = new ResourceLocation(Quark.MOD_ID, "textures/glint/enchanted_item_glint_" + name + ".png");

        return RenderLayer.makeType("entity_glint_direct_" + name, DefaultVertexFormats.POSITION_TEX, 7, 256, RenderLayer.State.getBuilder()
                .texture(new RenderState.TextureState(res, true, false))
                .writeMask(RenderState.COLOR_WRITE)
                .cull(RenderState.CULL_DISABLED)
                .depthTest(RenderState.DEPTH_EQUAL)
                .transparency(RenderState.GLINT_TRANSPARENCY)
                .texturing(RenderState.ENTITY_GLINT_TEXTURING)
                .layer(RenderState.field_239235_M_)
                .build(false));
    }

    private static RenderLayer buildArmorEntityGlintRenderLayer(String name) {
        final ResourceLocation res = new ResourceLocation(Quark.MOD_ID, "textures/glint/enchanted_item_glint_" + name + ".png");

        return RenderLayer.makeType("entity_glint_direct_" + name, DefaultVertexFormats.POSITION_TEX, 7, 256, RenderLayer.State.getBuilder()
                .texture(new RenderState.TextureState(res, true, false))
                .writeMask(RenderState.COLOR_WRITE)
                .cull(RenderState.CULL_DISABLED)
                .depthTest(RenderState.DEPTH_EQUAL)
                .transparency(RenderState.GLINT_TRANSPARENCY)
                .texturing(RenderState.ENTITY_GLINT_TEXTURING)
                .layer(RenderState.field_239235_M_)
                .build(false));
    }
}