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

package net.akehurst.games.pong.computational.game

import net.akehurst.games.pong.computational.user.api.UserNotification
import net.akehurst.games.pong.computational.user.api.UserRequest
import net.akehurst.games.pong.information.BatIdentity
import net.akehurst.games.pong.information.BatPosition
import net.akehurst.games.pong.information.ScreenSize

class Game : UserRequest {

    lateinit var userNotification: UserNotification

    private lateinit var userScreenSize:ScreenSize
    private lateinit var opponentScreenSize:ScreenSize

    // --- UserRequest ---

    override fun requestStartGame(screenSize: ScreenSize) {
        userNotification.notifyWaitingForOpponent()
    }

    override fun requestQuitGame() {
        TODO("not implemented")
    }

    override fun requestMoveLeft() {
        TODO("not implemented")
    }

    override fun requestMoveRight() {
        TODO("not implemented")
    }

}