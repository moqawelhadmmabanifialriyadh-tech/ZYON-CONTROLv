package com.zyon.control

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.projection.MediaProjectionManager
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(36, 48, 36, 36)
        }

        val title = TextView(this).apply {
            text = "ZYON CONTROL\n\nلوحة التحكم"
            textSize = 24f
        }
        layout.addView(title)

        val battery = getSystemService(
            Context.BATTERY_SERVICE
        ) as BatteryManager

        val info = TextView(this).apply {
            text = """
                الجهاز: ${Build.MODEL}
                Android: ${Build.VERSION.RELEASE}
                البطارية: ${battery.getIntProperty(
                    BatteryManager.BATTERY_PROPERTY_CAPACITY
                )}%
            """.trimIndent()
            textSize = 18f
        }
        layout.addView(info)

        val screenButton = Button(this).apply {
            text = "السماح بمشاركة الشاشة"
            setOnClickListener {
                val manager = getSystemService(
                    Context.MEDIA_PROJECTION_SERVICE
                ) as MediaProjectionManager

                startActivityForResult(
                    manager.createScreenCaptureIntent(), 4101
                )
            }
        }
        layout.addView(screenButton)

        val accessButton = Button(this).apply {
            text = "إعداد إمكانية الوصول"
            setOnClickListener {
                startActivity(
                    Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                )
            }
        }
        layout.addView(accessButton)

        setContentView(layout)
    }
}
