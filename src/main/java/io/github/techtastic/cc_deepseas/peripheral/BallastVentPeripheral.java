package io.github.techtastic.cc_deepseas.peripheral;

import com.maxenonyme.createsubmarine.block.entity.BallastVentBlockEntity;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.shared.peripheral.generic.methods.FluidMethods;
import net.minecraft.core.Direction;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class BallastVentPeripheral implements IPeripheral {
    private final BallastVentBlockEntity vent;
    private final Direction direction;
    private final FluidMethods fluidMethods = new FluidMethods();

    public BallastVentPeripheral(BallastVentBlockEntity vent, Direction direction) {
        this.vent = vent;
        this.direction = direction;
    }

    @Override
    public @NonNull String getType() {
        return "ballast_vent";
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
        return this.fluidMethods.tanks(this.vent.getFluidHandlerForSide(this.direction));
    }

    @LuaFunction(mainThread = true)
    public final int pushFluid(IComputerAccess computer, String toName, Optional<Integer> limit, Optional<String> fluidName) throws LuaException {
        return this.fluidMethods.pushFluid(this.vent.getFluidHandlerForSide(this.direction), computer, toName, limit, fluidName);
    }

    @LuaFunction(mainThread = true)
    public final int pullFluid(IComputerAccess computer, String fromName, Optional<Integer> limit, Optional<String> fluidName) throws LuaException {
        return this.fluidMethods.pullFluid(this.vent.getFluidHandlerForSide(this.direction), computer, fromName, limit, fluidName);
    }

    @LuaFunction
    public final boolean isAnyHolesFaceSubmerged() {
        try {
            Class<BallastVentBlockEntity> blockEntityClass = BallastVentBlockEntity.class;
            Method method = blockEntityClass.getDeclaredMethod("isAnyHolesFaceSubmerged");
            method.setAccessible(true);
            return (Boolean) method.invoke(this.vent);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException ignored) {
            return false;
        }
    }
}
