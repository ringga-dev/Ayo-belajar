package com.ringga.appfood.ui.home.music

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import com.ringga.appfood.data.model.ListAudioModel
import com.ringga.appfood.utill.SingleLiveEvent

class ListMusicViewModel : ViewModel() {
    private var state: SingleLiveEvent<UserState> = SingleLiveEvent()
//    private var _lagu = MutableLiveData<ListAudioModel>()

    //get lagu dari device
    fun getAllAudioFromDevice(context: Context) {
        val listLagu: MutableList<ListAudioModel> = ArrayList()

        val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.AudioColumns.DATA,
            MediaStore.Audio.AudioColumns.ALBUM,
            MediaStore.Audio.ArtistColumns.ARTIST
        )
        /** folder tertentu*/
//    val c: Cursor = context.contentResolver.query(
//        uri,
//        projection,
//        MediaStore.Audio.Media.DATA + " like ? ",
//        arrayOf("%yourFolderName%"),
//        null
//    )!!

        /** all music*/
        val c = context.contentResolver.query(
            uri,
            projection,
            null,
            null,
         null
        )
        if (c != null) {

            while (c.moveToNext()) {
                val path: String = c.getString(0)
                val album: String = c.getString(1)
                val artist: String = c.getString(2)
                val name = path.substring(path.lastIndexOf("/") + 1)

                if (path.endsWith(".mp3")){
                    if (path.endsWith("tone.mp3")){
                        continue
                    }else{
                        listLagu.add(ListAudioModel(path, name, album, artist))
                    }
                }else{
                    continue
                }

            }
            c.close()

        }
        state.value = UserState.ListLagu(listLagu)
    }

    fun getState() = state
}

sealed class UserState {
    data class Error(var err: String) : UserState()
    data class ShoewToals(var message: String) : UserState()
    data class Validate(
        var name: String? = null,
        var email: String? = null,
        var password: String? = null
    ) : UserState()

    data class IsLoding(var state: Boolean = false) : UserState()
    data class ListLagu(var dataLagu: MutableList<ListAudioModel>) : UserState()
    data class Failed(var message: String) : UserState()
    object Reset : UserState()
}