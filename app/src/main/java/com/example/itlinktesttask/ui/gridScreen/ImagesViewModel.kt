package com.example.itlinktesttask.ui.gridScreen

import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itlinktesttask.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject


@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {

    private val _images = MutableStateFlow<List<String>>(emptyList())
    val images = _images.asStateFlow()

    fun getFile(){
        viewModelScope.launch(Dispatchers.IO) {
            val responseBody = repository.retrieveFile().body()
            val path = saveFile(
                responseBody,
                "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)}/images.txt"
            )

            //read file
            val bufferedReader: BufferedReader = File(path).bufferedReader()
            val inputString = bufferedReader.use { it.readText() }

            _images.update {
                inputString.split("\n")
            }

        }
    }

    /**
     * save response body to file
     */
    private fun saveFile(body: ResponseBody?, path: String):String{
        if (body==null)
            return ""
        var input: InputStream? = null
        try {
            input = body.byteStream()
            val fos = FileOutputStream(path)
            fos.use { output ->
                val buffer = ByteArray(4 * 1024) // or other buffer size
                var read: Int
                while (input.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                }
                output.flush()
            }
            return path
        }catch (e:Exception){
            Log.e("saveFile",e.toString())
        }
        finally {
            input?.close()
        }
        return ""
    }

}