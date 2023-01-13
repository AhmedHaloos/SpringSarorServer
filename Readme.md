# Saror Server
this is the documentation for saror server used as backend for the callSaror application.
## Mapping and routes
- **"/users/"**
    - **"/"** : to get all users with **_get_** method
    - **"/login"** : for logging in with **_get_** method
    - **"/"** : to add new user with **_post_** method
    - **"/{id}"** : to update user data with **_put_** method
- **"/country/"**
  - **"/{countryName}"** : to get a country using its name using **_get_** method
  - **"/"** or **/all** : to get all countries with **_get_** method
  - **"/"** or **"/all/"** : to add a country or list cf countries using _post_ method
  - **"/{countryName}"** : to modify country with **_put_** method
  - **"/"** : to delete country  with **_delete_** method
  - **/all/** : to delete all countries with **_delete_** method
