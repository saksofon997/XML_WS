## Authority/User service

### Description

Microservice that handles user and company data, authorization, authentication and permissions. User has a list of roles. Each role contains a list of permissions. Permissions dictate what a role can do. Example: User has "BASIC_USER_ROLE" role that grants him permissions "CREATE_OWN_RENTAL", "EDIT_OWN_RENTAL"... but not "BAN_OTHER_USER" for example.

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

- **/role** GET: Get all roles
- **/role** POST: Create new role
- **/role/{id}** GET: Get a single role
- **/role/{id}** PUT: Change a role
- **/role/{id}** DELETE: Delete a role

- **/permissions** GET: Get all permissions
- **/permissions** POST: Create new permission
- **/permissions/{id}** GET: Get a single permission
- **/permissions/{id}** PUT: Change a permission
- **/permissions/{id}** DELETE: Delete a permission

- **/company** GET: Get all companies
- **/company** POST: Create company
- **/company/{id}** GET: Get single company
- **/company/{id}** PUT: Change user company
- **/company/{id}** DELETE: Company account termination/deactivation

## Rental service
### Description

Microservice that handles rentals, bundles and rental reports.

### Model
#### Rental:
- id
- vehicle_id
- customer_id
- owner_id
- start_time
- end_time
- status (Enum: CANCELED, PENDING, PAID)
- bundle_id
- location(Start and end location?)

#### Bundle:
- id
- name(?)
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

- **/customer/{id}/rental** GET: Get rentals of a customer
- **/vehicle/{id}/rental** GET: Get rentals of a vehicle
- **/owner/{id}/rental** GET: Get rentals of an owner

- **/rental/{id}/rental_report** GET: Get rental reports of a single rental
- **/rental/{id}/rental_report** POST: Create rental report
- **/rental/{id}/rental_report/{id}** GET: Get single rental report
- **/rental/{id}/rental_report/{id}** PUT: Change single rental report
- **/rental/{id}/rental_report/{id}** DELETE: Delete/reject single rental report

- **/bundle** POST: Create bundle
- **/bundle/{id}** GET: Get single bundle
- **/bundle/{id}** DELETE: Delete/reject single rental

## Chat service
### Description

Microservice that handles conversation between users. Conversation can be initiated when rental status is PENDING.

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
- timestamp

#### User:
(Synchronized with User service data with saga pattern(because of possible name change etc.))
- id
- name
- surname
- email

### Endpoints:
- **/conversation/websocket/{user_id}** Chat websocket

- **/conversation** POST: Create conversation
- **/user/{id}/conversation** GET: Get users conversations
- **/conversation/{id}** GET: Get single conversation

## Vehicle service
### Description

Microservice that handles vehicles, their occupancy, vehicle attributes (brand, model...), reviews, and pricelists.

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
- seats
- childSeats
- mileage
- cdw
- pricelist (some kind of reference)
- numberOfStars
- numberOfReviews

#### Vehicle occupancy:
- id
- start_time
- end_time
- type (Enum: RENTAL, PERSONAL)
- location(End location after end_time?? - Must also change in rental)

#### Pricelist: 
- id
- owner_id
- name
- pricePerDay
- pricePerKm
- cdw
- description
- vehicles 

#### Review: 
- id
- customer_id
- vehicle
- stars
- text

### Endpoints:
- **/brand** POST: Create brand
- **/brand** GET: Get all brands
- **/brand/{id}** GET: Get single brand
- **/brand/{id}** PUT: Change single brand
- **/brand/{id}** DELETE: Delete single brand

- **/brand/{id}/model** POST: Create model
- **/brand/{id}/model** GET: Get all models of a brand
- **/brand/{id}/model/{id}** GET: Get single model
- **/brand/{id}/model/{id}** PUT: Change single model
- **/brand/{id}/model/{id}** DELETE: Delete single model

- **/category** POST: Create category
- **/category** GET: Get all categories
- **/category/{id}** GET: Get single category
- **/category/{id}** PUT: Change single category
- **/category/{id}** DELETE: Delete single category

- **/transmission** POST: Create transmission type
- **/transmission** GET: Get all transmissions
- **/transmission/{id}** GET: Get single transmission type
- **/transmission/{id}** PUT: Change single transmission type
- **/transmission/{id}** DELETE: Delete single transmission type

- **/fuel** POST: Create fuel type
- **/fuel** GET: Get all fuel types
- **/fuel/{id}** GET: Get single fuel type
- **/fuel/{id}** PUT: Change single fuel type
- **/fuel/{id}** DELETE: Delete single fuel type

- **/vehicle** POST: Create vehicle
- **/vehicle** GET: Get all vehicles
- **/vehicle/{id}** GET: Get single vehicle
- **/vehicle/{id}** PUT: Change single vehicle
- **/vehicle/{id}** DELETE: Delete single vehicle

- **/vehicle/{id}/occupancy** GET: Get vehicle occupancy
- **/vehicle/{id}/occupancy** POST: Add vehicle occupancy (Owner manualy or after rental PAID(SAGA or Gateway))
- **/vehicle/{id}/occupancy/{id}** PUT: Change manually added vehicle occupancy
- **/vehicle/{id}/occupancy/{id}** DELETE: Delete manually added vehicle occupancy

- **/pricelist** POST: Create pricelist
- **/pricelist/{id}** GET: Get single pricelist
- **/pricelist/{id}** PUT: Change single pricelist
- **/pricelist/{id}** DELETE: Delete single pricelist
- **/pricelist/owner/{id}** GET: Get pricelists of a vehicle owner

- **/review** GET: Get all (pending) reviews (for administration purposes)
- **/vehicle/{id}/review** GET: Get reviews of a vehicle
- **/vehicle/{id}/review** POST: Create review
- **/vehicle/{id}/review/{id}** GET: Get single review
- **/vehicle/{id}/review/{id}** PUT: Change single review
- **/vehicle/{id}/review/{id}** DELETE: Delete single review

## Vehicle search service
### Description

Microservice for the search feature.

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
- pricelist
- numberOfStars
- numberOfReviews

#### Vehicle occupancy:
(Synchronized with Vehicle service data with saga pattern)
- id
- start_time
- end_time
- type (Enum: RENTAL, PERSONAL)
- location (?)

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
- **/search?location=Novi+Sad&dateTime=2018391013....?brand=Tesla,BMW,Zastava&model=....** GET: Get vehicles by search parameters

## Location service
### Description

Microservice that handles vehicle location.

### Model
#### Location:
- id
- vehicle_id
- latitude
- longitude

### Endpoints:
- **/location** POST: Created on vehicle registration
- **/location/vehicle/{id}** GET: Get vehicle location
- **/location/vehicle/{id}** PUT: Change vehicle location
- **/location/vehicle/{id}** DELETE: Delete vehicle location
