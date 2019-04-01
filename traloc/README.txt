/*Instructions on how to use the code*/
Start cassandra and create the following tables and keyspace in before proceeding:
create keyspace travel with replication = {'class':'SimpleStrategy', 'replication_factor':3};
create table travel.raw_data (id UUID PRIMARY KEY, url text, content text, country text);
create table travel.country(Country text primary key,Abbreviation text,Capital text,North text,South text, East text, West text, Continent text, Region text, Languages text,Area text, Elevation text, Population text, Population_density text, Currency_name text, Currency_code text, National_symbol text, Symbol_name text, National_dish text, Average_temperature text, Measurement text);
create table travel.transformed_data(country text primary key, positive list<text>);
create table travel.restaurant_data(id UUID primary key, country text, name text, cuisine text, cost_for_two double, rating double);


The data used is present in src/main/resources/data in the project
1. Requirements include JDK 1.8, Maven 3.5.3 and above and Apache Cassandra 3.11 and above
2. Build using command: mvn clean package
or import the project in an IDLE and build using maven. 
3. Call Process.loadAndProcessData() to load data and run the sentiment analyzer. The sentiment analysis can take up to an hour. 
4. Once the data is loaded and sentiment analysis is complete, run the controller TralocController. 
5. Open the browser and navigate to http://localhost:4567/travel.html to choose your destination.
Once the destination is chosen, please wait for a couple of seconds since the data fetching time could vary. 