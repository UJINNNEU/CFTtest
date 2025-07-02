package com.example.testcft.main_fragment

import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testcft.R
import com.example.testcft.database_people.OnItemClickListener
import com.example.testcft.database_people.PeopleEntity
import com.example.testcft.databinding.ItemPeopleBinding

class AdapterPeople(private val listener: OnItemClickListener): RecyclerView.Adapter<AdapterPeople.MyViewHolder>()  {

    private var itemList = emptyList<PeopleEntity>()

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

    fun addList(item: List<PeopleEntity>) {
        this.itemList = item
        notifyDataSetChanged()
    }

    class MyViewHolder(private val binding: ItemPeopleBinding, private val listener:OnItemClickListener
    ):RecyclerView.ViewHolder(binding.root)
    {
        fun bind(peopleEntity: PeopleEntity,holder: MyViewHolder) {
            binding.apply {

                 textfirstname1.text = peopleEntity.firstName
                 middlenametext1.text = peopleEntity.middleName
                 lastnametext1.text = peopleEntity.lastName

                numberphonetext1.text = peopleEntity.numberPhone
                addresstext1.text = "shirota: ${peopleEntity.latitude}  golgota:${peopleEntity.longitude}"

                if (peopleEntity.photo != null) {
                    val bitmap = BitmapFactory.decodeByteArray(peopleEntity.photo, 0, peopleEntity.photo!!.size)
                    photo1.setImageBitmap(bitmap)
                } else {
                    // если фото нет — дефолтное
                    photo1.setImageResource(R.drawable.ic_launcher_foreground)
                }

                 cardView.setOnClickListener()
                 {
                    listener.onItemClick(peopleEntity)
                 }
            }
        }

    }
}