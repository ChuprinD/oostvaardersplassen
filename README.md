# oostvaardersplassen

## Description
This project provides a helpful tool for assessing the potential
benefits of introducing grey wolves into the Oostvaardersplassen
reserve. The mathematical models, interactive simulations, and
analysis of ecological factors offer valuable insights that can
guide future management decisions. These findings could be used
to help manage the reserve and may also be useful for similar
conservation efforts in other areas. 

## Visuals
![Screenshot_1](/images/Screenshot_1.png)
![Screenshot_2](/images/Screenshot_2.png)
![Screenshot_3](/images/Screenshot_3.png)
![Screenshot_4](/images/Screenshot_4.png)


## Installation
This is how you set up the code to run it from source.

1. Run the clone command 
'git clone https://github.com/ChuprinD/oostvaardersplassen'
2. Create an empty sql database 
'CREATE DATABASE database_name;'
3. Use the mysql command in the terminal 
'mysql -u username -p database_name < [path_to_project]/ src/main/resources/DB_Mysql/ oostvaardersplassen3.0.sql'
4. Go to [path_to_project]/src/main/java/com/group3/database/ DatabaseApp.java 
Change variable PASS to your sql password
5. From project folder run 
'mvn clean install'
6. Download javafx-sdk-21.0.5 from official [site](https://openjfx.io/)
7. From project folder run 
'java --module-path [path_to_javaFX]/lib --add-modules javafx.controls,javafx.fxml,javafx.swing -jar target/oostvaardersplassen-1.0-SNAPSHOT.jar'