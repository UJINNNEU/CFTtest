package com.example.testcft

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.testcft.databinding.FragmentMainBinding
import com.example.testcft.databinding.FragmentSecondBinding
import com.example.testcft.main_fragment.ViewModelMain

class secondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    val viewModelMain:ViewModelMain by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSecondBinding.bind(view)

        binding.floatingActionButton.setOnClickListener(){
            findNavController().navigate(R.id.action_secondFragment_to_mainFragment)
        }

        binding.firstName2.text = viewModelMain.peopleEntity?.firstName ?: "ERRORviewModel"
        binding.middleName2.text = viewModelMain.peopleEntity?.middleName ?: "ERRORviewModel"
        binding.lastName2.text = viewModelMain.peopleEntity?.lastName ?: "ERRORviewModel"
        binding.phone2.text = viewModelMain.peopleEntity?.numberPhone ?: "ERRORviewModel"
        binding.email2.text = viewModelMain.peopleEntity?.email ?: "ERRORviewModel"
        binding.dolgota2.text = (viewModelMain.peopleEntity?.latitude ?: 0.0).toString()
        binding.shirota2.text = (viewModelMain.peopleEntity?.longitude?: 0.0).toString()

        if (viewModelMain.peopleEntity?.photo != null) {
            val bitmap = BitmapFactory.decodeByteArray(viewModelMain.peopleEntity!!.photo, 0,
                viewModelMain.peopleEntity!!.photo!!.size)
            binding.imageView.setImageBitmap(bitmap)
        } else {
            binding.imageView.setImageResource(R.drawable.ic_launcher_foreground)
        }

        binding.imageButton1.setOnClickListener(){
            makePhoneCall(viewModelMain.peopleEntity!!.numberPhone)
        }
        binding.imageButton2.setOnClickListener(){
            sendEmail(viewModelMain.peopleEntity!!.email)
        }
        binding.imageButton3.setOnClickListener(){
            map(viewModelMain.peopleEntity!!.longitude,viewModelMain.peopleEntity!!.latitude)
        }


    }
    private fun makePhoneCall(phoneNumber:String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        startActivity(intent)
    }

    private fun sendEmail(email:String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            putExtra(Intent.EXTRA_SUBJECT, "Обращение")
        }
        startActivity(Intent.createChooser(intent, "Отправить email"))
    }
    private fun map(longitude: Double,latitude: Double){
        val gmmIntentUri = Uri.parse("google.streetview:cbll=$longitude,$latitude")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }



}