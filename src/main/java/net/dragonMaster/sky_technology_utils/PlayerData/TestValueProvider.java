package net.dragonMaster.sky_technology_utils.PlayerData;

import cpw.mods.modlauncher.Launcher;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TestValueProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<TestValue> PLAYER_TEST_VALUE = CapabilityManager.get(new CapabilityToken<TestValue>() {});
    private TestValue testValue = null;
    private final LazyOptional<TestValue> optional = LazyOptional.of(this::createTestValue);

    private TestValue createTestValue() {
        if(this.testValue == null) {
            this.testValue = new TestValue();
        }
        return this.testValue;
    }


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_TEST_VALUE) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createTestValue().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createTestValue().loadNBTData(nbt);
    }
}
