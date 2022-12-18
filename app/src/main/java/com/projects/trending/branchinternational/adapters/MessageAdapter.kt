package com.projects.trending.branchinternational.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.projects.trending.branchinternational.R
import com.projects.trending.branchinternational.databinding.ItemMessageBinding
import com.projects.trending.branchinternational.model.MessagesItem
import com.projects.trending.branchinternational.view.ChatActivity
import java.util.*

class MessageAdapter(val mList: List<MessagesItem>, val context: Context) :
    RecyclerView.Adapter<MessageAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = ItemMessageBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_message, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = mList[position]
        holder.binding.apply {
            tvId.text = item.userId

            val time = item.timestamp.substring(0, 10) + "  " + item.timestamp.substring(14, 21)

            tvTime.text = time
            tvMessages.text = item.body

            if (item.agentId != null) {
                holder.binding.messageItem.setBackgroundColor(Color.parseColor("#2ecc71"))
                holder.binding.agent.visibility = View.VISIBLE
                tvAgent.text = item.agentId
            }

            messageItem.setOnClickListener {
                val list: ArrayList<MessagesItem> = ArrayList()
                val intent = Intent(context, ChatActivity::class.java)
                val threadId = item.threadId.toString()

                for (item in mList) {
                    if (item.threadId.toString() == threadId)
                        list.add(item)
                }
                intent.putExtra("thread", threadId)
                intent.putExtra("data", list)
                context.startActivity(intent)
            }
        }


    }

    override fun getItemCount(): Int {
        return mList.size
    }
}