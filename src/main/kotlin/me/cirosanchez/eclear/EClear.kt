package me.cirosanchez.eclear

import me.cirosanchez.eclear.EClearCommand
import org.bukkit.plugin.java.JavaPlugin
import revxrsal.commands.bukkit.BukkitCommandHandler



class EClear : JavaPlugin() {

    companion object{
        lateinit var instance: EClear
    }
    override fun onEnable() {
        instance = this

        saveDefaultConfig()

        getCommand("eclear").setExecutor(EClearCommand())
    }
}