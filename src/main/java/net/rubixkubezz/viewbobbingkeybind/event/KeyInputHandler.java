package net.rubixkubezz.viewbobbingkeybind.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_TOGGLE = "key.category.viewbobbingtoggle.toggle";
    public static final String KEY_TOGGLE_BOBBING = "key.viewbobbingtoggle.toggle.bobbing";

    public static KeyBinding toggleKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.wasPressed()) {
                boolean bobbingOn = client.options.getBobView().getValue();

                if (bobbingOn && client.player != null) {
                    client.options.getBobView().setValue(false);
                    client.player.sendMessage(Text.literal("View Bobbing Off").formatted(Formatting.RED), true);
                    client.player.playSound(SoundEvents.UI_HUD_BUBBLE_POP, 1f, 1f);
                } else if (client.player != null) {
                    client.options.getBobView().setValue(true);
                    client.player.sendMessage(Text.literal("View Bobbing On").formatted(Formatting.GREEN), true);
                    client.player.playSound(SoundEvents.UI_HUD_BUBBLE_POP, 1f, 1f);
                }
            }
        });
    }

    public static void register() {
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
           KEY_TOGGLE_BOBBING,
           InputUtil.Type.KEYSYM,
           GLFW.GLFW_KEY_K,
           KEY_CATEGORY_TOGGLE
        ));

        registerKeyInputs();
    }
}
