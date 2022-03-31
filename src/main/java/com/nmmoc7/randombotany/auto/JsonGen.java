package com.nmmoc7.randombotany.auto;

import com.nmmoc7.randombotany.RandomBotany;
import com.nmmoc7.randombotany.event.RegisterHandler;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author DustW
 **/
public class JsonGen {
    private static final boolean need = false;
    private static final Logger logger = LogManager.getLogger();

    public static void generate() {
        if (need) {
            RegisterHandler.getFlowers().forEach((s, c) -> {
                Path path = normalPath("blockstates", new ResourceLocation(RandomBotany.MOD_ID, s));
                save(path, json(s));
            });
        }
    }

    protected static void save(Path path, String json) {
        try {
            if (path.toFile().exists()) {
                path.toFile().delete();
            }

            Files.createDirectories(path.getParent());
            try (BufferedWriter bufferedwriter = Files.newBufferedWriter(path)) {
                bufferedwriter.write(json);
            }
            path.toFile().createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Path out() {
        return new File(new File(".").getAbsolutePath()).toPath().resolve("../src/main/resources");
    }

    static String json(String name) {
        return
                "{\n" +
                        "  \"forge_marker\": 1,\n" +
                        "  \"variants\": {\n" +
                        "    \"normal\": [{\n" +
                        "      \"model\": \"botania:shapes/cross_tinted\",\n" +
                        "      \"textures\": {\n" +
                        "        \"cross\": \"random-botany:blocks/" + name + "\"\n" +
                        "      }\n" +
                        "    }],\n" +
                        "    \"inventory\": [{\n" +
                        "      \"model\": \"builtin/generated\",\n" +
                        "      \"transform\": \"forge:default-item\",\n" +
                        "      \"textures\": {\n" +
                        "        \"layer0\": \"random-botany:blocks/" + name + "\"\n" +
                        "      }\n" +
                        "    }]\n" +
                        "  }\n" +
                        "}";
    }

    protected static Path normalPath(String path, ResourceLocation resourceLocation) {
        return out().resolve("assets/" + resourceLocation.getNamespace() + "/" + path + "/" + resourceLocation.getPath() + ".json");
    }
}
