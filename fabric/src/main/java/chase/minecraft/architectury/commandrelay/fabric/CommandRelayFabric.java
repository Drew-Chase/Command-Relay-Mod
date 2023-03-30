package chase.minecraft.architectury.commandrelay.fabric;

import chase.minecraft.architectury.commandrelay.CommandRelay;
import net.fabricmc.api.ModInitializer;

public class CommandRelayFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CommandRelay.init();
    }
}