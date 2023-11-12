# football-fan
Application meant for football lovers

## Data Source
The data come from [LiveScore](https://rapidapi.com/apidojo/api/livescore6) through Rapid API marketplace.
There is a prepared docker compose environment in the [docker folder](docker) to run a kafka connector environment and request data from LiveScore and thus publish them into a kafka topic.

### Running docker-compose
The following steps may be executed from the project root to run the docker-compose environment and have the connector up to receive data from LiveScore:
* `cd docker`
* `docker-compose up -d`
