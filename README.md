# Football Fan
Application for football lovers

## Purpose
The purpose of Football Fan is to show the current score of live football matches from championships all over the world.

## User Interface
The scores are displayed to the users through a web page, and Thymeleaf is used for this purpose. Therefore, the front-end page is rendered on the backend side. <br/> 
For the front-end components and styling of the application, [Bootstrap](https://getbootstrap.com/) is used.

### Limitations
While the data is near real-time on the backend side, the web page requires manual refreshing to display the live scores. As a follow-up step, a mechanism for automatic score refreshing on the page should be developed.

## Data Source
The data come from [LiveScore](https://rapidapi.com/apidojo/api/livescore6) through Rapid API marketplace.
There is a prepared docker compose environment in the [docker folder](docker) to run a kafka connector environment and request data from LiveScore and thus publish them into a kafka topic.

## Testing
Unit and integration tests can be executed by `./mvnw test` from the project root path.

## Environments
### Local
All the required services to run a local environment are configured in a Docker Compose file. There are two ways to execute the local environment: with sample data or real-time data.
#### Sample Data
Execute the following steps from the project root path to run the application locally by using sample data:
* `docker compose -f docker/docker-compose.yaml up zookeeper kafka`
* `./mvnw spring-boot:run`

To add sample data:
* `cd docker/localEnv/`
* `./script.sh docker-kafka-1`
* Access `http://localhost:8080` on the browser.

#### Real time data
In order to run the environment locally and start receiving live scores an account has to be created on [LiveScore API](https://rapidapi.com/apidojo/api/livescore6). Besides, the following steps have to be executed from the project root path:
* Replace the property`http.request.headers` in the configuration file `docker/connector/config.json` with the `X-RapidAPI-Key` that was generated on [LiveScore API](https://rapidapi.com/apidojo/api/livescore6).
* `docker compose -f docker/docker-compose.yaml up`
* `./mvnw spring-boot:run`
* Access `http://localhost:8080` on the browser.
* The url `http://localhost:9000` may be used to check events in the kafka topic through [Kafdrop](https://github.com/obsidiandynamics/kafdrop) web UI.
