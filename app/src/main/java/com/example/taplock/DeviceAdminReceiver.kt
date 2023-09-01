package com.example.taplock

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class DeviceAdminReceiver : DeviceAdminReceiver() {

    override fun onEnabled(context: Context, intent: Intent) {
        // Called when device admin is enabled
        Toast.makeText(context,"Admin Enabled", Toast.LENGTH_SHORT).show()
    }

    override fun onDisabled(context: Context, intent: Intent) {
        // Called when device admin is disabled
        Toast.makeText(context,"Admin Disabled", Toast.LENGTH_SHORT).show()
    }
}
