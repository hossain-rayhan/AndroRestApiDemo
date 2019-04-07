# AndroRestApiDemo
Project Name: Android Rest API Demo

Description: 
Get and Post data using REST Api in Android [Retrofit, REST API, HTTP GET and POST].

GET: It will return a user list in JSON format. However, the JSON data is not a direct user list as we expect. Thats why I need to modify my actual Data class. User has three properties: First Name, Last Name, and Profile Image.

POST:
The post user is slightly different from the users I am getting. That's why I had to create another PostUser class to send the post request to the server.


What I used: 
Retrofit- Type safe HTTP client
Picasso- Image Loading/Processing Library
Singleton Design Pattern- To create single instance of the Retrofit client
Internet Checker Util- I wrote a class to check the internet availability
RecyclerView
CardView
Dialog Fragment- To Add a new User
Floating Action Button- To create a new user
Multiple Dimens- To make the app responsive and supported by multiple devices
Toast- To show some alert messages

Improvement Opportunity:
1. I have added Internet Permission on our AndroidManifest.xml file. Need to consider explicit permission acceptance.
