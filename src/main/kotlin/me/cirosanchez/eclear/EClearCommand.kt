package me.cirosanchez.eclear

import me.cirosanchez.eclear.EClear
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.command.CommandException
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player
import revxrsal.commands.annotation.Command
import revxrsal.commands.command.CommandActor
import java.io.Console
import java.lang.Exception

class EClearCommand : CommandExecutor {

    override fun onCommand(
        sender: CommandSender?,
        command: org.bukkit.command.Command?,
        label: String?,
        args: Array<out String>?
    ): Boolean {
        if (sender is ConsoleCommandSender){
            if (args == null){
                BukkitAudiences.create(EClear.instance).console().sendMessage(
                    MiniMessage.miniMessage().deserialize(EClear.instance.config.getString("syntax"))
                )
                return true;
            }
            if (args.size == 0){
                BukkitAudiences.create(EClear.instance).console().sendMessage(
                    MiniMessage.miniMessage().deserialize(EClear.instance.config.getString("player"))
                )
                return true;
            }

            var arg: String = args!![0]

            var target: Player? = Bukkit.getPlayer(arg)

            if (target == null){
                var msg: String = EClear.instance.config.getString("no-player")
                msg = msg.replace("%player%", arg)
                BukkitAudiences.create(EClear.instance).console().sendMessage(
                    MiniMessage.miniMessage().deserialize(msg)
                )
                return true
            }

            target?.enderChest!!.clear()

            var msg: String = EClear.instance.config.getString("clear")
            msg = msg.replace("%player%", target.name)
            BukkitAudiences.create(EClear.instance).console().sendMessage(
                MiniMessage.miniMessage().deserialize(msg)
            )
        }
        if (sender is Player){
            val player: Player = sender
            if (args == null){
                BukkitAudiences.create(EClear.instance).player(player).sendMessage(
                    MiniMessage.miniMessage().deserialize(EClear.instance.config.getString("syntax"))
                )
                return true;
            }
            if (args.size == 0){
                BukkitAudiences.create(EClear.instance).player(player).sendMessage(
                    MiniMessage.miniMessage().deserialize(EClear.instance.config.getString("player"))
                )
                return true;
            }
            if (!player.hasPermission("eclear.command")){
                BukkitAudiences.create(EClear.instance).player(player).sendMessage(
                    MiniMessage.miniMessage().deserialize(EClear.instance.config.getString("no-permission"))
                )
                return true;
            }


            val arg: String = args[0]

            var target: Player? = Bukkit.getPlayer(arg)

            if (target == null){
                var msg: String = EClear.instance.config.getString("no-player")
                msg = msg.replace("%player%", arg)
                BukkitAudiences.create(EClear.instance).player(player).sendMessage(
                    MiniMessage.miniMessage().deserialize(msg)
                )
                return true
            }

            var msg: String = EClear.instance.config.getString("clear")
            msg = msg.replace("%player%", target.name)
            BukkitAudiences.create(EClear.instance).player(player).sendMessage(
                MiniMessage.miniMessage().deserialize(msg)
            )

        }
        return true;
    }
}