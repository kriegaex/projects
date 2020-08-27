# DIYTomcat

DIYTomcat is a self-learning, educative java project that establishes a http server. It follows the basic design of the Apache Tomcat and implements most of its components, such as the servlet container Catalina, JSP translator and compiler Jasper. It was definitely a side project that I used to learn the structure of a web server, the principles of http protocol and the techniques in java programming. 

### Learning Material

Most of the knowledge comes from [the official website of Apache Tomcat](http://tomcat.apache.org/). It clearly explains the structure of the Tomcat, which provides the undelying principle and other useful informations. 

The chinese book [Tomcat架构解析](https://book.douban.com/subject/27034717/) provided me much more details about the implementation of Tomcat, such as the classloading of the web application, handling servlet and jsp by DefaultServlet and JspServlet respectively.  Actually, this project starts with the overall structure given in this book:

![Screenshot 2020-08-27 at 14.50.21](/Users/chaozy/Desktop/Screenshot 2020-08-27 at 14.50.21.png)

Besides these two materials, there are a lot of other online resources that help me finish this project, espcially stackoverflow😊

### USAGE

The project follows the launching structure of Apache Tomcat. Executing the `startup.sh` in the root folder launches the server on three ports `18080, 18081, 18082`(configured in `/lib/server.xml`)

 `startup.sh` contains the shell script to either pack and compile two initial java classes together, which will load the rest classes in. 

### STRUCTURE

- `/lib` contains the project-required jars, since the java libraries in this project is handled by maven dependency, read `pom.xml` for more details.
- `/logs` contains the log files named by the dates
- `/conf` contains three basic configuration files: `context.xml`, `server.xml`, `web.xml`
- `notes` concludes the knowledge I gained from this project
- `TODO` describes some features or components that I haven't finished yet
- `/src/main` contains the source code

### TOOLS

- `junit` used for testing
- `log4j2` used for logging
- `maven` used for project management
- `jsoup` used for parsing .xml files
- `jspc` from Apache Tomcat used to handle JSP

### FLAWS😢

There are some advanecd techniques in Apache Tomcat that I either failed to complete them or simplified the function. Here are a few examples (not all of them):

1. Apache Tomcat uses `digester`component to handle `.xml` files, while I use `jsoup`to simply parse `.xml` into a document get what I want. 
2. Apache Tomcat provides different logging files to store different kinds of information, with configurations in different layers, for example the `${catalina.base}/conf/logging.properties` is responsible for the global logging. 
3. Apache Tomcat provides options to optimzie the running, while I haven't get into this part yet.

