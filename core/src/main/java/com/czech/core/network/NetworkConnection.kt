package com.czech.core.network

import android.annotation.TargetApi
import android.content.Context
import android.net.*
import android.os.Build
import androidx.lifecycle.LiveData

class NetworkConnection(val context: Context) : LiveData<Boolean>() {
    private var connectionManger: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    private fun networkConnectionCallback(): ConnectivityManager.NetworkCallback {
        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onLost(network: Network) {
                super.onLost(network)
                postValue(false)
            }

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                postValue(true)
            }
        }
        return networkCallback

    }

    override fun onActive() {
        super.onActive()
        updateConnection()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                connectionManger.registerDefaultNetworkCallback(networkConnectionCallback())
            }
            else -> {
                lollipopNetworkRequest()
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun lollipopNetworkRequest() {
        val requestBuilder = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        connectionManger.registerNetworkCallback(
            requestBuilder.build(),
            networkConnectionCallback()
        )
    }

    private fun updateConnection() {
        val activeNetwork: NetworkInfo? = connectionManger.activeNetworkInfo
        postValue((activeNetwork?.isConnected == true))
    }
}