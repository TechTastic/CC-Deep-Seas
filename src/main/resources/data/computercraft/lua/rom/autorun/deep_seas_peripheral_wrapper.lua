--- CC: Deep Seas provides a few new peripherals for Create: Deep Seas mechanics!
--
-- @module Peripherals

--- The Ballast Vent (`ballast_vent`) peripheral exposes a few methods as well as keeping the original `fluid_handler` methods.
--
-- @submodule ballast_vent
-- @usage local vent = peripheral.wrap("left") -- given the Vent is to the left of the computer

-- Determines whether any faces of the vent is submerged in water.
-- @function isAnyH

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