package io.github.techtastic.cc_deepseas;

import com.maxenonyme.createsubmarine.CreateSubmarine;
import com.maxenonyme.createsubmarine.config.HullStrengthConfig;
import dan200.computercraft.api.detail.VanillaDetailRegistries;
import dan200.computercraft.api.peripheral.PeripheralCapability;
import io.github.techtastic.cc_deepseas.peripheral.BallastVentPeripheral;
import io.github.techtastic.cc_deepseas.peripheral.HullControllerPeripheral;
import io.github.techtastic.cc_deepseas.peripheral.WaterThrusterPeripheral;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

import java.util.Map;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(CCDeepSeas.MODID)
public class CCDeepSeas {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "cc_deepseas";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public CCDeepSeas(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::registerCapabilities);

        VanillaDetailRegistries.BLOCK_IN_WORLD.addProvider((data, object) ->
                HullStrengthConfig.getFor(object.state()).ifPresent(config -> data.put("hullStrength", Map.of(
                        "maxDepthY", config.maxDepthY(),
                        "implosionChance", config.implosionChance()
                )))
        );
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                PeripheralCapability.get(),
                CreateSubmarine.BALLAST_VENT_BE.get(),
                BallastVentPeripheral::new
        );

        event.registerBlockEntity(
                PeripheralCapability.get(),
                CreateSubmarine.WATER_THRUSTER_BE.get(),
                (thruster, direction) -> new WaterThrusterPeripheral(thruster)
        );

        event.registerBlockEntity(
                PeripheralCapability.get(),
                CreateSubmarine.CREATIVE_OXYGENATOR_BE.get(),
                (controller, direction) -> new HullControllerPeripheral(controller)
        );
    }
}
