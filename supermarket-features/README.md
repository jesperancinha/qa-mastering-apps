# Albert Heijn Backend Technical Assignment

## Assignment

The assignment is to implement a microservice using Spring boot. The expected REST endpoints schema is described below. \
The assignment is rather open-ended and expects you to think of code structure and implementation yourself. \
Follow the requirements below and be ready to support decisions made in implementing this service.

### Endpoints

<table>
<tr>
   <td>Endpoint</td><td>Description</td><td>Request body example</td><td>Response body example</td>
</tr>
<!-- POST /deliveries -->
<tr>
   <td>POST /deliveries</td>
   <td>

   Creates a new delivery. <br>`status` is only allowed to be `IN_PROGRESS` or `DELIVERED`. For status `IN_PROGRESS` the `finishedAt` field must be `null`. For status `DELIVERED` the `finishedAt` field must be provided.
 
   </td>
   <td>

   ```json
   {
      "vehicleId": "AHV-589",
      "address": "Example street 15A",
      "startedAt": "2023-10-09T12:45:34.678Z",
      "status": "IN_PROGRESS"
   }
   ```

   </td>
   <td>

   ```json
   {
      "id": "69201507-0ae4-4c56-ac2d-75fbe27efad8",
      "vehicleId": "AHV-589",
      "address": "Example street 15A",
      "startedAt": "2023-10-09T12:45:34.678Z",
      "finishedAt": null,
      "status": "IN_PROGRESS" 
   }
   ```

   </td>
</tr>

<!-- POST /deliveries/invoice -->
<tr>
   <td>POST /deliveries/invoice</td>
   <td>
   
   Uses third party service (as defined in the [mock api](#mock-api)) to send invoices to customers.
   
   </td>
   <td>

   ```json
   {
      "deliveryIds": [
         "7167fc04-0625-49fc-98a9-8785a4a32b60"
      ]
   }
   ```

   </td>
   <td>

   ```json
   [
      { 
         "deliveryId": "7167fc04-0625-49fc-98a9-8785a4a32b60",
         "invoiceId": "e891827f-487f-4884-a8c3-77316212b81b"
      }
   ]
   ```

   </td>
</tr>

<!-- GET /deliveries/business-summary -->
<tr>
   <td>GET /deliveries/business-summary</td>
   <td colspan="2">
   
   Business wants a summary of yesterday's deliveries (Amsterdam time).<br>The summary must include how many deliveries were **started**. The summary should also include the average time between delivery start. This means if there are 3 deliveries that started at `01:00`, `03:00` and `09:00` the time between starting deliveries is `2 hours` (01:00-03:00) and `6 hours` (03:00 - 09:00) so the average is `4 hours` or `240 minutes`
   
   </td>
   <td>

   ```json
   {
      "deliveries": 3,
      "averageMinutesBetweenDeliveryStart": 240
   }
   ```

   </td>
</tr>
</table>

## Mock API
A mock API is exposed on port `8000` which is defined in the [docker-compose file](./docker-compose.yml#L4), this mock API must not be modified. The endpoint exposed in this API is used for the `/deliveries/invoice` task. The mock API exposes the following endpoint.
<!-- POST /v1/invoices -->
<table>
<tr>
   <td>Endpoint</td><td>Request body example</td><td>Response body example</td>
</tr>
<tr>
   <td>POST /v1/invoices</td>
   <td>

   ```json
   {
      "deliveryId": "7167fc04-0625-49fc-98a9-8785a4a32b60",
      "address": "Example street 15A"
   }
   ```

   </td>
   <td>

   ```json
   {
      "id": "e891827f-487f-4884-a8c3-77316212b81b",
      "sent": true
   }
   ```

   </td>
</tr>
</table>



## Requirements
- We do not expect you to spend more than 3 hours on the assignment. You can add items to [**To-do and considerations**](#to-do-and-considerations) for anything that you wanted to do but did not have enough time to complete. In the follow-up interview this assignment will be discussed and you can elaborate/expand on decisions made in the assignment.
- Write the assignment in **Kotlin** if you are proficient with it. It's also possible to write the assignment in **Java**. However, we prefer **Kotlin** as it is our primary programming language.
- Use **Git** and commit often, so we can see the iterations made on the code.
- The above REST endpoints are implemented (also following the requirements in the description).
- The data is stored in a `database`, you can choose what type.
- This is a customer-facing application, which means a website will use this data to display it to the user.
- Assume this is **production code** that will run in production at Albert Heijn and will be maintained/modified for a long time. Not all requirements for "production ready" are listed here, we expect you to decide what is necessary and to support your reasoning. Anything you weren't able to implement, think should be implemented, and other considerations should be added in the README under [**To-do and considerations**](#to-do-and-considerations).

## Where to start
- An empty application is already set up. You are expected to add the endpoint implementations yourself.
- [A docker-compose file](./docker-compose.yml) already exists that builds and runs the application. Run this to make the [mock API](#mock-api) and database (that you add yourself) available. You can use the following command `docker-compose up --build`

## To-do and considerations
- ...

## Sending in the assignment
- We expect a docker compose file that we can run with `docker-compose up` which should start up a functional application at port 8080 (including dependencies like a database).
- Create a pull request with your changes. Notify us via e-mail when the assignment is ready for review.

Thank you for your interest and time invested into making this assignment.
