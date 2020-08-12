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





# Servlet Context #

### INTRO ###

Definning a set of methods that a servlet uses to communicate with its servlet container, for example, to get the MIME type of a file, dispatch requests, or write to a log file.





# Servlet Life-cycle #

### How Tomcat works with servlets ###

One of the key requirements worked into the Servlet specification is that they only are expected to handle certain parts of the total data transaction process.  For example, the servlet code itself will never listen for requests on a certain port, nor will it communicate directly with a client, nor is it responsible for managing its access to resources.  Rather, these things are managed by Tomcat, the servlet container.  

This allows servlets to be re-used in a wide variety of environments, or for components to be developed asynchronously from one another - a connector can be re-factored for improved efficiency without any changes to the servlet code itself, as long as no major changes are made.

### Servlet life cycles  ###

As managed components, servlets have a life cycle, which begins when the managing container loads the servlet class, usually in response to a request, and ends when the container closes the servlet by calling the "destroy" method.  All the servlet's activity between these two points is considered part of its life cycle.

The lifecycle of a typical servlet running on Tomcat might look something like this:

1. Tomcat receives a request from a client through one of its connectors.
2. Tomcat maps this request to the appropriate Engine for processing.  These Engines are contained within other elements, such as Hosts and Servers, which limit the scope of Tomcat's search for the correct Engine.
3. Once the request has been mapped to the appropriate servlet, Tomcat checks to see if that servlet class has been loaded.  If it has not, Tomcat compiles the servlet into Java bytecode, which is executable by the JVM, and creates an instance of the servlet.
4. Tomcat initializes the servlet by calling its init method.  The servlet includes code that is able to read Tomcat configuration files and act accordingly, as well as declare any resources it might need, so that Tomcat can create them in an orderly, managed fashion.
5. Once the servlet has been initialized, Tomcat can call the servlet's service method to process the request, which will be returned as a response.
6. During the servlet's lifecycle, Tomcat and the servlet can communicate through the use of listener classes, which monitor the servlet for a variety of state changes.  Tomcat can retrieve and store these state changes in a variety of ways, and allow other servlets access to them, allowing state to be maintained and accessed by various components of a given context across the span of a single or multiple user sessions.  An example of this functionality in action is an e-commerce application that remembers what the user has added to their cart and is able to pass this data to a checkout process.
7. Tomcat calls the servlet's destroy method to smoothly remove the servlet.  This action is triggered either by a state change that is being listened for, or by an external command delivered to Tomcat to undeploy the servlet's Context or shut down the server.

[SOURCE](https://www.mulesoft.com/tcat/tomcat-servlet)

### DESTORY ###

In java servlet, `destroy()` is not supposed to be called by the programmer. But, if it is invoked, it gets executed. The implicit question is, will the servlet get destroyed? No, it will not. `destroy()` method is not supposed to and will not destroy a java servlet.

The meaning of `destroy()` in java servlet is, the content gets executed just before when the container decides to destroy the servlet. But if you invoke the `destroy()` method yourself, the content just gets executed and then the respective process continues. With respective to this question, the `destroy()` gets executed and then the servlet initialization gets completed.

`destroy()` method is invoked first, then Servlet is removed from the container and then eventually garbage collected. `destroy()` method generally contains code to free any resources like JDBC connection that will not be garbage collected.

[source](https://stackoverflow.com/questions/13437259/calling-servlets-destroy-method)

