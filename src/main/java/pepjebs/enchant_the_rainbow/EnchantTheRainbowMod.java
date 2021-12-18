package pepjebs.enchant_the_rainbow;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnchantTheRainbowMod implements ModInitializer {

    public static String MOD_ID = "enchant_the_rainbow";
    public static Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final String GLINT_COLOR_NBT_TAG = MOD_ID + ":GlintColor";

    public static Item NETHER_STAR_FRAGMENT = null;

    @Override
    public void onInitialize() {
        Registry.register(
                Registry.ITEM,
                new Identifier(MOD_ID, "nether_star_fragment"),
                new Item((new Item.Settings()).group(ItemGroup.MISC).rarity(Rarity.UNCOMMON)));
        NETHER_STAR_FRAGMENT = Registry.ITEM.get(new Identifier(MOD_ID, "nether_star_fragment"));
    }
}
