package com.example.testcft.presentation.main_fragment.adapter

import OnItemClickListener
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testcft.R
import com.example.testcft.databinding.ItemPeopleBinding
import com.example.testcft.domain.model.User

class AdapterPeople(private val listener: OnItemClickListener): RecyclerView.Adapter<AdapterPeople.MyViewHolder>()  {

    private var itemList = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemPeopleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding, listener) // Передаем listener в ViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position],holder)
    }

    override fun getItemCount(): Int = itemList.size

    fun addList(item: List<User>) {
        this.itemList = item
        notifyDataSetChanged()
    }

    class MyViewHolder(private val binding: ItemPeopleBinding, private val listener: OnItemClickListener
    ): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(user: User, holder: MyViewHolder) {
            binding.apply {

                 textfirstname1.text = user.firstName
                 middlenametext1.text = user.titleName
                 lastnametext1.text = user.lastName

                numberphonetext1.text = user.numberPhone
                addresstext1.text = "shirota: ${user.latitude}  golgota:${user.longitude}"

                if (user.photo != null) {
                    val bitmap = BitmapFactory.decodeByteArray(user.photo, 0, user.photo!!.size)
                    photo1.setImageBitmap(bitmap)
                } else {
                    // если фото нет — дефолтное
                    photo1.setImageResource(R.drawable.ic_launcher_foreground)
                }

                 cardView.setOnClickListener()
                 {
                    listener.onItemClick(user)
                 }
            }
        }

    }
}