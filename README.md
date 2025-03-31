# RestAssuredTestNGFramework
# Spotify API Automation Framework

## 📌 Overview
This project is a **test automation framework** for **Spotify API** using **RestAssured, Java, Maven, and Jenkins**. It automates API testing for Spotify's endpoints, ensuring reliable and consistent validation of functionalities such as **playlist management, track search, and user profile retrieval**.

## 🚀 Technologies Used
- **Java** - Programming language
- **RestAssured** - API testing library
- **Maven** - Build tool & dependency management
- **Jenkins** - CI/CD integration
- **TestNG** - Test framework

## 🛠️ Setup & Installation
### Prerequisites
- Install **Java (JDK 11 or later)**
- Install **Maven**
- Install **Jenkins** (optional for CI/CD)

### Clone the Repository
```sh
git clone https://github.com/sumitsaurabh2107/RestAssuredTestNGFramework.git
cd spotify-api-automation
```

### Configure API Credentials
1. Get **Spotify API Client ID & Client Secret** from [Spotify Developer Portal](https://developer.spotify.com/dashboard/).
2. Update **config.properties** or environment variables with your credentials:
```properties
SPOTIFY_CLIENT_ID=your_client_id
SPOTIFY_CLIENT_SECRET=your_client_secret
SPOTIFY_ACCESS_TOKEN=your_access_token
```

## 🏃 Running the Tests
### Run Tests Locally
```sh
mvn test
```

## 🔄 CI/CD Pipeline (Jenkins)
- Automated test execution on **code commits & merges**.
- **Test results & logs stored in Jenkins for analysis**.
- Pipeline configuration managed via `Jenkinsfile`.

## 📌 Features Covered
✅ **Authentication & Token Management**
✅ **Create, Update, and Delete Playlists**
✅ **Add & Remove Tracks from Playlists**
✅ **Search for Tracks & Artists**
✅ **Retrieve User Profile & Playlists**
✅ **Validation of Response Codes & Payloads**

## 🤝 Contribution
Feel free to fork the repository, create a new branch, and submit a **Pull Request**.

---
🚀 **Happy Testing!** 🎵
