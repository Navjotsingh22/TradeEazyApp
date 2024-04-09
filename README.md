TradeEazy Readme
Introduction
This README file outlines the structure and functionality of a trading app developed to learn the basics of app development. The app incorporates features such as login via Google, Facebook, and Firebase, registration, and various functionalities like accessing trading materials and videos, contacting support, and signing out.

Main Features
1. Login Page
The main activity focuses on providing a login page to authorize users to access the app. Three methods are utilized for authentication:

Google Login
Facebook Login
Firebase Login
Dependencies used:

groovy
Copy code
implementation("com.facebook.android:facebook-android-sdk:latest.release")
implementation(platform("com.google.firebase:firebase-bom:32.7.2"))
implementation("com.google.firebase:firebase-analytics")
implementation ("com.google.firebase:firebase-auth:22.3.1")
implementation("com.google.firebase:firebase-auth")
implementation("com.google.android.gms:play-services-auth:21.0.0")
implementation ("com.google.android.gms:play-services-identity:18.0.1")
2. Registration
After successful login, users can register by filling out a simple form containing EditText fields and a submit button. User information is then sent to the Firebase database for future logins.

3. Welcome Activity
Upon successful login, users are directed to the welcome activity, which serves as their first interaction with the app. Key features of the welcome activity include:

Drawer navigation with five items:
Home (under development)
Candlestick activity
Contact Us
Free Material
Video Lecture
Live stream of finance by Yahoo to enhance user experience.
4. Individual Activities
Candlestick Activity
Provides a PDF containing information about candlesticks. Implemented using com.github.barteksc.pdfviewer.PDFView.

Free Material
Offers a variety of PDFs for users to access, each triggered by a corresponding button click. Allows users to read trading-related materials conveniently.

Videos
Allows users to watch YouTube videos within the app using the com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView.

Contact Us
Enables users to send inquiries by redirecting them to their default mail application.

5. Sign Out
Provides a simple option for users to exit the app.

6. For the final touch I completed the Home Tab by incorporating News API to provide Financial News by using NewsAdaptor and RecyclerView.

Conclusion
This trading app provides a comprehensive learning experience for users by incorporating various functionalities related to trading and finance. The structured layout and intuitive design aim to enhance user engagement and learning.
