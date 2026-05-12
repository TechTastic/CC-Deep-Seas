--- CC: Deep Seas provides a few new peripherals for Create: Deep Seas mechanics!
--
-- @module Peripherals

--- The Ballast Vent (`ballast_vent`) peripheral exposes a few methods as well as keeping the original `fluid_handler` methods.
--
-- @submodule ballast_vent
-- @usage local vent = peripheral.wrap("left") -- given the Vent is to the left of the computer

--- Determines whether any faces of the vent are submerged in water.
-- @function isAnyHolesFaceSubmerged
-- @treturn boolean Whether any faces of the vent are submerged in water.
-- @usage
-- if vent.isAnyHolesFaceSubmerged() then
--    print("The vent is submerged in water!")
-- else
--    print("The vent is not submerged in water.")
-- end

--- The Water Thruster (`water_thruster`) peripheral exposes a few methods as well as keeping the original `fluid_handler` methods.
--
-- @submodule water_thruster
-- @usage local thruster = peripheral.wrap("left") -- given the Thruster is to the left of the computer

--- Gets the currently output thrust if the thruster.
-- @function getThrust
-- @treturn number The currently output thrust.
-- @usage print("Thruster: " .. tostring(thruster.getThrust()))

--- Gets the scaled output thrust of the thruster.
-- @function getScaledThrust
-- @treturn number The scaled output thrust.
-- @usage print("Scaled Thruster: " .. tostring(thruster.getScaledThrust

--- Gets the airflow of the thruster.
-- @function getAirflow
-- @treturn number The airflow of the thruster.
-- @usage print("Airflow: " .. tostring(thruster.getAirflow()))

--- Gets the scaled airflow of the thruster.
-- @function getScaledAirflow
-- @treturn number The scaled airflow of the thruster.
-- @usage print("Scaled Airflow: " .. tostring(thruster.getScaledAirflow()))

--- Gets the current air pressure of the thruster.
-- @function getCurrentAirPressure
-- @treturn number The current air pressure of the thruster.
-- @usage print("Current Air Pressure: " .. tostring(thruster.getCurrentAirPressure()))

--- Determines whether the thruster is active.
-- @function isActive
-- @treturn boolean Whether the thruster is active.
-- @usage
-- if thruster.isActive() then
--    print("The thruster is active!")
-- end

--- The Oxygenator (`oxygenator`) peripheral exposes a few methods.
--
-- @submodule creative_oxygenator
-- @usage local oxygenator = peripheral.wrap("left") -- given the Oxygenator is to the left of the computer

--- Gets the current Sub-Level ID of the Sub-Level
--
-- @function getCurrentSubLevelID
-- @treturn string The current Sub-Level ID of the Sub-Level.
-- @usage print("Current Sub-Level ID: " .. oxygenator.getCurrentSubLevelID())

--- Gets the current compartment data of the Sub-Level.
--
-- @function getCompartments
-- @treturn array[table] The current compartment data of the Sub-Level.
-- @usage
-- local compartments = oxygenator.getCompartments()
-- for i, compartment in pairs(compartments) do
--    print("Compartment " .. i .. ":")
--    print("\tAnchor: " .. tostring(compartment.anchor)) -- `anchor` is a `vector`
--    print("\tInternals:")
--    for k, internal in pairs(compartment.internal) do
--        print("\t\t" .. tostring(internal)) -- `internal` is a list of `vector`s
--    end
--    print("\tHull:")
--    for k, hull in pairs(compartment.hull) do
--        print("\t\t" .. tostring(hull)) -- `hull` is a list of `vector`s
--    end
---   if compartment.sealed then
--        print("\tCompartment is sealed.")
--    else
--        print("\tCompartment is not sealed.")
--    end
-- end

local native = peripheral.call

peripheral.call = function(...)
	local args = table.pack(...)
	local name = args[1]
	local method = args[2]
	if peripheral.hasType(name, "oxygenator") and method == "getCompartments" then
		local status, result = pcall(function() return native(table.unpack(args)) end)
        if not status then
            error(result)
        end
		if result == nil then
			return nil
		end

		for i,j in pairs(result) do
			for k,v in pairs(j.internal) do
				j.internal[k] = vector.new(v.x, v.y, v.z)
			end
			for k,v in pairs(j.hull) do
				j.hull[k] = vector.new(v.x, v.y, v.z)
			end
			j.anchor = vector.new(j.anchor.x, j.anchor.y, j.anchor.z)
		end
		return result
	end
	return native(table.unpack(args))
end