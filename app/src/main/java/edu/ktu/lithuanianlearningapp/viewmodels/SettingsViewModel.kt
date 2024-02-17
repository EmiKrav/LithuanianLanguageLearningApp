package edu.ktu.lithuanianlearningapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import edu.ktu.lithuanianlearningapp.utils.readBool
import edu.ktu.lithuanianlearningapp.utils.readInt
import edu.ktu.lithuanianlearningapp.utils.writeBool
import edu.ktu.lithuanianlearningapp.utils.writeInt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SettingsViewModel (val appContext: Application) : AndroidViewModel(appContext) {

    companion object{
        const val V1_USER_KEY = "name"
        const val V2_USER_KEY2 = "name2"
        const val V3_USER_KEY3 = "name3"
        const val V4_USER_KEY4 = "name4"
    }


    fun saveArg1(name: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeBool(V1_USER_KEY, name)
        }
    }

    val getArg1 = appContext.readBool(V1_USER_KEY).asLiveData()

    fun saveArg2(name2: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeBool(V2_USER_KEY2, name2)
        }
    }

    val getArg2 = appContext.readBool(V2_USER_KEY2).asLiveData()

    fun saveArg3(name3: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeBool(V3_USER_KEY3, name3)
        }
    }

    val getArg3 = appContext.readBool(V3_USER_KEY3).asLiveData()

    fun saveProgress(name4: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeInt(V4_USER_KEY4, name4)
        }
    }

    //Getting the name of saved user
    val getProgress = appContext.readInt(V4_USER_KEY4).asLiveData()
}
