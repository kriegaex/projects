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