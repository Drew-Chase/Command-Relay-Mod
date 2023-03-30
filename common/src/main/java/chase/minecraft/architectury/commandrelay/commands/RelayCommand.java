package chase.minecraft.architectury.commandrelay.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.commands.TellRawCommand;
import net.minecraft.server.level.ServerPlayer;

public class RelayCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal(".")
                        .redirect(
                                dispatcher.register(Commands.literal("relay")
                                        .then(Commands.argument("command", StringArgumentType.greedyString())
                                                .suggests((ctx, builder) -> dispatcher.getRoot().listSuggestions(ctx, builder))
                                                .executes(ctx -> relay(ctx, StringArgumentType.getString(ctx, "command")) ? 1 : 0)
                                        )
                                )
                        )

        );
    }

    private static boolean relay(CommandContext<CommandSourceStack> context, String cmd) {
        ClickEvent click = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + cmd);
        HoverEvent hover = new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.literal("Click to use command. This will NOT execute command!"));
        MutableComponent message = Component.empty();

        if (context.getSource().isPlayer()) {
            ServerPlayer player = context.getSource().getPlayer();
            if (player != null) {
                message.append(Component.literal(String.format("%s%s%s", ChatFormatting.GOLD, player.getDisplayName().getString(), ChatFormatting.WHITE)));
            }
        } else {
            message.append(Component.literal(String.format("%sSERVER%s", ChatFormatting.GOLD, ChatFormatting.WHITE)));
        }
        message.append(Component.literal(ChatFormatting.GREEN + " has Shared Command: "));
        message.append(Component.literal("[CLICK HERE]").withStyle(style -> style.withColor(ChatFormatting.GOLD).withClickEvent(click).withHoverEvent(hover)));
        context.getSource().getServer().getPlayerList().getPlayers().forEach(player -> {
            player.sendSystemMessage(message);
        });
        return true;
    }
}
