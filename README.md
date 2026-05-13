# CC: Deep Seas
*CC: Tweaked x Create: Deep Seas Addon!*

**CC: Deep Seas** is an addon for **Create: Deep Seas** to add new peripherals and expose internal information about submarines!

## Peripherals
- Ballast Vent
  - Kept Fluid Handler methods
  - Added new `isAnyHolesFaceSubmerged` method
- Water Thruster
  - Kept Fluid Handler methods
  - Added new `getThrust` method
  - Added new `getScaledThrust` method
  - Added new `getAirflow` method
  - Added new `getAirflowScaling` method
  - Added new `getCurrentAirPressure` method
  - Added new `isActive` method
- Creative Oxygenator
  - Added new `getCurrentSubLevelID` method
  - Added new `getCompartments` method

## Extra Block Details
This mod also adds the physics properties of blocks to any method calling to block details, such as `turtle.inspect()`.
These new details will be listed under the `"hullStrength"` key and have the following key-value pairs:
- `maxDepthY: number`
- `implosionChance: number`