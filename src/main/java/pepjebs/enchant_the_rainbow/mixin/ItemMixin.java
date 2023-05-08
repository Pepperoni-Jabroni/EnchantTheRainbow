package pepjebs.enchant_the_rainbow.mixin;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pepjebs.enchant_the_rainbow.item.NetherStarFragmentItem;

import java.util.List;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "appendTooltip", at = @At(value = "TAIL"))
    public void appendFragmentColorTooltip(
            ItemStack stack,
            @Nullable World world,
            List<Text> tooltip,
            TooltipContext context,
            CallbackInfo ci) {
        String tooltipColor = NetherStarFragmentItem.getTooltipColorForStack(stack);
        if (tooltipColor == null)
            return;
        tooltip.add(
                Text.translatable(
                        "item.enchant_the_rainbow.nether_star_fragment.color",
                                tooltipColor)
                        .formatted(Formatting.GRAY)
        );
    }
}
