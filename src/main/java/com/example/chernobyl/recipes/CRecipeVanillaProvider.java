package com.example.chernobyl.recipes;

import com.example.chernobyl.api.CBlocks;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class CRecipeVanillaProvider extends VanillaRecipeProvider {
    private final Map<Item, TagKey<Item>> replacements = new HashMap<>();
    private final Set<ResourceLocation> excludes = new HashSet<>();

    public CRecipeVanillaProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, CBlocks.NUCLEAR_BOMB.get(), 1)
                .pattern("III")
                .pattern("RTR")
                .pattern("III")
                .define('I', Ingredient.of(Items.IRON_INGOT, Blocks.IRON_BLOCK))
                .define('R', Ingredient.of(Items.REDSTONE_BLOCK))
                .define('T', Ingredient.of(Items.TNT))
                .unlockedBy("has_tnt", has(Items.TNT))
                .save(consumer);

        super.buildRecipes(vanilla -> {
            FinishedRecipe modified = enhance(vanilla);
            if (modified != null)
                consumer.accept(modified);
        });
    }

    @Nullable
    private FinishedRecipe enhance(FinishedRecipe vanilla) {
        if (vanilla instanceof ShapelessRecipeBuilder.Result shapeless)
            return enhance(shapeless);
        if (vanilla instanceof ShapedRecipeBuilder.Result shaped)
            return enhance(shaped);
        return null;
    }

    @Nullable
    private FinishedRecipe enhance(ShapelessRecipeBuilder.Result vanilla) {
        List<Ingredient> ingredients = getField(ShapelessRecipeBuilder.Result.class, vanilla, 4);
        boolean modified = false;
        for (int x = 0; x < ingredients.size(); x++) {
            Ingredient ing = enhance(vanilla.getId(), ingredients.get(x));
            if (ing != null) {
                ingredients.set(x, ing);
                modified = true;
            }
        }
        return modified ? vanilla : null;
    }

    @Nullable
    @Override
    protected CompletableFuture<?> saveAdvancement(CachedOutput output, FinishedRecipe recipe, JsonObject json) {
        // NOOP - We don't replace any of the advancement things yet...
        return null;
    }

    @Override
    protected CompletableFuture<?> buildAdvancement(CachedOutput output, ResourceLocation name, Advancement.Builder builder) {
        // NOOP - We don't replace any of the advancement things yet...
        return CompletableFuture.allOf();
    }

    @Nullable
    private FinishedRecipe enhance(ShapedRecipeBuilder.Result vanilla) {
        Map<Character, Ingredient> ingredients = getField(ShapedRecipeBuilder.Result.class, vanilla, 5);
        boolean modified = false;
        for (Character x : ingredients.keySet()) {
            Ingredient ing = enhance(vanilla.getId(), ingredients.get(x));
            if (ing != null) {
                ingredients.put(x, ing);
                modified = true;
            }
        }
        return modified ? vanilla : null;
    }

    @Nullable
    private Ingredient enhance(ResourceLocation name, Ingredient vanilla) {
        if (excludes.contains(name))
            return null;

        boolean modified = false;
        List<Ingredient.Value> items = new ArrayList<>();
        Ingredient.Value[] vanillaItems = getField(Ingredient.class, vanilla, 2); //This will probably crash between versions, if null fix index
        for (Ingredient.Value entry : vanillaItems) {
            if (entry instanceof Ingredient.ItemValue) {
                ItemStack stack = entry.getItems().stream().findFirst().orElse(ItemStack.EMPTY);
                TagKey<Item> replacement = replacements.get(stack.getItem());
                if (replacement != null) {
                    items.add(new Ingredient.TagValue(replacement));
                    modified = true;
                } else
                    items.add(entry);
            } else
                items.add(entry);
        }
        return modified ? Ingredient.fromValues(items.stream()) : null;
    }

    @SuppressWarnings("unchecked")
    private <T, R> R getField(Class<T> clz, T inst, int index) {
        Field fld = clz.getDeclaredFields()[index];
        fld.setAccessible(true);
        try {
            return (R) fld.get(inst);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
