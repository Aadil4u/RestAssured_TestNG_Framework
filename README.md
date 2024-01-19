# Rest Assured TestNG Framework

This is a Rest Assured TestNG Framework for API testing. It utilizes the Rest Assured library, TestNG for test execution, Allure for reporting, and other dependencies for additional functionality.

## Prerequisites

- Java JDK 11 or higher
- Maven

## Getting Started

1. Clone the repository:

   ```bash
   git clone git@github.com:Aadil4u/RestAssured_TestNG_Framework.git
   ```
2. Navigate to the project directory:
```bash
cd RestAssured_TestNG_Framework
```  
3. Install dependencies:
```bash
mvn install -DskipTests
```

4. Run the tests:
```bash
mvn clean test -DACCOUNT_BASE_URI=https://accounts.spotify.com -DBASE_URI=https://api.spotify.com
```