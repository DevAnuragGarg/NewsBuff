package com.intact.newsbuff.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.intact.newsbuff.R
import timber.log.Timber
import kotlin.math.pow

/**
 * as on Draw methods can be called so many times layout change, orientation change,
 *  some change in the layout, we should not allocate any object memory. So created init
 *  method we initialize these values
 *
 *  ANTI ALIAS FLAG: It will not show zig zag as per the pixel, little blurred but user friendly
 *
 *  invalidate vs postValidate: f you want to re-draw your view from the UI thread you can call
 *  invalidate() method. If you want to re-draw your view from a non-UI thread you can call
 *  postInvalidate() method. The postInvalidate method notifies the system from a non-UI thread and
 *  the view gets redrawn in the next event loop on the UI thread as soon as possible.
 *
 *  when the user touches the View it starts a flow of touch event which is wrapped up as a
 *  MotionEvent object. This object has information about touch location, event action, number of
 *  pointers, event time, pressure etc. onTouchEvent()returns true for any touch event, then the
 *  flow stops there. Override onTouchEvent() and return true inside our custom view.
 *
 *  A “gesture” begins with ACTION_DOWN and ends with ACTION_UP.
 *  ACTION_DOWN: This gets called when the user first time places its finger on the screen. x, y
 *  are the touch coordinates for the same. This is the starting point of a gesture
 *  ACTION_MOVE: This gets called when the user starts moving his finger on the screen.This will be
 *  called multiple times giving you latest x, y coordinates. We won’t be needing it.
 *  ACTION_UP: This is fired when the user removes his finger from the screen. Coordinates x, y will
 *  give you location from where he removed his finger. This is the end point of a gesture.
 */

class CustomView(
    context: Context,
    attributes: AttributeSet? = null
) : View(context, attributes) {

    var squareSize: Float = 0F
    private var squareColor: Int
    private var circleColor: Int
    private var circleX: Float = 0F
    private var circleY: Float = 0F
    private var rect: Rect = Rect()
    private var circleRadius: Float = 150F
    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var paintCircle: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        val typedArray = context.obtainStyledAttributes(attributes, R.styleable.CustomView)
        squareColor = typedArray.getColor(R.styleable.CustomView_square_color, Color.BLACK)
        circleColor = typedArray.getColor(R.styleable.CustomView_circle_color, Color.BLACK)
        squareSize = typedArray.getDimension(
            R.styleable.CustomView_square_size,
            resources.getDimension(R.dimen.dimen_240)
        )

        // always recycle these values
        typedArray.recycle()
        paint.color = squareColor
        paintCircle.color = circleColor
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            it.drawColor(Color.RED)

            // circle center
            if (circleX == 0F || circleY == 0F) {
                circleX = width / 2.toFloat()
                circleY = height / 2.toFloat()
            }

            // creating a rectangle
            //rect.left = 10
            //rect.top = 10
            //rect.right = rect.left + squareSize.toInt()
            //rect.bottom = rect.top + squareSize.toInt()

            // creating a circle
            it.drawCircle(circleX, circleY, circleRadius, paintCircle)

            //it.drawRect(rect, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var value = super.onTouchEvent(event)

        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                value = true
            }
            MotionEvent.ACTION_MOVE -> {
                val x = event.x
                val y = event.y

                // to detect if the touch is inside our circle
                val dx = (x - circleX.toDouble()).pow(2.toDouble())
                val dy = (x - circleY.toDouble()).pow(2.toDouble())

                if (dx + dy < circleRadius.toDouble().pow(2.toDouble())) {
                    Timber.d("Circle: Inside")
                    // Touched
                    circleX = x
                    circleY = y
                    postInvalidate()
                    return true
                } else {
                    Timber.d("Circle: Outside")
                }
            }
        }

        return value
    }
}