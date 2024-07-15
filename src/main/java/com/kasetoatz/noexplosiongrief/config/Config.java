package com.kasetoatz.noexplosiongrief.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    public static boolean blockCreeper = true;
    public static boolean blockGhast = true;
    public static boolean blockWither = true;

    private static final File config = new File(MinecraftClient.getInstance().runDirectory, "config/noexplosiongrief.json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void load()
    {
        if (!config.exists())
        {
            save();
            return;
        }
        try (FileReader reader = new FileReader(config))
        {
            JsonObject json = gson.fromJson(reader, JsonObject.class);
            if (json.has("blockCreeper"))
            {
                blockCreeper = json.get("blockCreeper").getAsBoolean();
            }
            if (json.has("blockGhast"))
            {
                blockGhast = json.get("blockGhast").getAsBoolean();
            }
            if (json.has("blockWither"))
            {
                blockWither = json.get("blockWither").getAsBoolean();
            }
        }
        catch (IOException exc)
        {
            throw new CrashException(CrashReport.create(exc, "Loading config file."));
        }
    }

    public static void save()
    {
        JsonObject json = new JsonObject();
        json.addProperty("blockCreeper", blockCreeper);
        json.addProperty("blockGhast", blockGhast);
        json.addProperty("blockWither", blockWither);
        try (FileWriter writer = new FileWriter(config))
        {
            gson.toJson(json, writer);
        }
        catch (IOException exc)
        {
            throw new CrashException(CrashReport.create(exc, "Saving config file."));
        }
    }
}
