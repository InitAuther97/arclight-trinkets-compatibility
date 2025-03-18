package io.github.initauther97.arclight.atc;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArclightTrinketsComp implements ModInitializer {
	public static final String MOD_ID = "arclight-trinkets-compatibility";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Arclight Trinkets compatibility initialized, provided by InitAuther97");
		var version = FabricLoader.getInstance().getModContainer("trinkets").get().getMetadata().getVersion();
		LOGGER.info("atc v0.1.0, trinkets {}", version);
	}
}