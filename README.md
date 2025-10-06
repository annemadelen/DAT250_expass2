Project Report Expass 2

I started this project by planning a folder structure and deciding which files I wanted to include. 
This gave me a clear overview of all the different components and how they would fit together. 
After that, I downloaded the HTTP client using the VS Code REST client extension and went through the process of writing the code for each file. 

At one point, I ran into a problem where every line of code was not recognized by the IDE. 
Even though I had the correct imports, VS code marked all the files as they had errors. 
The first solution I tried was changing the gradle and java versions to make them compatible. 
That worked temporarily by removing all the red lines, so I continued by making all the tests. 
However, when I tried to run the tests, the same issue reoccurred. 
This time it seemed less about the code itself and more about VS code not recognizing its extensions. 
To solve this, I had to delete VS code and remove all related downloads, then reinstall it to my computer. 
After doing that, everything finally worked as expected. 
I was able to run all the tests successfully, and they passed without any issues.   

This project was my first experience with REST API where I learned more about setting up a structured workflow, 
dealing with environment issues and debugging problems that where not related to the code itself. 
I would say that the biggest lesson is to make sure all tools and dependencies are set up correctly,
and that I should probably do a factory reset of my computer. 

Project Report Expass 3

I started this project by only focusing on the frontend part and finishing a Poll that worked as intented. 
This went smooth and I had fun designing and learning React. 
When i started to connect my backend and using fetch, some problems occured. 
These problems were issues including missing code in my API or code that I needed to update in my backend part.
After going back and forth between my frontend and backend, trying to run the app multiple times and fixing bugs,
things started to work.

After this I wanted to implement a simple login, which lead to more going back and forth, changing things in both parts.
I managed to do this and then moved on to aesthetics and fixing my css file with some help from ChatGPT.
I want to continue to improve this and learn more about css later.

One issue that I need to solve is that an error message "invalid password or username" need to pop up if you type wrong when trying to login.

I also want to make a logout button. 

Project Report Expass 4

I did not get any technical problems with this assignment luckly. 
I only had some mistakes in the code from expass 2 that i had to edit such that the test passed. 
I made four tables icluding users, polls, vote_options and votes. users represented the user entity. polls represented Poll entity with a foreign key creator_id referecing user.id. vote_options is representing the vote_option entity, with a foreign key poll_id refrencing poll.id. votes is representing the vote entitiy, with foreign key voter_id (to user.id) and vote_option_id (to vote_options.id)
one pending issue that i did not manage to solve was that after the changes I did to my code, the test I made in expass 2 no longer passed. 

I have added 3 different screenshots, one where i tried to make 2 users by adding all the information needed. I also tried to make a poll by using INSERT INTO and added different vote options. Then i used SELECT tool to control that the poll was correctly added. 

Project Report Expass 5

During the setup and experimentation with Redis, several technical issues occured. Initially, Redis was installed successfully and working from the terminal, but after a system restart, the local Redis service was no longer recognized. I tried to reinstall redis through homebrew and starting it as a background service. I tested this by redis-cli ping and got back a pong, then I experimented with GET and SET which was sucessful. Next i tried to keep track of logged-in users and represent the complex information and this also worked. 

Then I tried implementing a new main RedisPollApp.java containing a simple poll for testing. This is where I got more issues. This incolved the dependencies where VS code could not read them leading to failing imports in the code. I tried to fix this but for some reason this lead to more complications with the JPA test files from last expass. I could no longer run ./gradlew build becouse of this. I was not able to fix this, however after a new download of VS code, the dependencie "redis.clients:jedis:6.2.0" sudddenly worked again. This made it possible to test that RedisPollApp.java and PollCacheManeger.java was able to run and work as expected. 

I did not manage to fix the PollTest from last week and can no longer run ./gradlew build. Even with some help from other students, it would not be fixed. We concluded that there is some fundemental issues with my PC or VS code itself. I might have to do a reset where I try to download everything again and make sure everything is working togheter. 





