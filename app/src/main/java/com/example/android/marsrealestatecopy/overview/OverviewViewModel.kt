/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestatecopy.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsrealestatecopy.network.MarApi
import com.example.android.marsrealestatecopy.network.MarsProperty
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()
    //private val _property = MutableLiveData<MarsProperty>()

   private val _properties = MutableLiveData<List<MarsProperty>>()

    val properties: LiveData<List<MarsProperty>>
        get() = _properties

     //The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties()
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getMarsRealEstateProperties() {
//        _response.value = "Set the Mars API Response here!!!"

//        MarApi.retrofitService.getProperties().enqueue(
//                object : Callback<List<MarsProperty>>
//                {
//                    override fun onResponse(call: Call<List<MarsProperty>>, response: Response<List<MarsProperty>>) {
//                        _response.value = "Success: ${response.body()?.size} Mars properties retrieved"
//
//                    }
//
//                    override fun onFailure(call: Call<List<MarsProperty>>, t: Throwable) {
//                        _response.value = "Failure: " +t.message
//                    }
//                })


        /**use coroutines with exception handling, instead of using callbacks
         * Sets the value of the response LiveData to the Mars API status or the successful number of
         * Mars properties retrieved.
         */
        viewModelScope.launch {

//            try {
//                val listResult = MarApi.retrofitService.getProperties()
//                _response.value = "Success: ${listResult.size} Mars properties retrieved"
//
//                if(listResult.size > 0 ){
//                    _property.value = listResult[0]
//                }
//
//            } catch (e: Exception) {
//                _response.value = "Failure: ${e.message}"
//
//            }

            try {

                _properties.value = MarApi.retrofitService.getProperties()
                _response.value = "Success: Mars properties retrieved"

            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"

            }
        }
    }
}
