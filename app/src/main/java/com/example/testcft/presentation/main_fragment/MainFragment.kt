package com.example.testcft.presentation.main_fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testcft.R
import com.example.testcft.data.local.database_people.OnItemClickListener
import com.example.testcft.data.local.database_people.PeopleEntity
import com.example.testcft.databinding.FragmentMainBinding
import com.example.testcft.presentation.main_fragment.adapter.AdapterPeople
import kotlinx.coroutines.launch


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    val viewModel:ViewModelMain by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
    val adapter = AdapterPeople(object : OnItemClickListener {
        override fun onItemClick(peopleEntity: PeopleEntity) {
            viewModel.peopleEntity = peopleEntity
            Log.d("MyLog", "Fragment ${viewModel.peopleEntity!!.lastName}")
            findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
        }

    })

    override fun onViewCreated(
        view: View, savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        val recyclerView = binding.RV
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Кнопка загрузки 10 пользователей
        binding.floatingActionButton2.setOnClickListener {

            lifecycleScope.launch {
               // saveAndGetAPI()
               // retrofitGetAPI()
            }

        }

//        // Кнопка очистки списка
//        binding.floatingActionButton3.setOnClickListener {
//
//            adapter.addList(emptyList())
//            adapter.notifyDataSetChanged()
//
//            lifecycleScope.launch {
//                peopleDao.deleteAllPeople()
//            }
//
//        }

    }
        override fun onDestroy(

    ) {
        super.onDestroy()
        _binding = null
    }
}