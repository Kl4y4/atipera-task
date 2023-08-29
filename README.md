# atipera-task
RUNNING:

If using Intellij IDEA, the IDE should do everything automatically.

To build gradle wrapper:

gradlew.bat build

To build JAR file:

gradle jar

To run the application:

gradle run

USAGE:

API currently has only one endpoint - /repos - and it requires a 'username' parameter - otherwise Bad Request will be returned.
