package dashnetwork.vikki.command;

import dashnetwork.vikki.Vikki;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {

    private static List<Command> commands = new ArrayList<>();
    protected static JDA jda = Vikki.getJda();
    private boolean permission;
    private String[] labels;

    public Command(boolean permission, String... labels) {
        this.permission = permission;
        this.labels = labels;

        commands.add(this);
    }

    public static List<Command> getCommands() {
        return commands;
    }

    public boolean getPermission() {
        return permission;
    }

    public String[] getLabels() {
        return labels;
    }

    public abstract void onCommand(TextChannel channel, User user, String label, String[] args);

}
