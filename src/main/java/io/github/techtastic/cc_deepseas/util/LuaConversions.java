package io.github.techtastic.cc_deepseas.util;

import com.maxenonyme.createsubmarine.compartment.CompartmentDetector;
import net.minecraft.core.BlockPos;

import java.util.Map;

public class LuaConversions {
    public static Map<String, Object> toLua(CompartmentDetector.Component compartment) {
        return Map.of(
                "internal", compartment.internal().stream().map(LuaConversions::toLua).toList(),
                "hull", compartment.hull().stream().map(LuaConversions::toLua).toList(),
                "sealed", compartment.sealed(),
                "anchor", toLua(compartment.anchor())
        );
    }

    public static Map<String, Integer> toLua(BlockPos pos) {
        return Map.of(
                "x", pos.getX(),
                "y", pos.getY(),
                "z", pos.getZ()
        );
    }
}
