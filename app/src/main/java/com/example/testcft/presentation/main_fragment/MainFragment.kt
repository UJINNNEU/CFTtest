package com.example.testcft.presentation.main_fragment

import OnItemClickListener
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testcft.App
import com.example.testcft.R
import com.example.testcft.databinding.FragmentMainBinding
import com.example.testcft.presentation.ViewModelUserParser
import com.example.testcft.presentation.main_fragment.adapter.AdapterPeople
import com.example.testcft.data.repository.UserRepositoryImpl
import com.example.testcft.domain.model.User
import com.example.testcft.domain.usecases.RefreshUsersUseCase
import com.example.testcft.presentation.main_fragment.ViewModelFactory.ViewModelMainFactory
import kotlinx.coroutines.launch


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    val viewModelUserParser: ViewModelUserParser by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
    val adapter = AdapterPeople(
        object : OnItemClickListener {
        override fun onItemClick(
            user: User
        ) {
            viewModelUserParser.user = user
           // Log.d("MyLog", "Fragment ${viewModelUserParser.peopleEntity!!.lastName}")
            findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
        }

    })
    lateinit var userRepositoryImpl:UserRepositoryImpl
    lateinit var refreshUsersUseCase: RefreshUsersUseCase
    private lateinit var viewModelMain: ViewModelMain

    override fun onViewCreated(
        view: View, savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        val recyclerView = binding.RV
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        userRepositoryImpl = UserRepositoryImpl(requireActivity().application as App)
        refreshUsersUseCase = RefreshUsersUseCase(userRepositoryImpl)

        val factory = ViewModelMainFactory(refreshUsersUseCase)
        viewModelMain = ViewModelProvider(requireActivity(), factory)[ViewModelMain::class.java]

        viewModelMain = ViewModelMain(refreshUsersUseCase)

        viewModelMain.users.observe(viewLifecycleOwner){ userList ->
            adapter.addList(userList)

        }
    }

    override fun onResume() {
        super.onResume()

        // Кнопка загрузки 10 пользователей
        binding.UpdateButton.setOnClickListener {
            Log.d("MyLog","button click")
            lifecycleScope.launch{
                Log.d("MyLog","scope on")
                viewModelMain.getPeopleList()
                Log.d("MyLog","${viewModelMain.users.value?.size}")
            }
        }

        // Кнопка очистки списка
        binding.DeleteButton.setOnClickListener {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}