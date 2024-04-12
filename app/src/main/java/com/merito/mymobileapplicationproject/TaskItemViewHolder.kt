package com.merito.mymobileapplicationproject

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.merito.mymobileapplicationproject.databinding.TaskiItemCellBinding
import java.time.format.DateTimeFormatter

class TaskItemViewHolder(
    private val contex: Context,
    private val binding: TaskiItemCellBinding,
    private val clickListener: TaskItemClickListener

) : RecyclerView.ViewHolder(binding.root) {
    val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
    fun bindTaskItem(taskItem: TaskItem) {
        binding.name.text = taskItem.name

        if (taskItem.isCompleted()) {
            binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.dueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

        }

        binding.completeButton.setImageResource(taskItem.imageResource())
        binding.completeButton.setColorFilter(taskItem.imageColor(contex))

        binding.completeButton.setOnClickListener {
            clickListener.completeTaskItem(taskItem)
        }

        binding.taskCellContainer.setOnClickListener {
            clickListener.editTaskItem(taskItem)
        }

        if (taskItem.dueTime() != null)
            binding.dueTime.text = timeFormat.format(taskItem.dueTime())
        else
            binding.dueTime.text = ""
    }
}