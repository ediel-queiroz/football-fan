# Football Fan
Application meant for football lovers

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

### Running docker-compose
The following steps may be executed from the project root to run the docker-compose environment and have the connector up to receive data from LiveScore:
* `cd docker`
* `docker-compose up -d`
