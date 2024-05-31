package ai.opensource.emago.navigation

import ai.opensource.emago.screens.chat.ChatCreateScreen
import ai.opensource.emago.screens.chat.ChatListScreen
import ai.opensource.emago.screens.chat.SingleChatScreen
import ai.opensource.emago.screens.home.HomeScreen
import ai.opensource.emago.screens.home.ReviewScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun ChatNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "chatList"){
        composable("chatList"){ ChatListScreen(navController) }
        composable("chatCreate") { ChatCreateScreen(navController) }
        composable("chat/{chatId}") { backstackEntry ->
            val chatId = backstackEntry.arguments?.getString("chatId")
            if(chatId != null){
                SingleChatScreen(navController, chatId)
            }
        }
//        composable(DestinationScreen.SingleChat.route) {
//            val chatId = it.arguments?.getString("chatId")
//            chatId?.let {
//                SingleChatScreen(navController, vm, chatId)
//            }
//        }
        //composable(){}
    }
}