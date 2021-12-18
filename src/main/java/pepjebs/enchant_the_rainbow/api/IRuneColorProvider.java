package pepjebs.enchant_the_rainbow.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;

/**
 * @author WireSegal
 * Ported to Fabric 1.18 by Pepperoni-Jabroni
 * Created at 2:22 PM on 8/17/19.
 */
// This class lovingly yoinked (& updated to 1.18) from
// https://github.com/VazkiiMods/Quark/blob/master/src/main/java/vazkii/quark/api/IRuneColorProvider.java
// Published under the "CC BY-NC-SA 3.0" Creative Commons License
public interface IRuneColorProvider {

    @Environment(EnvType.CLIENT)
    int getRuneColor(ItemStack stack);
}