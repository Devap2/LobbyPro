package me.devap.lobbypro.commands;

import me.devap.lobbypro.LobbyPro;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreBoardCMD implements CommandExecutor {

    private LobbyPro plugin;

    public ScoreBoardCMD(LobbyPro plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        String pname = p.getName();
        int onlinePlayers = plugin.getServer().getOnlinePlayers().size();
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        assert manager != null;
        Scoreboard scoreboard = manager.getNewScoreboard();

        // Creating the title and display name for the scoreboard.
        Objective objective = scoreboard.registerNewObjective("scoreboard", "", "DevCraft");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(org.bukkit.ChatColor.RED + "" + org.bukkit.ChatColor.BOLD + " -- DevCraft -- ");

        // Setting the different scores to text.
        Score s1 = objective.getScore("");
        Score s2 = objective.getScore(ChatColor.GOLD + "Name: " + ChatColor.GRAY + pname);
        Score s3 = objective.getScore(ChatColor.GOLD + "Rank: " + ChatColor.GRAY + "Default");
        Score s4 = objective.getScore(ChatColor.GOLD + "Level: " + ChatColor.GRAY + p.getLevel());
        Score s5 = objective.getScore(ChatColor.GOLD + "Jumps: " + ChatColor.GRAY + p.getStatistic(Statistic.JUMP));
        Score s6 = objective.getScore(" ");
        Score s7 = objective.getScore(ChatColor.GOLD + "Lobby: " + ChatColor.GRAY + " 1");
        Score s8 = objective.getScore(ChatColor.GOLD + "Players: " + ChatColor.GRAY + onlinePlayers);
        Score s9 = objective.getScore("  ");
        Score s10 = objective.getScore(ChatColor.GREEN + "play.devcraft.net");

        s1.setScore(10);
        s2.setScore(9);
        s3.setScore(8);
        s4.setScore(7);
        s5.setScore(6);
        s6.setScore(5);
        s7.setScore(4);
        s8.setScore(3);
        s9.setScore(2);
        s10.setScore(1);

        // Setting the scoreboard for the player.
        p.setScoreboard(scoreboard);

        return false;
    }
}
