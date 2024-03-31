package edu.ktu.lithuanianlearningapp.viewmodels

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import edu.ktu.lithuanianlearningapp.R
import org.apache.commons.lang3.StringUtils.indexOfDifference


class TypingViewModel(
        val challange: String = "слово",
        val translation: String = "zodis",
        val maxAttempts: Int = challange.length,
        application: Application
    ) : AndroidViewModel(application) {


        private var index = 0

        private val _attemptsRemaining = MutableLiveData<Int>()
        val attemptsRemaining: LiveData<Int>
            get() = _attemptsRemaining
        private val _correct = MutableLiveData<Int>()
        val correct: LiveData<Int>
            get() = _correct
        private val _guessedChar = MutableLiveData<String>()
        val guessedChar: LiveData<String>
            get() = _guessedChar
        private val _guessResult = MutableLiveData<String>()
        val guessResult: LiveData<String>
            get() = _guessResult

        init {
            _correct.value = maxAttempts
        }

        init {
            _attemptsRemaining.value = maxAttempts
        }

        private val _guessError = MutableLiveData<String?>()
        val guessError: LiveData<String?>
            get() = _guessError
        val guessedLetterField = ObservableField<String>()

        private val _lose = MutableLiveData<Boolean>()
    val lose: LiveData<Boolean>
        get() = _lose


         var at = 0
         var at1 = 0

        fun makeGuess() {
            guessedLetterField.get().isNullOrEmpty().let { isEmpty: Boolean ->
                if (isEmpty) {
                    _guessError.value =
                        getApplication<Application>().resources.getString(R.string.nan_or_empty)
                    return
                } else {
                    _guessError.value = ""
                    guessedLetterField.get()?.let { guessedChar: String ->
                        _attemptsRemaining.value = _attemptsRemaining.value!! - 1
                        val done = guessedChar.lowercase() == challange.lowercase()
                        if (_attemptsRemaining.value == 0 || done || at1 == maxAttempts) {
                            _lose.value = true
                        }
                        else if(done == false) {
                            at = indexOfDifference(guessedChar.lowercase(), challange.lowercase())
                            _correct.value = _correct.value!! - 1
                            index++
                            if (at1 < at) {
                                at1 = at
                                _guessResult.value = challange.substring(0, at1)
                            } else if (at1 >= at) {
                                at1++
                                if (at1 == maxAttempts) {
                                    _lose.value = true
                                }
                                _guessResult.value = challange.substring(0, at1)
                            }
                            _guessedChar.value = guessedChar
                            guessedLetterField.set("")
                        }
                    }
                }
            }
        }

        fun navigatedToLose() {
            _lose.value = false
        }
    }
    class TypingViewModelFactory(
        val challange: String = "слово",
        val translation: String = "zodis",
        val maxAttempts: Int = challange.length,
        val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TypingViewModel::class.java)) {
                return TypingViewModel(challange, translation, maxAttempts, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }


