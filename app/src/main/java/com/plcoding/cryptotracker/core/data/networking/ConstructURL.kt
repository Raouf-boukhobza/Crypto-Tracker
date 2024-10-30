package com.plcoding.cryptotracker.core.data.networking

import com.plcoding.cryptotracker.BuildConfig


fun constructURL(url : String) : String {
    return when {
        url.contains(BuildConfig.Base_Url) -> url
        url.startsWith("/") -> BuildConfig.Base_Url + url.drop(1)
        else -> BuildConfig.Base_Url + url
    }
}