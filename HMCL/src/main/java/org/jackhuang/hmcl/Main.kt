/*
 * Hello Minecraft! Launcher.
 * Copyright (C) 2017  huangyuhui <huanghongxun2008@126.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see {http://www.gnu.org/licenses/}.
 */
package org.jackhuang.hmcl

import javafx.application.Application
import javafx.stage.Stage
import org.jackhuang.hmcl.ui.Controllers
import org.jackhuang.hmcl.util.DEFAULT_USER_AGENT
import org.jackhuang.hmcl.util.OS
import java.io.File

class MainApplication : Application() {

    override fun start(stage: Stage) {
        Controllers.initialize(stage)

        stage.isResizable = false
        stage.scene = Controllers.scene
        stage.show()
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            DEFAULT_USER_AGENT = "Hello Minecraft! Launcher"

            launch(MainApplication::class.java, *args)
        }

        fun getWorkingDirectory(folder: String): File {
            val userhome = System.getProperty("user.home", ".")
            return when (OS.CURRENT_OS) {
                OS.LINUX -> File(userhome, ".$folder/")
                OS.WINDOWS -> {
                    val appdata: String? = System.getenv("APPDATA")
                    File(appdata ?: userhome, ".$folder/")
                }
                OS.OSX -> File(userhome, "Library/Application Support/" + folder)
                else -> File(userhome, "$folder/")
            }
        }

        fun getMinecraftDirectory(): File = getWorkingDirectory("minecraft")
    }
}