package pepjebs.enchant_the_rainbow.mixin;

import net.minecraft.client.render.RenderPhase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RenderPhase.class)
public interface RenderPhaseAccessor {
    @Accessor("COLOR_MASK")
    static RenderPhase.WriteMaskState getColorMask() {
        throw new AssertionError();
    }

    @Accessor("DISABLE_CULLING")
    static RenderPhase.Cull getDisableCulling() {
        throw new AssertionError();
    }

    @Accessor("EQUAL_DEPTH_TEST")
    static RenderPhase.DepthTest getEqualDepthTest() {
        throw new AssertionError();
    }

    @Accessor("GLINT_TRANSPARENCY")
    static RenderPhase.Transparency getGlintTransparency() {
        throw new AssertionError();
    }

    @Accessor("GLINT_TEXTURING")
    static RenderPhase.Texturing getGlintTexturing() {
        throw new AssertionError();
    }

    @Accessor("ITEM_TARGET")
    static RenderPhase.Target getItemTarget() {
        throw new AssertionError();
    }

    @Accessor("ENTITY_GLINT_TEXTURING")
    static RenderPhase.Texturing getEntityGlintTexturing() {
        throw new AssertionError();
    }

    @Accessor("VIEW_OFFSET_Z_LAYERING")
    static RenderPhase.Layering getViewOffsetZLayering() {
        throw new AssertionError();
    }
}
