package com.example.project7


import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class GameView(context: Context) : View(context) {
    private val balloonColor = Color.parseColor("#CCCCFF")
    private val paint = Paint()
    private var balloons: Balloons? = null
    private var attempts: Int = 0
    private var gameWon: Boolean = false

    fun setBalloons(balloons: Balloons, ) {
        this.balloons = balloons
        this.attempts = balloons.getBalloons().size + 3
        postInvalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        balloons?.let { balloons ->
            for (balloon in balloons.getBalloons()) {
                paint.color = balloonColor
                canvas.drawCircle(balloon.x.toFloat(), balloon.y.toFloat(), balloon.radius.toFloat(), paint)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let { motionEvent ->
            if (!gameWon && motionEvent.action == MotionEvent.ACTION_DOWN && attempts > 0) {
                val clickedX = motionEvent.x
                val clickedY = motionEvent.y

                balloons?.let { balloons ->
                    if (balloons.popBalloon(clickedX, clickedY)) {
                        attempts--
                        Log.w("GameView", "Attempts left: $attempts")
                        postInvalidate()
                    } else {
                        attempts--
                        Log.w("GameView", "Attempts left: $attempts")
                        postInvalidate()
                    }

                    if (balloons.allPopped()) {
                        gameWon = true
                        gameWon()
                    } else if (attempts <= 0) {
                        gameOver()
                    }
                }

                postInvalidate()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    private fun gameWon() {
        Toast.makeText(context, "YOU WON", Toast.LENGTH_SHORT).show()
    }

    private fun gameOver() {
        Toast.makeText(context, "GAME OVER", Toast.LENGTH_SHORT).show()
        (context as? Activity)?.finish()
    }
}