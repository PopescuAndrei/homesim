In order to run the application, you must first have jdk1.8 installed as it uses JavaFx8.
The project uses Maven so all dependencies will get downloaded the first time you build 
the project.

######################################
Instructions for building the solution
######################################
We need a custom library, PLPLan.jar installed in the maven local repo. Navigate in the root of the project
and you'll find it here.
In order to install it in the local repo, fire a command line inside the root of the project and run
the following script:

mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile=PLPlan.jar -DgroupId=org.lib -DartifactId=plplan -Dversion=0.4 -Dpackaging=jar -DgeneratePom=true

After that, maven will use the jar from the local repo.

######################################
Instructions for running the solution
######################################
mvn clean install

Or use any IDE you like and just import the project, do a clean install and run the project.