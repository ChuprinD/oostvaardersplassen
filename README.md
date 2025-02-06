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

1. Run the clone command<br>
`git clone https://github.com/ChuprinD/oostvaardersplassen`<br>
2. Create an empty sql database <br>
`CREATE DATABASE database_name;`<br>
3. Use the mysql command in the terminal<br>
`mysql -u username -p database_name < [path_to_project]/ src/main/resources/DB_Mysql/ oostvaardersplassen3.0.sql`<br>
4. Go to [path_to_project]/src/main/java/com/group3/database/ DatabaseApp.java<br> 
Change variable PASS to your sql password<br>
5. From project folder run<br>
`mvn clean install`<br>
6. Download javafx-sdk-21.0.5 from official [site](https://openjfx.io/)<br>
7. From project folder run<br> 
`java --module-path [path_to_javaFX]/lib --add-modules javafx.controls,javafx.fxml,javafx.swing -jar target/oostvaardersplassen-1.0-SNAPSHOT.jar`