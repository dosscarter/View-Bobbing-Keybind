package net.rubixkubezz.viewbobbingkeybind;

import net.fabricmc.api.ClientModInitializer;
import net.rubixkubezz.viewbobbingkeybind.event.KeyInputHandler;

public class ViewBobbingKeybindClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
    }
}
