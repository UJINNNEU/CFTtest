package com.example.testcft.main_fragment

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.testcft.R
import com.example.testcft.database_people.DatabaseDao
import com.example.testcft.database_people.OnItemClickListener
import com.example.testcft.database_people.PeopleDataBase
import com.example.testcft.database_people.PeopleEntity
import com.example.testcft.databinding.FragmentMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.net.URL
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var db:PeopleDataBase
    private lateinit var peopleDao: DatabaseDao


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = PeopleDataBase.getDataBase(requireContext())
        peopleDao = db.DatabaseDao()

        lifecycleScope.launch {
            Log.d("MyLog","${peopleDao.getPeopleCount()}")
        }

    }
    val viewModel:ViewModelMain by activityViewModels()

    val adapter = AdapterPeople(object : OnItemClickListener {
        override fun onItemClick(peopleEntity: PeopleEntity) {
            viewModel.peopleEntity = peopleEntity
            Log.d("MyLog","Fragment ${viewModel.peopleEntity!!.lastName}")
            findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
        }

    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)



        val recyclerView = binding.RV
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            val listPeopleFirst = peopleDao.getAllPeople()
            if(listPeopleFirst.first().isNotEmpty())
            {
               adapter.addList(listPeopleFirst.first())
            }
            else
            {
              saveAndGetAPI()
            }

        }

        // Кнопка загрузки 10 пользователей
        binding.floatingActionButton2.setOnClickListener {

            lifecycleScope.launch {
                saveAndGetAPI()
            }

        }

        // Кнопка очистки списка
        binding.floatingActionButton3.setOnClickListener {

            adapter.addList(emptyList())
            adapter.notifyDataSetChanged()

            lifecycleScope.launch {
                peopleDao.deleteAllPeople()
            }

        }

    }

    private suspend fun saveAndGetAPI(){
        try {
            var people = mutableListOf<PeopleEntity>()

            repeat(10)
            {
                people.add(fetchSingleUser(requireContext()))
            }

            if(people.isNotEmpty())
            {
                people.forEach(){
                    peopleDao.insertPeople(it)

                }
            }

            // Обновляем RecyclerView
            adapter.addList(people)
            adapter.notifyDataSetChanged()


        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Ошибка загрузки: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }



    suspend fun fetchSingleUser(context: Context): PeopleEntity = withContext(Dispatchers.IO) {
        val queue = Volley.newRequestQueue(context.applicationContext)
        val url = "https://randomuser.me/api/"

        try {
            val response = suspendCancellableCoroutine<JSONObject> { continuation ->
                val request = JsonObjectRequest(
                    Request.Method.GET, url, null,
                    { response -> continuation.resume(response) },
                    { error -> continuation.resumeWithException(error) }
                )
                queue.add(request)

                continuation.invokeOnCancellation {
                    request.cancel()
                }
            }

            val userObject = response.getJSONArray("results").getJSONObject(0)
            PeopleEntity(
                id = null,
                firstName = userObject.getJSONObject("name").getString("first"),
                middleName = userObject.getJSONObject("name").getString("title"),
                lastName = userObject.getJSONObject("name").getString("last"),
                email = userObject.getString("email"),
                numberPhone = userObject.getString("phone"),
                photo = downloadImageAsByteArray(userObject.getJSONObject("picture").getString("large")), // или преобразуйте photoUrl в ByteArray если нужно
                latitude = userObject.getJSONObject("location")
                    .getJSONObject("coordinates").getString("latitude").toDouble(),
                longitude = userObject.getJSONObject("location")
                    .getJSONObject("coordinates").getString("longitude").toDouble()
            )
        } catch (e: Exception) {
            Log.e("API", "Error fetching user", e)
            Toast.makeText(requireContext(), "Ошибка загрузки: ${e.message}", Toast.LENGTH_SHORT).show()

            throw e // Пробрасываем исключение дальше
        }
    }

    private suspend fun downloadImageAsByteArray(url: String): ByteArray? {
        return try {
            withContext(Dispatchers.IO) {
                val connection = URL(url).openConnection()
                connection.connect()
                val inputStream = connection.getInputStream()
                val bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream.close()

                val outputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)
                outputStream.toByteArray()
            }
        } catch (e: Exception) {
            Log.e("ImageLoad", "Error loading image", e)
            Toast.makeText(requireContext(), "Ошибка загрузки: ${e.message}", Toast.LENGTH_SHORT).show()

            null
        }
    }





    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}