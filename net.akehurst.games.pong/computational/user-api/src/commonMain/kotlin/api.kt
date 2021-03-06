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

package net.akehurst.games.pong.computational.user.api

import net.akehurst.games.pong.information.*

/**
 * Something the user requests of the application, via the GUI
 */
interface UserRequest {

    fun requestStartGame(screenSize: ScreenSize)
    fun requestQuitGame()

    fun requestMoveLeft()
    fun requestMoveRight()

}

/**
 * Something the application notifies the user about
 */
interface UserNotification {

    fun notifyWaitingForOpponent()

    fun notifyGameEnded(result: GameResult)
    fun notifyGameQuit()

    fun notifyBatPosition(batId: BatIdentity, position: BatPosition)

    fun notifyBallVelocity(velocity:BallVelocity)

}