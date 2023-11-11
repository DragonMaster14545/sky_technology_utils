package net.dragonMaster.sky_technology_utils.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_SKY_TECHNOLOGY_UTILS = "key.category.sky_technology_utils.tutorial";
    public static final String KEY_TEST = "key.sky_technology_utils.test";

    public static final KeyMapping TEST_KEY = new KeyMapping(KEY_TEST, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_SKY_TECHNOLOGY_UTILS);
}
