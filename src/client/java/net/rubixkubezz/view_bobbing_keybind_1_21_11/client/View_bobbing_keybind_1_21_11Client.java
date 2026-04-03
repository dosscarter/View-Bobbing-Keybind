package net.rubixkubezz.view_bobbing_keybind_1_21_11.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class View_bobbing_keybind_1_21_11Client implements ClientModInitializer {

    private static final KeyBinding.Category CATEGORY = KeyBinding.Category.create(Identifier.of("viewbobbing", "category"));
    public static final String KEY_TOGGLE_BOBBING = "key.viewbobbingtoggle.toggle.bobbing";

    public static KeyBinding toggleKey;

    @Override
    public void onInitializeClient() {
        System.out.println("Initializing View Bobbing Toggle Mod");
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_TOGGLE_BOBBING,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                CATEGORY
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

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
}
