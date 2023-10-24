package com.example.recipesearch.ui.utils.nav

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route: String){
    object SaveScreen: Screen("save")
    object SearchScreen: Screen("search")
    object WebScreen: Screen("web")

    fun withArgs(vararg args: String):String{
        return buildString {
            append(route)
            args.forEach{ arg->
                append("/$arg")
            }
        }
    }
}
fun String.encodeUrl() = URLEncoder.encode(this, StandardCharsets.UTF_8.toString())
fun String.decodeUrl() = URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
