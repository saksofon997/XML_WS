## Authority/User service

### Description

Microservice that works with user data, authorization, authentication and permissions.

### Model
#### User:
- id
- name
- surname
- email
- password
- authorities
- address
- city
- state
- phone_number
- enabled
#### Authority:
- id
- name
- permissions
#### Permission:
- id
- name
#### Company:
- id
- cid (company id - poslovni identifikacioni broj)
- name
- agents(users)

### Endpoints:
- **/user/register** POST: Registration
- **/user/login** POST: Login
- **/user/{id}** GET: Get single user
- **/user/{id}** PUT: Change user data
- **/user/{id}** DELETE: User account termination/deactivation
- **/user/{id}/permissions** GET: Get users permissions
- **/user/{id}/permissions** PUT: Change users permissions

- **/company** POST: Create company
- **/company/{id}** GET: Get single company
- **/company/{id}** PUT: Change user company
- **/company/{id}** DELETE: Company account termination/deactivation

- **/user** GET: Get all users

## Rental service
### Description

Microservice that works with rentals, bundles, and rental reports

### Model
#### Rental:
- id
- vehicle_id
- customer_id
- start_time
- end_time
- status (Enum: CANCELED, PENDING, PAID)
- bundle_id

#### Bundle:
- id
- rentals

#### Rental Report:
- id
- rental
- mileage
- description

### Endpoints:

- **/rentals** POST: Create rental

- **/rentals/customer/{id}**
