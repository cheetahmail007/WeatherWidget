package com.example.weatherappall

object Constants {
    const val WEATHER_SUCCESS_RESPONSE = """
    {
    "coord": {
        "lon": 50.1,
        "lat": 50.1
    },
    "weather": [
        {
            "id": 804,
            "main": "Clouds",
            "description": "overcast clouds",
            "icon": "04n"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 288.98,
        "feels_like": 288,
        "temp_min": 288.98,
        "temp_max": 288.98,
        "pressure": 1022,
        "humidity": 53,
        "sea_level": 1022,
        "grnd_level": 1021
    },
    "visibility": 10000,
    "wind": {
        "speed": 2.93,
        "deg": 237,
        "gust": 4.94
    },
    "clouds": {
        "all": 93
    },
    "dt": 1665164544,
    "sys": {
        "country": "KZ",
        "sunrise": 1665110894,
        "sunset": 1665151592
    },
    "timezone": 18000,
    "id": 610189,
    "name": "Bulak",
    "cod": 200
}
    """
}