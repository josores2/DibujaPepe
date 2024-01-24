package com.josesorli.dibuja3

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DrawingView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val paint = Paint()
    private val path = Path()

    init {
        paint.color = Color.BLACK
        paint.isAntiAlias = true
        paint.strokeWidth = 10f
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas?.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(event.x, event.y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(event.x, event.y)
                invalidate()
            }
        }
        return false
    }

    fun clearDrawing() {
        path.reset()
        invalidate()
    }
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawingView = findViewById<DrawingView>(R.id.drawingView)

        // Example: Clear the drawing when a button is clicked
        val clearButton = findViewById<Button>(R.id.clearButton)
        clearButton.setOnClickListener {
            drawingView.clearDrawing()
        }
    }
}
