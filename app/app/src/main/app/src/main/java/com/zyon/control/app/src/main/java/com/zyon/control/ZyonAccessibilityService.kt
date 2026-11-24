package com.zyon.control

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.view.accessibility.AccessibilityEvent

class ZyonAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(
        event: AccessibilityEvent?
    ) {
        // لا يجمع محتوى الشاشة أو ضغطات المفاتيح
    }

    override fun onInterrupt() {
    }

    fun tap(x: Float, y: Float) {
        val path = Path().apply {
            moveTo(x, y)
        }

        val gesture = GestureDescription.Builder()
            .addStroke(
                GestureDescription.StrokeDescription(
                    path, 0, 100
                )
            )
            .build()

        dispatchGesture(gesture, null, null)
    }

    fun swipe(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float
    ) {
        val path = Path().apply {
            moveTo(x1, y1)
            lineTo(x2, y2)
        }

        val gesture = GestureDescription.Builder()
            .addStroke(
                GestureDescription.StrokeDescription(
                    path, 0, 350
                )
            )
            .build()

        dispatchGesture(gesture, null, null)
    }
}
