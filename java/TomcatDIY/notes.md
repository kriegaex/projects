## ClassLoader ##



### INRO ###

The **Java ClassLoader** is a part of the [**Java Runtime Environment**](https://www.geeksforgeeks.org/differences-jdk-jre-jvm/) that dynamically loads Java classes into the [**Java Virtual Machine**](https://www.geeksforgeeks.org/jvm-works-jvm-architecture/). The Java run time system does not need to know about files and file systems because of classloaders.

READ [HERE from GeeksForGeeks](https://www.geeksforgeeks.org/classloader-in-java/)

### WHY USE Delegation Model ###

[structure of delegation model](https://stackoverflow.com/questions/2642606/java-classloader-delegation-model)

Basically to keep the uniqueness of each class, [READ here for more examples and explanations]([https://stackoverflow.com/questions/24857681/benefits-of-java-classloader-delegation-model#:~:text=For%20example%2C%20in%20a%20browser,as%20they%20wont%20be%20different.&text=This%20makes%20it%20easier%20to%20add%20functionality%20to%20an%20existing%20class%20loader.](https://stackoverflow.com/questions/24857681/benefits-of-java-classloader-delegation-model#:~:text=For example%2C in a browser,as they wont be different.&text=This makes it easier to add functionality to an existing class loader.))



### Structure of class loaders in Tomcat ###

Bootstrap class loader --> Extension class loader --> Application class loader -->

Common class loader --> Webapp class loader --> JSP class loader



Common class loader is reponsible for loading classes and jars in `%tomcat_home%/lib`

Webapp class loader aims to load classes speciliased to a web app, for example, the `WEB-INF/classes` under J2EE.

JSP class loader is responsible to load classes from java files which are translated from jsp file.



There are another two class loaders : Catalina class loader & Share class loader, which should load classes and jars from `%tomcat_home%/catalina/` & `%tomcat_home%/share`. Both them haven't be implemented.



### CLASS LOADERS in Tomcat ###

![](/Users/chaozy/Library/Application Support/typora-user-images/image-20200723231748430.png)		



[READMORE about the class loaders in Tomcat](https://tomcat.apache.org/tomcat-8.0-doc/class-loader-howto.html)



### TRACE class loading ###

INTELLIJ : Edit Configurations --> Bootstrap --> VM options --> `-Xlog:class+load=info`





## WatchService ##

To implement this functionality, called *file change notification*, a program must be able to detect what is happening to the relevant directory on the file system. One way to do so is to poll the file system looking for changes, but this approach is inefficient. It does not scale to applications that have hundreds of open files or directories to monitor.

The `java.nio.file` package provides a file change notification API, called the Watch Service API. 

[official tutorial](https://docs.oracle.com/javase/tutorial/essential/io/notification.html)



### Servlet Auto-Reloading in Tomcat ###

Tomcat by default will automatically reload a servlet when it notices that the servlet's class file has been modified. This is certainly a great convenience when debugging servlets; however, bear in mind that in order to implement this functionality, Tomcat must periodically check the modification time on every servlet. This entails a lot of filesystem activity that is unnecessary when the servlets have been debugged and are not changing.

To turn this feature off, you need only set the `reloadable` attribute in the web application's `Context` element (in either your *server.xml* or your context XML fragment file, wherever you've stored your `Context` element), and restart Tomcat. Once you've done this, you can still reload the servlet classes in a given `Context` by using the `Manager` application (detailed in the section "[The Manager Webapp](https://www.oreilly.com/library/view/tomcat-the-definitive/9780596101060/ch03s07.html)" in Chapter 3).



### ISSUES ###

- WatchService will only monitor the files under the registered directory. So files under subfolder cannot be located. All subfolders have to be registered. (https://stackoverflow.com/questions/16611426/monitor-subfolders-with-a-java-watch-service)
- Keys have to be resetted after each used, reason: https://stackoverflow.com/questions/20180547/event-fired-only-once-when-watching-a-directory



### Resources ###

[TOP tutorial](https://www.baeldung.com/java-nio2-watchservice)

