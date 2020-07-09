package dashnetwork.vikki;

import dashnetwork.vikki.command.commands.CommandTest;
import dashnetwork.vikki.listeners.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Vikki extends ListenerAdapter {

    private static JDA jda = null;

    public static JDA getJda() {
        return jda;
    }

    public static void main(String[] args) {
        try {
            InputStream input = Vikki.class.getClass().getResourceAsStream("/token.yml");
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String token = reader.readLine();

            JDABuilder builder = JDABuilder.createDefault(token);
            builder.setStatus(OnlineStatus.IDLE);
            builder.addEventListeners(new Vikki());
            builder.build().awaitReady();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void onReady(ReadyEvent event) {
        jda = event.getJDA();

        // TODO: Startup
        jda.addEventListener(new CommandListener());

        new CommandTest();

        jda.getPresence().setStatus(OnlineStatus.ONLINE);
    }

}
