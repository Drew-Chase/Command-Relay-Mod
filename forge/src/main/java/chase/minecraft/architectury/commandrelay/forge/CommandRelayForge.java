package chase.minecraft.architectury.commandrelay.forge;

import dev.architectury.platform.forge.EventBuses;
import chase.minecraft.architectury.commandrelay.CommandRelay;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CommandRelay.MOD_ID)
public class CommandRelayForge {
    public CommandRelayForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(CommandRelay.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        CommandRelay.init();
    }
}