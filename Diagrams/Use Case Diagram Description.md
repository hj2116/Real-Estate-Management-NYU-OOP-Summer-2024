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

| UC 5: Create tenant                                                                                                       |                                                                                                             |
|--------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| **Description**         | The Tenant Manager registers new tenants with personal details and contact information. This feature ensures all tenant data is systematically recorded and easily accessible. |
| **Connected Use Cases** | None                                                                                           |                                                                                                             |
| **Actors**              | Manager                                                                                        |                                                                                                             |
| **Preconditions**       | The Tenant Manager is authenticated and has the necessary permissions to create a tenant.      |                                                                                                             |
| **Postconditions**      | A new tenant is created and stored in the system's database.                                    |                                                                                                             |

| UC 6: Read tenant                                                                                                         |                                                                                                             |
|--------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| **Description**         | The Tenant Manager views tenant profiles and lease agreements. This provides a detailed overview of tenant information and their associated lease agreements. |
| **Connected Use Cases** | Update tenant, Delete tenant                                                                   |                                                                                                             |
| **Actors**              | Manager                                                                                        |                                                                                                             |
| **Preconditions**       | The Tenant Manager is authenticated and has access rights to view tenant information.          |                                                                                                             |
| **Postconditions**      | The requested tenant details are displayed to the Tenant Manager.                              |                                                                                                             |

| UC 7: Update tenant                                                                                                       |                                                                                                             |
|--------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| **Description**         | The Tenant Manager edits tenant information. This feature allows for modifications to tenant details, ensuring information remains current and accurate. |
| **Connected Use Cases** | Read tenant                                                                                    |                                                                                                             |
| **Actors**              | Manager                                                                                        |                                                                                                             |
| **Preconditions**       | The Tenant Manager is authenticated and has the necessary permissions to update tenant information.  |                                                                                                             |
| **Postconditions**      | The tenant details are updated in the system's database.                                       |                                                                                                             |


| UC 8: Delete tenant                                                                                                       |                                                                                                             |
|--------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| **Description**         | The Tenant Manager removes tenants from the system. This helps in maintaining an up-to-date tenant database by removing inactive or outdated records. |
| **Connected Use Cases** | Read tenant                                                                                    |                                                                                                             |
| **Actors**              | Manager                                                                                        |                                                                                                             |
| **Preconditions**       | The Tenant Manager is authenticated and has the necessary permissions to delete a tenant.      |                                                                                                             |
| **Postconditions**      | The tenant is removed from the system's database.                                              |                                                                                                             |

| UC 9: Create lease                                                                                                        |                                                                                                             |
|--------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| **Description**         | The Lease Manager generates lease agreements for properties, including start and end dates, and rent amount. This feature ensures all lease details are accurately recorded and easily accessible. |
| **Connected Use Cases** | None                                                                                           |                                                                                                             |
| **Actors**              | Manager                                                                                        |                                                                                                             |
| **Preconditions**       | The Lease Manager is authenticated and has the necessary permissions to create a lease.        |                                                                                                             |
| **Postconditions**      | A new lease is created and stored in the system's database.                                     |                                                                                                             |

| UC 10: Read lease                                                                                                         |                                                                                                             |
|--------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| **Description**         | The Lease Manager views existing lease agreements. This provides a detailed overview of all active and past lease agreements. |
| **Connected Use Cases** | Delete lease                                                                                   |                                                                                                             |
| **Actors**              | Manager                                                                                        |                                                                                                             |
| **Preconditions**       | The Lease Manager is authenticated and has access rights to view lease information.            |                                                                                                             |
| **Postconditions**      | The requested lease details are displayed to the Lease Manager.                                |                                                                                                             |
| UC 11: Update lease                                                                                                       |                                                                                                             |
|--------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| **Description**         | The Lease Manager edits existing lease agreements. This feature allows for modifications to tenant details, ensuring information remains current and accurate. |
| **Connected Use Cases** | Delete lease                                                                                   |                                                                                                             |
| **Actors**              | Manager                                                                                        |                                                                                                             |
| **Preconditions**       | The Lease Manager is authenticated and has access rights to view lease information.            |                                                                                                             |
| **Postconditions**      | The requested lease details are displayed to the Lease Manager.                                |                                                                                                             |

| UC 12: Delete lease                                                                                                       |                                                                                                             |
|--------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| **Description**         | The Lease Manager terminates lease agreements. This helps in managing lease expirations and removals in a systematic manner. |
| **Connected Use Cases** | None                                                                                           |                                                                                                             |
| **Actors**              | Manager                                                                                        |                                                                                                             |
| **Preconditions**       | The Lease Manager is authenticated and has the necessary permissions to delete a lease.        |                                                                                                             |
| **Postconditions**      | The lease is removed from the system's database.                                               |                                                                                                             |
