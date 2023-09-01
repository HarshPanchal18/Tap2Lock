package com.example.taplock

import android.app.Activity
import android.app.admin.DeviceAdminReceiver
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var devicePolicyManager: DevicePolicyManager
    private lateinit var componentName: ComponentName

    companion object {
        const val REQUEST_ENABLE_DEVICE_ADMIN = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // get the instances
        devicePolicyManager =
            getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        componentName = ComponentName(this, DeviceAdminReceiver::class.java)

        // Check if the app has been granted device admin permission
        if (devicePolicyManager.isAdminActive(componentName)) {
            // Ask the user to add a new device administrator to the system
            val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
            // Component name of the administrator component
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName)
            // An optional CharSequence providing additional explanation for why the admin is being added
            intent.putExtra(
                DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                "Enable device admin permission to lock the screen"
            )
            startActivityForResult(intent, REQUEST_ENABLE_DEVICE_ADMIN)
        } else {
            lockScreen()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ENABLE_DEVICE_ADMIN) {
            if (resultCode == Activity.RESULT_OK) {
                lockScreen() // Permission Granted, lock the screen
            } else {
                finish() // Permission Denied, Exit
            }
        }
    }

    private fun lockScreen() {
        devicePolicyManager.lockNow()
        finish()
    }
}
