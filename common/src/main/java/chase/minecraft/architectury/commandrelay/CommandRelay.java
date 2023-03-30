package chase.minecraft.architectury.commandrelay;

import chase.minecraft.architectury.commandrelay.commands.RelayCommand;
import dev.architectury.event.events.common.CommandRegistrationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandRelay {

    public static final Logger log = LogManager.getLogger("Comand Relay");
    public static final String MOD_ID = "commandrelay";

    public static void init() {
        log.info("Initializing Command Relay!");
        CommandRegistrationEvent.EVENT.register((dispatcher, context, selection) -> {
            RelayCommand.register(dispatcher);
        });
    }
}