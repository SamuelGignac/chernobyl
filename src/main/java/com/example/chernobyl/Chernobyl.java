package com.example.chernobyl;

import com.example.chernobyl.init.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Chernobyl.MODID)
public class Chernobyl
{
    public static final String MODID = "chernobyl";
    public static final Logger LOGGER = LogUtils.getLogger();

//    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
//    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
//    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
//    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
//    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
//    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));
//
//    // Creates a new food item with the id "examplemod:example_id", nutrition 1 and saturation 2
//    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
//            .alwaysEat().nutrition(1).saturationMod(2f).build())));
//
    // Creates a creative tab with the id "examplemod:example_tab" for the example item, that is placed after the combat tab
//    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
//            .withTabsBefore(CreativeModeTabs.COMBAT)
//            .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
//            .displayItems((parameters, output) -> {
//                output.accept(EXAMPLE_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
//            }).build());

    public static final DeferredRegister<Biome> BIOME_REGISTER = DeferredRegister.create(Registries.BIOME, MODID);
    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(Registries.BLOCK, MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_REGISTER = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MODID);
    public static final DeferredRegister<WorldCarver<?>> CARVER_REGISTER = DeferredRegister.create(Registries.CARVER, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TAB_REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredRegister<ConfiguredWorldCarver<?>> CONFIGURED_CARVER_REGISTER = DeferredRegister.create(Registries.CONFIGURED_CARVER, MODID);
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE_REGISTER = DeferredRegister.create(Registries.CONFIGURED_FEATURE, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE_REGISTER = DeferredRegister.create(Registries.ENTITY_TYPE, MODID);
    public static final DeferredRegister<Feature<?>> FEATURE_REGISTER = DeferredRegister.create(Registries.FEATURE, MODID);
    public static final DeferredRegister<Fluid> FLUID_REGISTER = DeferredRegister.create(Registries.FLUID, MODID);
    public static final DeferredRegister<FluidType> FORGE_FLUID_REGISTER = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, MODID);
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(Registries.ITEM, MODID);
    public static final DeferredRegister<ParticleType<?>> PARTICLES_REGISTER = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MODID);
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANT_REGISTER = DeferredRegister.create(Registries.PAINTING_VARIANT, MODID);
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURE_REGISTER = DeferredRegister.create(Registries.PLACED_FEATURE, MODID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENT_REGISTER = DeferredRegister.create(Registries.SOUND_EVENT, MODID);
    public static final DeferredRegister<DamageType> DAMAGE_TYPE_REGISTER = DeferredRegister.create(Registries.DAMAGE_TYPE, MODID);


    public Chernobyl()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::loadComplete);

        // Register events for deferred registers
//        BIOME_REGISTER.register(modEventBus);
        BLOCK_REGISTER.register(modEventBus);
        BLOCK_ENTITY_REGISTER.register(modEventBus);
//        CARVER_REGISTER.register(modEventBus);
//        CREATIVE_TAB_REGISTER.register(modEventBus);
//        CONFIGURED_CARVER_REGISTER.register(modEventBus);
//        CONFIGURED_FEATURE_REGISTER.register(modEventBus);
//        ENTITY_TYPE_REGISTER.register(modEventBus);
//        FEATURE_REGISTER.register(modEventBus);
//        FLUID_REGISTER.register(modEventBus);
//        FORGE_FLUID_REGISTER.register(modEventBus);
        ITEM_REGISTER.register(modEventBus);
//        PARTICLES_REGISTER.register(modEventBus);
//        PLACED_FEATURE_REGISTER.register(modEventBus);
//        SOUND_EVENT_REGISTER.register(modEventBus);
//        DAMAGE_TYPE_REGISTER.register(modEventBus);

        // Initialize the config file first so other things can rely on it
        ModConfig.setup();

        ModBlocks.setup();
        ModItems.setup();
//        ModEntities.setup();
//        ModFeatures.setup();
//        ModBiomes.setup();
//        ModParticles.setup();
//        ModPaintings.setup();
//        ModSounds.setup();

        // Initialize the creative tab last after blocks and items have been setup
//        ModCreativeTab.setup();
        modEventBus.addListener(ModCreativeTab::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
//        event.enqueueWork(ModBiomes::setupTerraBlender);
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        event.enqueueWork(ModBlocks::setRenderTypes);
    }

    private void loadComplete(final FMLLoadCompleteEvent event)
    {
        // Setup tags
        ModTags.setup();
    }

//    public Chernobyl()
//    {
//        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
//
//        Uranium.register(modEventBus);
//        UraniumOre.register(modEventBus);
//
//        Plutonium.register(modEventBus);
//        PlutoniumOre.register(modEventBus);
//
//        // Register the commonSetup method for modloading
//        modEventBus.addListener(this::commonSetup);
//
//        BIOME_REGISTER.register(modEventBus);
//        // Register the Deferred Register to the mod event bus so blocks get registered
//        BLOCKS.register(modEventBus);
//        // Register the Deferred Register to the mod event bus so items get registered
//        ITEMS.register(modEventBus);
//        // Register the Deferred Register to the mod event bus so tabs get registered
//        CREATIVE_MODE_TABS.register(modEventBus);
//
//        // Register ourselves for server and other game events we are interested in
//        MinecraftForge.EVENT_BUS.register(this);
//
//        // Register the item to a creative tab
//        modEventBus.addListener(this::addCreative);
//
//        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
//    }
//
//    private void commonSetup(final FMLCommonSetupEvent event)
//    {
//        // Some common setup code
//        LOGGER.info("HELLO FROM COMMON SETUP");
//
//        if (Config.logDirtBlock)
//            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
//
//        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);
//
//        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
//    }
//
//    // Add the example block item to the building blocks tab
//    private void addCreative(BuildCreativeModeTabContentsEvent event){
//        if (event.getTabKey().equals(CreativeModeTabs.BUILDING_BLOCKS)) {
//            event.accept(EXAMPLE_BLOCK_ITEM);
//        } else if (event.getTabKey().equals(CreativeModeTabs.INGREDIENTS)) {
//            event.accept(Uranium.URANIUM);
//            event.accept(Plutonium.PLUTONIUM);
//        } else if (event.getTabKey().equals(CreativeModeTabs.NATURAL_BLOCKS)) {
//            event.accept(UraniumOre.URANIUM_ORE);
//            event.accept(PlutoniumOre.PLUTONIUM_ORE);
//        }
//    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }

}
