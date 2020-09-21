/**
 * Copyright (C) 2016 Dr. David H. Akehurst (http://dr.david.h.akehurst.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.akehurst.games.pong.application.common

import net.akehurst.games.pong.computational.game.Game

import kotlin.time.ExperimentalTime

@ExperimentalTime
class Application(
        userControls: UserControlsKind
) {

    enum class UserControlsKind { KEYS, PAD }

    // computational
    val game = Game()

    // engineering



    // technology

    init {
        game.userNotification = gui
        gui.userRequest = game
    }

    fun start() {
        gui.start()
        game.start()
    }
}