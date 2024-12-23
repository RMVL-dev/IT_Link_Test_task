package com.example.itlinktesttask.ui.test

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itlinktesttask.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.io.FileOutputStream
import java.io.InputStream
import java.nio.file.Paths
import javax.inject.Inject


@HiltViewModel
class TestViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {

    fun getFile(context:Context){
        viewModelScope.launch(Dispatchers.IO) {
            val responseBody = repository.retrieveFile().body()
            val path = saveFile(responseBody, "${context.filesDir}/newFile.txt")
            Log.d("FilePath",path)
        }
    }

    private fun saveFile(body: ResponseBody?, path: String):String{
        if (body==null)
            return ""
        var input: InputStream? = null
        try {
            input = body.byteStream()
            //val file = File(getCacheDir(), "cacheFileAppeal.srl")
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