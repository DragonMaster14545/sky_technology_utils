package net.dragonMaster.sky_technology_utils.PlayerData;

import net.minecraft.nbt.CompoundTag;

public class TestValue {
    private int testValue;
    private final int minTestValue = 0;
    private final int maxTestValue = 1000;

    public int getTestValue(){
        return testValue;
    }
    public void  addTestValue(int add) {
        if(this.testValue > maxTestValue) {
            this.testValue = minTestValue;
        } else {
            this.testValue ++;
        }
    }
    public void  removeTestValue(int remove) {
        this.testValue = Math.max(testValue-remove, minTestValue);
    }

    public void copyFrom(TestValue source) {
    this.testValue = source.testValue;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("testValue",testValue);
    }

    public void loadNBTData(CompoundTag nbt) {
        testValue = nbt.getInt("testValue");
    }
}
