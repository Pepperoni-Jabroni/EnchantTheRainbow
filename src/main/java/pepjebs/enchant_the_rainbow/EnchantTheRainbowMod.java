package pepjebs.enchant_the_rainbow;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pepjebs.enchant_the_rainbow.item.NetherStarFragmentItem;

public class EnchantTheRainbowMod implements ModInitializer {

    public static String MOD_ID = "enchant_the_rainbow";
    public static Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final String GLINT_COLOR_NBT_TAG = MOD_ID + ":GlintColor";

    public static Item NETHER_STAR_FRAGMENT = null;

    @Override
    public void onInitialize() {
        NETHER_STAR_FRAGMENT = Registry.register(
                Registries.ITEM,
                new Identifier(MOD_ID, "nether_star_fragment"),
                new NetherStarFragmentItem((new Item.Settings()).rarity(Rarity.UNCOMMON)));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            content.addAfter(Items.NETHER_STAR, NETHER_STAR_FRAGMENT);
        });
    }
}
