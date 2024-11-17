The project is a Kotlin-based Android library for calculating size recommendations based on BMI (Body Mass Index). It includes the following components:

1. **Library Module (`calc_sdk`)**:
    - Provides the core functionality for calculating size recommendations.
    - Contains an implementation of the `ICalcApi` interface, specifically the `DefaultCalcApi` class, which calculates size recommendations based on BMI values.
    - The logic for size recommendations is as follows:
        - S: BMI < 18.5
        - M: 18.5 <= BMI < 24.9
        - L: 25 <= BMI < 29.9
        - XL: BMI >= 30

2. **UI Module (`calc_sdk_ui`)**:
    - Provides a user interface for inputting height and weight to calculate size recommendations.
    - Utilizes Jetpack Compose for building the UI.
    - Includes UI tests to verify the functionality of the size calculator screen.

3. **Build Configuration**:
    - Uses Gradle Kotlin DSL for build configuration.
    - Configured with AndroidX libraries, Kotlin, and Jetpack Compose.
    - Supports a minimum SDK version of 24 and a compile SDK version of 35.
    - Includes dependencies for testing, such as JUnit and Espresso.

4. **Testing**:
    - Contains unit tests for the core calculation logic.
    - Includes UI tests to ensure the correct display and functionality of the size calculator screen.

This project is designed to be integrated into other Android applications to provide size recommendations based on user input of height and weight.
