package dashnetwork.vikki.listeners;

import dashnetwork.vikki.command.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User user = event.getAuthor();
        String message = event.getMessage().getContentRaw();

        if (!user.isBot() && event.isFromGuild() && message.startsWith("!")) {
            TextChannel channel = event.getTextChannel();
            Member member = event.getMember();

            message = message.substring(1);

            while (message.contains("  "))
                message = message.replace("  ", " ");

            String[] split = message.split(" ");
            String label = split[0];
            List<String> args = new ArrayList<>();

            for (int i = 1; i < split.length; i++)
                args.add(split[i]);

            for (Command command : Command.getCommands()) {
                if (!command.getPermission() || member.hasPermission(Permission.ADMINISTRATOR)) {
                    for (String labels : command.getLabels()) {
                        if (message.split(" ")[0].equalsIgnoreCase(labels)) {
                            command.onCommand(channel, user, label, args.toArray(new String[args.size()]));
                        }
                    }
                }
            }
        }
    }

}
