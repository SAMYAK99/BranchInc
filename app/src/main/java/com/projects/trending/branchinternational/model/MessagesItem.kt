package com.projects.trending.branchinternational.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MessagesItem(
    @SerializedName("agent_id")
    val agentId: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("thread_id")
    val threadId: Int,
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("user_id")
    val userId: String
): Serializable {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}