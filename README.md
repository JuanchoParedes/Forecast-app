# Forecast App
![Captura](https://github.com/JuanchoParedes/Forecast-app/assets/4059634/11824acb-c2fb-4685-9743-831b5ffcb374)

**Forecast App** is a simple Android application that provides weather forecasts based on user input. It leverages the [WeatherAPI](https://www.weatherapi.com/) service to retrieve weather data.

## Features

- **Search**: Enter a location in the search view to get suggestions.
- **Suggestions**: View suggestions based on the entered text.
- **Forecast**: Click on a suggestion to view detailed weather forecasts.
- **Background Tasks**: Utilizes RxJava for efficient background tasks.
- **Dependency Injection**: Uses Dagger2 for dependency injection.
- **API Calls**: Implements Retrofit for communication with the WeatherAPI.
- **Architecture Patterns**: Follows Clean Architecture, SOLID principles, and Repository pattern.
- **MVVM**: Utilizes MVVM architecture with ViewModel and LiveData.
- **Android Splashscreen API**: Implements the Android Splashscreen API.

## API Key

To run the app, you will need to obtain an API key from [WeatherAPI](https://www.weatherapi.com/) and replace it in the appropriate places in the code.

## Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/JuanchoParedes/Forecast-app.git


2. Open the project in Android Studio.  Replace the API key in the code with your WeatherAPI key in your module build.gradle file into the "API_KEY" buildConfigField [^1]

3. Build and run the application.


## Project Disclosure

This project is intended for educational purposes and as a demonstration of coding and architectural concepts. It may not adhere to all best practices, and certain features may be implemented for learning purposes rather than real-world efficiency or security.

**Please note the following:**

- The code may not be optimized for production use.
- Certain features may be intentionally simplified for educational clarity.
- Security considerations may not be exhaustive.

Use this project as a learning resource, and feel free to explore, modify, and experiment with the code. If you plan to use any part of this project in a production environment, make sure to thoroughly review and adapt the code based on your specific requirements.

Contributions, suggestions, and improvements are welcome, but keep in mind the educational nature of this project.


[^1]:This is a bad practice and it is only for demonstration purposes. For Release and real wolrd applications, a secure way to store keys must be implemented.





