/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.raywenderlich.android.domain.repository.WeatherRepository
import com.raywenderlich.android.ui.home.mapper.HomeViewStateMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private const val SEARCH_DELAY_MILLIS = 500L
private const val MIN_QUERY_LENGTH = 2

@FlowPreview
@ExperimentalCoroutinesApi
class HomeViewModel(
    private val weatherRepository: WeatherRepository,
    private val homeViewStateMapper: HomeViewStateMapper
) : ViewModel() {

  val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

  private suspend fun getLocations(query: String): List<LocationViewState> {
    val locations = viewModelScope.async { weatherRepository.findLocation(query) }

    return homeViewStateMapper.mapLocationsToViewState(locations.await())
  }

  fun fetchLocationDetails(cityId: Int) {
    viewModelScope.launch {
      weatherRepository.fetchLocationDetails(cityId)
    }
  }
  val forecasts: LiveData<List<ForecastViewState>> = weatherRepository
    .getForecasts()
    .map {homeViewStateMapper.mapForecastsToViewState(it)
    }
    .catch {

    }
    .asLiveData()
}
