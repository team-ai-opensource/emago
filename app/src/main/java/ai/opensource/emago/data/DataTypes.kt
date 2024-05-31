package ai.opensource.emago.data

data class UserData(

    var userId: String?="",
    var name: String?="",
    var number: String?="",
    var profileImageUrl: String?="",
) {
    fun toMap() = mapOf(
        "userId" to userId,
        "name" to name,
        "number" to number,
        "profileImageUrl" to profileImageUrl
    )
}

data class ChatData(
    var id: String = "", // 문서 ID를 저장할 필드
    val userId: String?="",
    val title: String?="Null채팅방",
    val description: String?="설명없음"
)

data class ChatUser(
    val userId: String?="",
    val name: String?="",
    val imageUrl: String?="",
    val number: String?="",
)

data class Feedback(
    val comment: String? ="",
    val advanced_sentence: String? ="",
    val error_sentence: String? ="",
    val correct_sentence: String? =""
)

data class Message(
    val chatId: String?="",
    val user: ChatUser=ChatUser(),
    val timestamp: String?="",
    val message: String?="",
    var feedback: Feedback? = null,
)