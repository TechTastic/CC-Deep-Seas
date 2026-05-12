package io.github.techtastic.cc_deepseas.peripheral;

import com.maxenonyme.createsubmarine.block.entity.HullControllerBlockEntity;
import com.maxenonyme.createsubmarine.compartment.CompartmentTracker;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IPeripheral;
import dev.ryanhcode.sable.companion.SableCompanion;
import dev.ryanhcode.sable.companion.SubLevelAccess;
import dev.ryanhcode.sable.sublevel.SubLevel;
import io.github.techtastic.cc_deepseas.util.LuaConversions;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class HullControllerPeripheral implements IPeripheral {
    private final HullControllerBlockEntity controller;

    public HullControllerPeripheral(HullControllerBlockEntity controller) {
        this.controller = controller;
    }

    private SubLevel getSubLevel() {
        SubLevelAccess access = SableCompanion.INSTANCE.getContaining(this.controller.getLevel(), this.controller.getBlockPos());
        if (access instanceof SubLevel subLevel)
            return subLevel;
        return null;
    }

    @Override
    public @NonNull String getType() {
        return "oxygenator";
    }

    @Override
    public boolean equals(@Nullable IPeripheral other) {
        return other == this;
    }

    @LuaFunction
    public final @Nullable String getCurrentSubLevelID() {
        SubLevel subLevel = this.getSubLevel();
        if (subLevel == null)
            return null;
        return this.getSubLevel().getUniqueId().toString();
    }

    @LuaFunction
    public final List<Map<String, Object>> getCompartments() {
        SubLevel subLevel = this.getSubLevel();
        if (subLevel == null)
            return List.of();
        return CompartmentTracker.getCompartments(subLevel.getUniqueId()).stream().map(LuaConversions::toLua).toList();
    }
}
