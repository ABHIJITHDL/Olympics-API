# Olympics Simulator API

Welcome to the Olympics Simulator API! This API allows you to simulate Olympic events, retrieve details about countries, athletes, and events, and rank countries based on their medal counts.

## Table of Contents
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
    - [Event Endpoints](#event-endpoints)
    - [Country Endpoints](#country-endpoints)
    - [Athlete Endpoints](#athlete-endpoints)
- [Simulations](#simulations)
- [Initialize Data](#initialize-data)
- [Conclusion](#conclusion)

## Getting Started

1. **Base URL:** `http://localhost:8080/api/v1`
2. **Prerequisites:** Ensure your server and docker containers are running and that the data is initialized using the `Set event details` endpoint.

## API Endpoints

### Event Endpoints

- **Get all events**
    - **Endpoint:** `GET /events`
    - **Description:** Retrieves a list of all events.

- **Get event by Id**
    - **Endpoint:** `GET /events/{id}`
    - **Description:** Retrieves details for a specific event by its ID.

- **Get top countries by event Id**
    - **Endpoint:** `GET /events/{id}/toppers`
    - **Description:** Retrieves the top countries for a specific event by event ID.

### Country Endpoints

- **Get all countries**
    - **Endpoint:** `GET /countries`
    - **Description:** Retrieves a list of all participating countries.

- **Get top gold countries**
    - **Endpoint:** `GET /countries/goldWinners`
    - **Description:** Retrieves the top countries by the number of gold medals. Pass the `count` query parameter to limit the results.

- **Get top silver countries**
    - **Endpoint:** `GET /countries/silverWinners`
    - **Description:** Retrieves the top countries by the number of silver medals. Pass the `count` query parameter to limit the results.

- **Get top bronze countries**
    - **Endpoint:** `GET /countries/bronzeWinners`
    - **Description:** Retrieves the top countries by the number of bronze medals. Pass the `count` query parameter to limit the results.

- **Get top countries**
    - **Endpoint:** `GET /countries/topMedalWinners`
    - **Description:** Retrieves the top countries considering all medal types. Pass the `count` query parameter to limit the results.

### Athlete Endpoints

- **Get all athletes**
    - **Endpoint:** `GET /athletes`
    - **Description:** Retrieves a list of all athletes.

- **Get top athletes**
    - **Endpoint:** `GET /athletes/toppers`
    - **Description:** Retrieves the top athletes. Pass the `count` query parameter to limit the results. The `gender` query parameter is optional and can be used to filter results by gender.

## Simulations

- **Simulate event by id**
    - **Endpoint:** `GET /events/simulate/{id}`
    - **Description:** Simulates the results of a specific event by its ID.

- **Simulate all events**
    - **Endpoint:** `GET /events/simulate`
    - **Description:** Simulates the results of all events.

## Initialize Data

- **Set event details**
    - **Endpoint:** `POST /events`
    - **Description:** Initializes the data by setting up the number of events, athletes, participants, and the associated country.
    - **Sample Request Body:**
      ```json
      {
        "events": "10",
        "athletes": "30",
        "participants": "10",
        "country": "GE"
      }
      ```

  This endpoint should be called before running simulations or retrieving data to ensure that the necessary data is available.

## Conclusion

The Olympics Simulator API provides a flexible way to simulate and interact with Olympic events, athletes, and countries. Make sure to initialize your data using the `Set event details` endpoint to start exploring the capabilities of this API!
