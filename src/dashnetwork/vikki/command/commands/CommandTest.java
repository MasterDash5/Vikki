package dashnetwork.vikki.command.commands;

import dashnetwork.vikki.command.Command;
import net.dv8tion.jda.api.entities.*;

public class CommandTest extends Command {

    public CommandTest() {
        super(true, "test");
    }

    @Override
    public void onCommand(TextChannel channel, User user, String label, String[] args) {
        String message = user.getAsTag() + "\n"
                + label + "\n"
                + args;

        channel.sendMessage(message).queue();
    }

}
