# Real-Estate-Management-NYU-OOP-Summer-2024

| UC 1: Create property                                                                                                     |                                                                                                             |
|--------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| **Description**         | The Property Manager adds new property listings with details such as address, type (residential/commercial), size, rental price, and description. This feature ensures all property information is accurately recorded and easily retrievable. |
| **Connected Use Cases** | None                                                                                           |                                                                                                             |
| **Actors**              | Manager                                                                                        |                                                                                                             |
| **Preconditions**       | The Property Manager is authenticated and has the necessary permissions to create a property.  |                                                                                                             |
| **Postconditions**      | A new property is created and stored in the system's database.                                  |                                                                                                             |

| UC 2: Read property                                                                                                       |                                                                                                             |
|--------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| **Description**         | The Property Manager views a list of properties, providing a comprehensive overview of all available properties, allowing users to browse through listings. |
| **Connected Use Cases** | Update property, Delete property                                                               |                                                                                                             |
| **Actors**              | Manager                                                                                        |                                                                                                             |
| **Preconditions**       | The Property Manager is authenticated and has access rights to view property information.      |                                                                                                             |
| **Postconditions**      | The requested property details are displayed to the Property Manager.                           |                                                                                                             |

| UC 3: Update property                                                                                                     |                                                                                                             |
|--------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| **Description**         | The Property Manager edits property information. This feature allows for modifications to property details, ensuring information remains current and accurate. |
| **Connected Use Cases** | Read property                                                                                  |                                                                                                             |
| **Actors**              | Manager                                                                                        |                                                                                                             |
| **Preconditions**       | The Property Manager is authenticated and has the necessary permissions to update property information.  |                                                                                                             |
| **Postconditions**      | The property details are updated in the system's database.                                     |                                                                                                             |

| UC 4: Delete property                                                                                                     |                                                                                                             |
|--------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| **Description**         | The Property Manager removes property listings from the system. This helps in maintaining an up-to-date database by removing outdated or unavailable listings. |
| **Connected Use Cases** | Read property                                                                                  |                                                                                                             |
| **Actors**              | Manager                                                                                        |                                                                                                             |
| **Preconditions**       | The Property Manager is authenticated and has the necessary permissions to delete a property.  |                                                                                                             |
| **Postconditions**      | The property is removed from the system's database.                                            |                                                                                                             |
