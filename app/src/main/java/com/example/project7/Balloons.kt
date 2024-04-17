package com.example.project7

class Balloons(private var balloons: List<Balloon>) {

    fun allPopped(): Boolean {
        return balloons.all { it.isPopped() }
    }

    fun getBalloons(): List<Balloon> {
        return balloons
    }

    fun popBalloon(x: Float, y: Float): Boolean {
        for (balloon in balloons) {
            if (!balloon.isPopped() && balloon.isInsideBalloon(x, y)) {
                balloon.pop()
                balloons = balloons.filter { !it.isPopped() }
                return true
            }
        }
        return false
    }
}