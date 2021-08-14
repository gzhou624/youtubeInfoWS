#### A restful web services to interact with Youtube using the following technologies:
1. Java
2. Spring boot
3. Restful Web Services
4. Maven
5. [Youtube Data API](https://developers.google.com/youtube/v3)

Once the application starts up in local,
```
2021-08-14 09:36:55.614  INFO 13880 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2021-08-14 09:36:55.645  INFO 13880 --- [  restartedMain] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2021-08-14 09:36:55.692  INFO 13880 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2021-08-14 09:36:55.692  INFO 13880 --- [  restartedMain] com.demo.MainApp                         : Started MainApp in 3.968 seconds (JVM running for 4.951)
2021-08-14 09:38:01.037  INFO 13880 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring FrameworkServlet 'dispatcherServlet'
2021-08-14 09:38:01.037  INFO 13880 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization started
2021-08-14 09:38:01.057  INFO 13880 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization completed in 20 ms
```
call the url in your browser: 

`http://localhost:8080/video/Kt7lpqdbNgA` (for a video).  
or  
`http://localhost:8080/channel/UCGaOvAFinZ7BCN_FDmw74fQ` (for a channel).  

You should receive a response similar to this:
```[{"crawlerTime":1628952082240,"keywords":"yt:cc=off,cvg,trip,explore,attractions,tourist,guide,vacation,tourism,expedia,travel,visit,Six Flags over Texas,Dallas Zoo,Dallas Museum of Art,Dallas World Aquarium,Majestic Theater,Southfork Ranch,Reunion Tower,Nasher Sculpture Center,The Sixth Floor Museum at Dealey Plaza,Dallas Arboretum and Botanical Garden,Perot Museum Of Nature And Science,","mainBody":"Dallas ¨C Soak up the southern charm in this Texan city. Here you¡¯ll find a mix of modern edge and wild west roots; check out the best of Dallas. \n\nWhen ready, browse vacation packages to Dallas: https://www.expedia.com/Dallas.d178253.Destination-Travel-Guides\n\nWelcome to #Dallas, also called ¡°The Big D,¡± part of the Dallas-Fort Worth-Arlington metroplex of 6.5 million people.\n\nWhether you¡¯re looking for arts, technology, or culture, a Dallas #vacation has a little bit of everything. Stroll through the Dallas Museum of Art and the Nasher Sculpture Center to take in their creative works, followed by a trip up to the top of the Reunion Tower for a breathtaking view of the city.\n\nDallas #sightseeing must include the underwater passages of the Dallas World Aquarium. You can also scream your way through over 100 rides at Six Flags, or explore the history of the cowboy way at Pioneer Plaza.\n\nA fine Texas day comes to an end with steak, dancing, and mingling with friendly locals who have hearts as big as the state itself.\n\nFor now, we hope you enjoy watching this #travel #guide as much as we enjoyed making it. \n\nMore travel information around Dallas: https://www.expedia.com/Dallas.dx178253\n\nSubscribe to Expedia¡¯s YouTube Channel for great travel videos and join the conversation on the best vacation ideas. \n\n---------\n\nFollow us on social media: \nFACEBOOK: https://www.facebook.com/expedia\nTWITTER: https://twitter.com/Expedia\nINSTAGRAM: https://instagram.com/expedia/\nPINTEREST: https://www.pinterest.com/expedia\n\n---------\n\n0:52 - Reunion tower\n0:17 - Dallas Museum of Art\n1:32 - Nasher Sculpture Center\n1:42 - Arboretum and Botanical Gardens\n2:00 - Dallas World Aquarium\n2:17 - Perot Museum of Nature and Science\n2:29 - Dallas Zoo\n2:40 - Six Flags Over Texas \n2:56 - Heritage Village\n3:00 - Southfork Ranch\n3:22 - Pioneer Plaza\n3:42 - West End\n3:57 - The Sixth Floor Museum","releaseTime":1373932150000,"title":"Dallas Vacation Travel Guide | Expedia","videoId":"Kt7lpqdbNgA"}]```

This data is now available for further analysis.
