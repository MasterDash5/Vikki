package dashnetwork.vikki;

import net.dv8tion.jda.api.JDA;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new Thread(() -> Vikki.main(new String[0])).start();
    }

    @Override
    public void onDisable() {
        JDA jda = Vikki.getJda();

        if (jda != null)
            jda.shutdown();
    }

}
