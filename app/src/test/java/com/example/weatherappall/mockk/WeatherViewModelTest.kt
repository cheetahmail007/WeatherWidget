package com.example.weatherappall.mockk

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.weatherappall.Constants
import com.example.weatherappall.model.WeatherRepository
import com.example.weatherappall.model.remote.data.forecast.ForecastResponse
import com.example.weatherappall.model.remote.data.pollutionforecast.PollutionForecastResponse
import com.example.weatherappall.model.remote.data.weather.WeatherResponse
import com.example.weatherappall.viewmodel.WeatherViewModel
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.lang.AssertionError

@ExperimentalCoroutinesApi
class WeatherViewModelTest {
    private val repository = mockk<WeatherRepository>(relaxUnitFun = true)
    private val weatherObserver = mockk<Observer<WeatherResponse>>(relaxed = true)
    private val forecastObserver = mockk<Observer<ForecastResponse>>(relaxed = true)
    private val pollutionObserver = mockk<Observer<PollutionForecastResponse>>(relaxed = true)
    private val errorObserver = mockk<Observer<String>>(relaxed = true)
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getCurrentLocationWeather success testing`() {
        runBlockingTest {

            val dummyResponse = Response.success(
                Gson().fromJson(
                    Constants.WEATHER_SUCCESS_RESPONSE,
                    WeatherResponse::class.java
                )
            )

            coEvery { repository.getCurrentLocationWeather(TEST_LAT, TEST_LON) } returns dummyResponse

            val viewModel = WeatherViewModel(repository)

            viewModel.weatherLiveData.observeForever(weatherObserver)

            viewModel.getCurrentLocationWeather(TEST_LAT, TEST_LON)

            coVerify { repository.getCurrentLocationWeather(TEST_LAT, TEST_LON) }

            val expectedResult =
                Gson().fromJson(Constants.WEATHER_SUCCESS_RESPONSE, WeatherResponse::class.java)

            coVerify { weatherObserver.onChanged(expectedResult) }

            viewModel.weatherLiveData.removeObserver(weatherObserver)
        }
    }

    @Test(expected = AssertionError::class)
    fun `getCurrentLocationWeather exception testing`() = runTest {

        val viewModel = WeatherViewModel(repository)

        viewModel.error.observeForever(errorObserver)

        viewModel.getCurrentLocationWeather(TEST_LAT, TEST_LON)

        coVerify { repository.getCurrentLocationWeather(TEST_LAT, TEST_LON) }

        coVerify { errorObserver.onChanged(any()) }

        viewModel.error.removeObserver(errorObserver)
    }
    private companion object{
        const val TEST_LAT = 50.1
        const val TEST_LON = 50.1
    }
}