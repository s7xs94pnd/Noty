package com.example.noty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemLongClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noty.data.models.NotyModel
import com.example.noty.databinding.ItemNotyBinding
import com.example.noty.ui.OnItemClick

class RvNotyAdapter(private val itemLongClick: OnItemClick,private val itemClick: OnItemClick) : ListAdapter<NotyModel, RvNotyAdapter.NotyViewHolder>(DiffCallBack()) {

//DiffCallBack() — это объект, который передаётся в ListAdapter для сравнения элементов списка.

    inner class NotyViewHolder(private val binding: ItemNotyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: NotyModel) = with(binding) {
            tvTitle.text = item.title
            tvDesc.text = item.description
            tvTime.text = item.time
        }
    }

    /*Создаёт объект LayoutInflater, который отвечает за превращение XML-макета
    (например, item_noty.xml) в реальный объект View, который можно отобразить на экране.
    Контекст (parent.context) нужен, чтобы LayoutInflater знал, в каком окружении он работает (например, какой стиль или тема у приложения).
    Здесь parent — это родительский контейнер, переданный RecyclerView, и он даёт правильный контекст.*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotyViewHolder {
        return NotyViewHolder(
            ItemNotyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    /*RecyclerView вызывает onBindViewHolder, передавая holder и position.
    Адаптер берёт данные для позиции (getItem(position)).
	Данные передаются в holder.onBind, который обновляет содержимое интерфейса для элемента списка.*/

    override fun onBindViewHolder(holder: NotyViewHolder, position: Int) {
        holder.onBind(getItem(position))
        holder.itemView.setOnClickListener{
            itemClick.onClick(getItem(position))
        }
        holder.itemView.setOnLongClickListener{
            itemLongClick.onLongClick(getItem(position))
            // TODO: зачем тут нужно тру
            true
        }
    }

/*	RecyclerView с помощью DiffUtil вычисляет разницу между старым и новым списками.
	Это позволяет обновлять только те элементы, которые изменились, вместо перерисовки всего списка.
	*/
    class DiffCallBack : DiffUtil.ItemCallback<NotyModel>() {
        override fun areItemsTheSame(oldItem: NotyModel, newItem: NotyModel): Boolean {
            return oldItem.id == newItem.id
        }
/*DiffCallBack нужен, чтобы сравнивать элементы списка и обновлять только те, которые изменились.
Он оптимизирует работу RecyclerView и делает обновления быстрыми и плавными.*/
        override fun areContentsTheSame(oldItem: NotyModel, newItem: NotyModel): Boolean {
            return oldItem == newItem
        }
    }
}
