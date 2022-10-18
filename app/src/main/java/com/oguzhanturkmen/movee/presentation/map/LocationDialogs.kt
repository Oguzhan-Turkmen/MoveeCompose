package com.oguzhanturkmen.movee.presentation.map

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.IntentSender
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import com.oguzhanturkmen.movee.presentation.map.LocationUtilsViewModel.isLocationPermissionGranted

@Composable
fun LocationPermissionsDialog(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit,
) {
    val requestLocationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->

        if (isGranted) {
            onPermissionGranted()
        } else {
            onPermissionDenied()
        }
    }
    SideEffect {
        requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
}

@Composable
fun LocationPermissionsAndSettingDialogs(
    updateCurrentLocation: () -> Unit,
) {
    var requestLocationSetting by remember { mutableStateOf(false) }

    if (isLocationPermissionGranted(LocalContext.current)) {
        SideEffect {
            requestLocationSetting = true
        }
    } else {
        LocationPermissionsDialog(
            onPermissionGranted = {
                requestLocationSetting = true
            },
            onPermissionDenied = {
                requestLocationSetting = false
            },
        )
    }

    if (requestLocationSetting) {
        LocationSettingDialog(
            onSuccess = {
                requestLocationSetting = false
                updateCurrentLocation()
            },
            onFailure = {
                requestLocationSetting = false
                updateCurrentLocation()
            },
        )
    }
}

@Composable
fun LocationSettingDialog(
    onSuccess: () -> Unit,
    onFailure: () -> Unit,
) {
    val context: Context = LocalContext.current

    val enableLocationSettingLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { activityResult ->
        if (activityResult.resultCode == Activity.RESULT_OK)
            onSuccess()
        else {
            onFailure()
        }
    }

    val locationRequest = LocationRequest.create().apply {
        priority = Priority.PRIORITY_HIGH_ACCURACY
    }
    val locationRequestBuilder = LocationSettingsRequest.Builder()
        .addLocationRequest(locationRequest)
    val locationSettingsResponseTask = LocationServices.getSettingsClient(context)
        .checkLocationSettings(locationRequestBuilder.build())

    locationSettingsResponseTask.addOnSuccessListener {
        onSuccess()
    }

    locationSettingsResponseTask.addOnFailureListener { exception ->
        if (exception is ResolvableApiException) {
            try {
                val intentSenderRequest =
                    IntentSenderRequest.Builder(exception.resolution).build()
                enableLocationSettingLauncher.launch(intentSenderRequest)

            } catch (sendEx: IntentSender.SendIntentException) {
                sendEx.printStackTrace()
            }
        } else {
            onFailure()
        }
    }
}