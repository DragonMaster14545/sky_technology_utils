package net.dragonMaster.sky_technology_utils;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.common.machine.multiblock.part.ItemBusPartMachine;

public class GtOverrides  extends ItemBusPartMachine {
    public int slots;
    public GtOverrides(IMachineBlockEntity holder, int tier, int slots, IO io, Object... args) {
        super(holder, tier, io, args, slots);
    }

    @Override
    protected NotifiableItemStackHandler createInventory(Object... args) {
        if (args.length > 0 && args[args.length - 1] instanceof Integer slotsValue) {
            return new NotifiableItemStackHandler(this, slotsValue, io);
        }
        throw new IllegalArgumentException("CustomSlotItemBusPartMachine needs args [slots] for initialization");
    }
}
