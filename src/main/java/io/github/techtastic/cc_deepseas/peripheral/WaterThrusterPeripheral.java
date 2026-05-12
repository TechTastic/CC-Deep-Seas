package io.github.techtastic.cc_deepseas.peripheral;

import com.maxenonyme.createsubmarine.block.entity.WaterThrusterBlockEntity;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.shared.peripheral.generic.methods.FluidMethods;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class WaterThrusterPeripheral implements IPeripheral {
    private final WaterThrusterBlockEntity thruster;
    private final FluidMethods fluidMethods = new FluidMethods();

    public WaterThrusterPeripheral(WaterThrusterBlockEntity thruster) {
        this.thruster = thruster;
    }

    @Override
    public @NonNull String getType() {
        return "water_thruster";
    }

    @Override
    public @NonNull Set<String> getAdditionalTypes() {
        return this.fluidMethods.getType().getAdditionalTypes();
    }

    @Override
    public boolean equals(@Nullable IPeripheral other) {
        return other == this;
    }

    @LuaFunction(mainThread = true)
    public final Map<Integer, Map<String, ?>> tanks() {
        return this.fluidMethods.tanks(this.thruster.waterTank);
    }

    @LuaFunction(mainThread = true)
    public final int pushFluid(IComputerAccess computer, String toName, Optional<Integer> limit, Optional<String> fluidName) throws LuaException {
        return this.fluidMethods.pushFluid(this.thruster.waterTank, computer, toName, limit, fluidName);
    }

    @LuaFunction(mainThread = true)
    public final int pullFluid(IComputerAccess computer, String fromName, Optional<Integer> limit, Optional<String> fluidName) throws LuaException {
        return this.fluidMethods.pullFluid(this.thruster.waterTank, computer, fromName, limit, fluidName);
    }

    @LuaFunction
    public final double getThrust() {
        return this.thruster.getThrust();
    }

    @LuaFunction
    public final double getScaledThrust() {
        return this.thruster.getScaledThrust();
    }

    @LuaFunction
    public final double getAirflow() {
        return this.thruster.getAirflow();
    }

    @LuaFunction
    public final double getAirflowScaling() {
        return this.thruster.getAirflowScaling();
    }

    @LuaFunction
    public final double getCurrentAirPressure() {
        return this.thruster.getCurrentAirPressure();
    }

    @LuaFunction
    public final boolean isActive() {
        return this.thruster.isActive();
    }
}
