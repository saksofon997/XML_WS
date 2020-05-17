## Authority/User service

### Description

Microservice that handles user data, authorization, authentication and permissions.

### Model
#### User:
- id
- name
- surname
- email
- password
- roles
- address
- city
- state
- phone_number
- enabled
#### Role:
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
- **/user** GET: Get all users
- **/user/{id}** GET: Get single user
- **/user/{id}** PUT: Change user data
- **/user/{id}** DELETE: User account termination/deactivation

- **/role/{id}/permissions** GET: Get role permissions
- **/role/{id}/permissions** PUT: Change role permissions

- **/company** POST: Create company
- **/company/{id}** GET: Get single company
- **/company/{id}** PUT: Change user company
- **/company/{id}** DELETE: Company account termination/deactivation

## Rental service
### Description

Microservice that handles rentals, bundles and rental reports

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
- name
- rentals

#### Rental Report:
- id
- rental
- mileage
- description

### Endpoints:
- **/rental** POST: Create rental
- **/rental/{id}** GET: Get single rental
- **/rental/{id}** PUT: Change single rental
- **/rental/{id}** DELETE: Delete/reject single rental
- **/rental/customer/{id}** GET: Get rentals of a customer

- **/rental/{id}/rental_report** GET: Get rental reports of a signle rental
- **/rental/{id}/rental_report** POST: Create rental report
- **/rental/{id}/rental_report/{id}** GET: Get single rental report
- **/rental/{id}/rental_report/{id}** PUT: Change single rental report
- **/rental/{id}/rental_report/{id}** DELETE: Delete/reject single rental report

- **/bundle** POST: Create bundle
- **/bundle/{id}** DELETE: Delete/reject single rental



## Chat service
### Description

Microservice that handles conversation between users

### Model
#### Conversation:
- id
- user_1
- user_2
- messages

#### Message:
- id
- sender
- reciever
- text

#### User:
(Synchronized with User service data with saga pattern)
- id
- name
- surname
- email

### Endpoints:
- **/conversation/websocket/{user_id}** Chat websocket

- **/conversation** POST: Create conversation
- **/conversation/{id}** GET: Get single conversation

## Vehicle service
### Description

Microservice that handles vehicles, its occupancy, vehicle attributes (brand, model...) and pricelists

### Model
#### Brand:
- id
- name
- models

#### Model:
- id
- name
- brand

#### Category:
- id
- name

#### Transmission:
- id
- name

#### Fuel:
- id
- name

#### Vehicle:
- id
- brand
- model
- category
- transmission
- fuel
- price
- seats
- childSeats
- mileage
- cdw
- numberOfStars
- numberOfReviews

#### Vehicle occupancy:
- id
- start_time
- end_time
- type (Enum: RENTAL, MANUAL)

#### Pricelist: 
- id
- owner_id
- name
- pricePerDay
- pricePerKm
- cdw
- description

#### Review: 
- id
- customer_id
- vehicle
- stars
- text

### Endpoints:
- **/brand** POST: Create brand
- **/brand/{id}** GET: Get single brand
- **/brand/{id}** PUT: Change single brand
- **/brand/{id}** DELETE: Delete single brand

- **/brand/{id}/model** POST: Create model
- **/model/{id}** GET: Get single model
- **/model/{id}** PUT: Change single model
- **/model/{id}** DELETE: Delete single model

- **/category** POST: Create category
- **/category/{id}** GET: Get single category
- **/category/{id}** PUT: Change single category
- **/category/{id}** DELETE: Delete single category

- **/transmission** POST: Create transmission type
- **/transmission/{id}** GET: Get single transmission type
- **/transmission/{id}** PUT: Change single transmission type
- **/transmission/{id}** DELETE: Delete single transmission type

- **/fuel** POST: Create fuel type
- **/fuel/{id}** GET: Get single fuel type
- **/fuel/{id}** PUT: Change single fuel type
- **/fuel/{id}** DELETE: Delete single fuel type

- **/vehicle** POST: Create vehicle
- **/vehicle/{id}** GET: Get single vehicle
- **/vehicle/{id}** PUT: Change single vehicle
- **/vehicle/{id}** DELETE: Delete single vehicle
- **/vehicle/{id}/review** GET: Get reviews of a vehicle

- **/vehicle/{id}/occupancy** GET: Get vehicle occupancy
- **/vehicle/{id}/occupancy** POST: Add vehicle occupancy (Owner manualy or after rental PAID(SAGA or Gateway))
- **/vehicle/{id}/occupancy/{id}** PUT: Change manually added vehicle occupancy
- **/vehicle/{id}/occupancy/{id}** DELETE: Delete manually added vehicle occupancy

- **/pricelist** POST: Create pricelist
- **/pricelist/{id}** GET: Get single pricelist
- **/pricelist/{id}** PUT: Change single pricelist
- **/pricelist/{id}** DELETE: Delete single pricelist
- **/pricelist/owner/{id}** GET: Get pricelists of a vehicle owner

- **/review** POST: Create review
- **/review/{id}** GET: Get single review
- **/review/{id}** PUT: Change single review
- **/review/{id}** DELETE: Delete single review
- **/review/customer/{id}** GET: Get reviews of a customer

## Vehicle search service
### Description

Microservice for search feature

### Model
#### Brand:
(Synchronized with Vehicle service data with saga pattern)
- id
- name
- models

#### Model:
(Synchronized with Vehicle service data with saga pattern)
- id
- name
- brand

#### Category:
(Synchronized with Vehicle service data with saga pattern)
- id
- name

#### Transmission:
(Synchronized with Vehicle service data with saga pattern)
- id
- name

#### Fuel:
(Synchronized with Vehicle service data with saga pattern)
- id
- name

#### Vehicle:
(Synchronized with Vehicle service data with saga pattern)
- id
- brand
- model
- category
- transmission
- fuel
- seats
- childSeats
- mileage
- cdw
- numberOfStars
- numberOfReviews

#### Vehicle occupancy:
(Synchronized with Vehicle service data with saga pattern)
- id
- start_time
- end_time
- type (Enum: RENTAL, MANUAL)

#### Pricelist: 
(Synchronized with Vehicle service data with saga pattern)
- id
- owner_id
- name
- pricePerDay
- pricePerKm
- cdw
- description

### Endpoints:
- **/simple_search?location=Novi Sad&dateTime=2018391013....** GET: Get vehicles by simple search parameters
- **/advanced_search?brand=tesla,bmw,zastava&model=....** GET: Get vehicles by advanced search parameters

## Location service
### Description

Microservice that handles vehicle location

### Model
#### Location:
- id
- vehicle_id
- latitude
- longitude

### Endpoints:
- **/location** POST: Create on vehicle registration
- **/location/vehicle/{id}** GET: Get vehicle location
- **/location/vehicle/{id}** PUT: Change vehicle location
- **/location/vehicle/{id}** DELETE: Delete vehicle location
