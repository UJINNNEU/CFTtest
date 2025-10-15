package com.example.testcft.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.net.URL

class ImageDownloader
{
    private suspend fun downloadImageAsByteArray(
        url: String): ByteArray? {
        //photo = downloadImageAsByteArray(userObject.getJSONObject("picture").getString("large")), // или преобразуйте photoUrl в ByteArray если нужно
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
          //  Toast.makeText(requireContext(), "Ошибка загрузки: ${e.message}", Toast.LENGTH_SHORT).show()

            null
        }
    }
}