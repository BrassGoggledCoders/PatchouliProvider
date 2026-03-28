package xyz.brassgoggledcoders.patchouliprovider.page;

import com.google.gson.JsonObject;
import net.minecraft.resources.Identifier;
import xyz.brassgoggledcoders.patchouliprovider.AbstractPageBuilder;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

@SuppressWarnings("unchecked")
public abstract class RecipePageBuilder<T extends RecipePageBuilder<T>> extends AbstractPageBuilder<T> {
    private final String recipe;
    private boolean linkRecipe = true;
    private String recipe2;
    private boolean linkRecipe2 = true;
    private String title;
    private String text;

    public RecipePageBuilder(String type, Identifier recipe, EntryBuilder parent) {
        super(type, parent);
        this.recipe = recipe.toString();
    }

    @Override
    protected void serialize(JsonObject json) {
        json.addProperty("recipe", recipe);
        if (!linkRecipe) {
            json.addProperty("link_recipe", false);
        }
        if (recipe2 != null) {
            json.addProperty("recipe2", recipe2);
        }
        if (!linkRecipe2) {
            json.addProperty("link_recipe2", false);
        }
        if (title != null) {
            json.addProperty("title", title);
        }
        if (text != null) {
            json.addProperty("text", text);
        }
    }

    public T setRecipe2(Identifier recipe2) {
        this.recipe2 = recipe2.toString();
        return (T) this;
    }

    public T setLinkRecipe(boolean linkRecipe) {
        this.linkRecipe = linkRecipe;
        return (T) this;
    }

    public T setLinkRecipe2(boolean linkRecipe2) {
        this.linkRecipe2 = linkRecipe2;
        return (T) this;
    }

    public T setTitle(String title) {
        this.title = title;
        return (T) this;
    }

    public T setText(String text) {
        this.text = text;
        return (T) this;
    }

}
