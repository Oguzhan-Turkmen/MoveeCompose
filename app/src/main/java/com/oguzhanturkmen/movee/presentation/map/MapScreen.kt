package com.oguzhanturkmen.movee.presentation.map

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.*
import com.oguzhanturkmen.movee.presentation.map.LocationUtilsViewModel.getDefaultLocation
import com.oguzhanturkmen.movee.presentation.map.LocationUtilsViewModel.getPosition
import com.oguzhanturkmen.movee.presentation.map.LocationUtilsViewModel.requestLocationResultCallback

private const val TAG = "LocationTrackActivity"

/*
@Composable
fun MapScreen(mapViewModel: MapViewModel = hiltViewModel()){
    var isMapLoaded by remember { mutableStateOf(false) }

    val cameraPositionState = rememberCameraPositionState {
        position = mapViewModel.defaultCameraPosition
    }

    val mapProperties by remember { mutableStateOf(MapProperties(isMyLocationEnabled = true)) }

    val locationState = mapViewModel.locationFlow.collectAsState(initial = mapViewModel.newLocation())

    LaunchedEffect(locationState.value) {
        Log.d(TAG, "Updating blue dot on map...")
        mapViewModel.locationSource.onLocationChanged(locationState.value)

        Log.d(TAG, "Updating camera position...")
        val cameraPosition = CameraPosition.fromLatLngZoom(LatLng(locationState.value.latitude, locationState.value.longitude), ZOOM)
        cameraPositionState.animate(CameraUpdateFactory.newCameraPosition(cameraPosition), 1_000)
    }

    LaunchedEffect(cameraPositionState.isMoving) {
        if (cameraPositionState.isMoving) {
            Log.d(TAG, "Map camera started moving due to ${cameraPositionState.cameraMoveStartedReason.name}")
        }
    }
    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
            onMapLoaded = {
                isMapLoaded = true
            },
            locationSource = mapViewModel.locationSource,
            properties = mapProperties
        )
        if (!isMapLoaded) {
            AnimatedVisibility(
                modifier = Modifier
                    .matchParentSize(),
                visible = !isMapLoaded,
                enter = EnterTransition.None,
                exit = fadeOut()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .background(MaterialTheme.colors.background)
                        .wrapContentSize()
                )
            }
        }
    }
}
*/

@SuppressLint("MissingPermission")
@Composable
fun MapScreen(
) {
    val context = LocalContext.current
    val activity = context.findActivity()
    val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
    var currentLocation by remember { mutableStateOf(getDefaultLocation()) }
    val cameraPositionState = rememberCameraPositionState()
    var requestLocationUpdate by remember { mutableStateOf(true) }
    cameraPositionState.position = CameraPosition.fromLatLngZoom(
        getPosition(currentLocation), 12f
    )
    MyGoogleMap(cameraPositionState)
    if (requestLocationUpdate) {
        LocationPermissionsAndSettingDialogs(
            updateCurrentLocation = {
                requestLocationUpdate = false
                requestLocationResultCallback(fusedLocationProviderClient) { locationResult ->
                    locationResult.lastLocation?.let { location ->
                        currentLocation = location
                    }
                }
            }
        )
    }
}

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}

@Composable
fun MyGoogleMap(
    cameraPositionState: CameraPositionState,
) {
    val mapProperties by remember { mutableStateOf(MapProperties(isMyLocationEnabled = true)) }
    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(zoomControlsEnabled = true, mapToolbarEnabled = true)
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = mapUiSettings,
            properties = mapProperties
        )
    }

}
/*

private val singapore = LatLng(1.35, 103.87)
private val singapore2 = LatLng(2.50, 103.87)
private val TAG2 = "assads"

@Composable
fun GoogleMapClustering() {
    val items = remember { mutableStateListOf<MyItem>() }
    LaunchedEffect(Unit) {
        for (i in 1..10) {
            val position = LatLng(
                singapore2.latitude + Random.nextFloat(),
                singapore2.longitude + Random.nextFloat(),
            )
            items.add(MyItem(position, "Marker", "Snippet"))
        }
    }
    GoogleMapClustering(items = items)
}

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun GoogleMapClustering(items: List<MyItem>) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        val context = LocalContext.current
        var clusterManager by remember { mutableStateOf<ClusterManager<MyItem>?>(null) }
        MapEffect(items) { map ->
            if (clusterManager == null) {
                clusterManager = ClusterManager<MyItem>(context, map)
            }
            clusterManager?.addItems(items)
        }
        LaunchedEffect(key1 = cameraPositionState.isMoving) {
            if (!cameraPositionState.isMoving) {
                clusterManager?.onCameraIdle()
            }
        }
        MarkerInfoWindow(
            state = rememberMarkerState(position = singapore),
            onClick = {
                // This won't work :(
                Log.d(TAG2, "I cannot be clicked :( $it")
                true
            }
        )
    }
}
data class MyItem(
    val itemPosition: LatLng,
    val itemTitle: String,
    val itemSnippet: String,
) : ClusterItem {
    override fun getPosition(): LatLng =
        itemPosition

    override fun getTitle(): String =
        itemTitle

    override fun getSnippet(): String =
        itemSnippet
}
*/





