# tournament-data
storing data from tournaments

1. Running the application

Requirements :
Start PostgreSQL instance on port 5432.
url: jdbc:postgresql://localhost:5432/postgres
user: postgres
password: postgres

Running the application: 
Start main method in TournamentDataApplication.kt class

The application will run on Localhost:8080

2. Using the application

Import postman GolfTournament.postman_collection.json from package tournament-data/postman to your postman application.
There will be 2 bookmarks from which you can add tournaments from 2 different data sources to the database.

You can check saved data in your running local database instance.

3. Accommodating of additional data sources.

a) Add another DataSource enum to DataSource, DataSourcePo and DataSourceDomain enum classes

b) create new TournamentData data class in adapters/api/dto/tournament package. This class must extend abstract class
TournamentData and implement all required methods. The new data source must have minimum required data that can be mapped to:
externalId: String
tournamentStartDate: Instant
tournamentEndDate: Instant
golfCourseName: String
hostCountry: String
numberOfRounds: Int

Add corresponding DataSource enum to addTournamentDataSource() method.
All additional data should be added to mapTournamentAdditionalData() method.

c) Add another case corresponding to new DataSource in makeTournament function in TournamentAdapter class
d) Add enum to data_source table in database
After that you can add tournaments with new data source.
