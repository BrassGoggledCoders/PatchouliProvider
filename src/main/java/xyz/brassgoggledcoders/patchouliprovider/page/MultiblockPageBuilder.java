package xyz.brassgoggledcoders.patchouliprovider.page;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import xyz.brassgoggledcoders.patchouliprovider.AbstractPageBuilder;
import xyz.brassgoggledcoders.patchouliprovider.EntryBuilder;

import java.util.List;
import java.util.Map;

public class MultiblockPageBuilder extends AbstractPageBuilder<MultiblockPageBuilder> {
    private final String multiblockId;
    private JsonObject multiblock;
    private Boolean enableVisualize = true;
    private String name;
    private String text;

    public MultiblockPageBuilder(String multiblockId, JsonObject multiblock, EntryBuilder entryBuilder) {
        super("patchouli:multiblock", entryBuilder);
        this.multiblockId = multiblockId;
        this.multiblock = multiblock;
    }

    @Override
    protected void serialize(JsonObject json) {
        json.addProperty("multiblock_id", multiblockId);
        json.add("multiblock", multiblock);
        if (!enableVisualize) {
            json.addProperty("enable_visualize", enableVisualize);
        }
        if (name != null) {
            json.addProperty("name", name);
        }
        if (text != null) {
            json.addProperty("text", text);
        }
    }

    public MultiblockPageBuilder setEnableVisualize(Boolean value) {
        this.enableVisualize = value;
        return this;
    }


    public MultiblockPageBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MultiblockPageBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public static class MultiblockBuilder {
        private String[][] pattern;
        private JsonObject mapping;
        private Boolean symmetrical;
        private int[] offset;
        private JsonObject sparsePattern;

        public MultiblockBuilder setPattern(String[][] pattern) {
            this.pattern = pattern;
            return this;
        }

        public MultiblockBuilder setMapping(Map<String, String> mapping) {
            this.mapping = new JsonObject();
            for (Map.Entry<String, String> entry : mapping.entrySet()) {
                this.mapping.addProperty(entry.getKey(), entry.getValue());
            }
            return this;
        }

        public MultiblockBuilder setSymmetrical(Boolean symmetrical) {
            this.symmetrical = symmetrical;
            return this;
        }

        public MultiblockBuilder setOffset(int x, int y, int z) {
            this.offset = new int[]{x, y, z};
            return this;
        }

        public MultiblockBuilder setSparsePattern(Map<String, List<int[]>> sparsePattern) {
            this.sparsePattern = new JsonObject();
            for (Map.Entry<String, List<int[]>> entry : sparsePattern.entrySet()) {
                JsonArray positions = new JsonArray();
                for (int[] pos : entry.getValue()) {
                    JsonArray position = new JsonArray();
                    for (int coord : pos) {
                        position.add(coord);
                    }
                    positions.add(position);
                }
                this.sparsePattern.add(entry.getKey(), positions);
            }
            return this;
        }

        private void serialize(JsonObject json) {
            if (pattern != null) {
                json.add("pattern", serializePattern());
            }
            if (mapping != null) {
                json.add("mapping", mapping);
            }
            if (symmetrical != null) {
                json.addProperty("symmetrical", symmetrical);
            }
            if (offset != null) {
                json.add("offset", serializeOffset());
            }
            if (sparsePattern != null) {
                json.add("sparse_pattern", sparsePattern);
            }
        }

        private JsonArray serializePattern() {
            JsonArray patternJson = new JsonArray();
            for (String[] layer : pattern) {
                JsonArray layerJson = new JsonArray();
                for (String row : layer) {
                    layerJson.add(row);
                }
                patternJson.add(layerJson);
            }
            return patternJson;
        }

        private JsonObject serializeOffset() {
            JsonObject offsetJson = new JsonObject();
            offsetJson.addProperty("x", offset[0]);
            offsetJson.addProperty("y", offset[1]);
            offsetJson.addProperty("z", offset[2]);
            return offsetJson;
        }

        public JsonObject build() {
            JsonObject json = new JsonObject();
            serialize(json);
            return json;
        }
    }
}
