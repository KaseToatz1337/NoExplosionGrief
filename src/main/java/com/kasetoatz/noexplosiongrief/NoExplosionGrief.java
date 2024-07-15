package com.kasetoatz.noexplosiongrief;

import com.kasetoatz.noexplosiongrief.config.Config;
import net.fabricmc.api.ModInitializer;

public class NoExplosionGrief implements ModInitializer {
    @Override
    public void onInitialize() {
        Config.load();
    }
}
