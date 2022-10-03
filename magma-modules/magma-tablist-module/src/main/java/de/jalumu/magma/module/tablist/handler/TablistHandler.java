package de.jalumu.magma.module.tablist.handler;

import de.jalumu.magma.module.tablist.MagmaTablistModule;
import de.jalumu.magma.platform.base.text.placeholder.Placeholders;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.*;

public class TablistHandler {

    private static HashMap<String, String> map = new HashMap<>();

    private static YamlConfiguration configuration;
    private static MagmaTablistModule magma;

    public static void init(MagmaTablistModule magmaTablistModule, YamlConfiguration yml) {
        configuration = yml;
        magma = magmaTablistModule;
    }

    public static void updateTablist() {
        LuckPerms api = LuckPermsProvider.get();

        List<Player> sortedPlayers = new ArrayList<>();

        Bukkit.getOnlinePlayers().stream()
                .sorted(Comparator.comparingInt(value -> api.getGroupManager().getGroup(api.getPlayerAdapter(Player.class).getUser(value).getPrimaryGroup()).getWeight().getAsInt() * -1))
                .forEach(sortedPlayers::add);

        for (Player player : Bukkit.getOnlinePlayers()) {

            Scoreboard board = player.getScoreboard();
            int i = 0;
            for (Player sorted : sortedPlayers) {
                Team team = board.getTeam(i + sorted.getName());
                if (team == null) {
                    team = board.registerNewTeam(i + sorted.getName());
                }
                Component prefix = MiniMessage.miniMessage().deserialize(configuration.getString("tablist.player.prefix"), Placeholders.player(sorted.getUniqueId()));
                team.prefix(prefix);
                team.addPlayer(sorted);
                team.addEntry(sorted.getDisplayName());
                System.out.println(team.getPrefix());
                i++;
            }
        }

    }

    public static void updateDecoration() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Audience audience = player;
            MiniMessage mm = MiniMessage.miniMessage();
            audience.sendPlayerListHeaderAndFooter(mm.deserialize(configuration.getString("tablist.decoration.header"), Placeholders.player(player.getUniqueId())), mm.deserialize(configuration.getString("tablist.decoration.footer"), Placeholders.player(player.getUniqueId())));
        }
    }

}
