package com.example.project7

class Balloon(val x : Int, val y : Int, val radius : Int) {
    private var popped: Boolean = false
    override fun toString(): String {
        return "Balloon(x=$x, y=$y, radius=$radius)"
    }
    fun pop() {
        popped = true
    }

    fun isPopped() : Boolean {
        return popped
    }

    fun isInsideBalloon(x1: Float, y1: Float) : Boolean {
        val dx = x1 - x
        val dy = y1 - y
        return dx * dx + dy * dy <= radius * radius
    }
}