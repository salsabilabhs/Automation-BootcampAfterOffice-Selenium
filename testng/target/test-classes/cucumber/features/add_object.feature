Feature: Add Object via API with authentication
As an authenticated user
I want to add a new object via API
So that the object is stored in the system

Scenario: Add a new Object with valid data
When I send "POST" request to "/api/objects" with body:
"""
{
        "name": "Tab Galaxy S10",
        "data": {
            "year": 2025,
            "price": 3000,
            "cpu_model": "iOS 17",
            "hard_disk_size": "1 TB",
            "capacity": "255",
            "screen_size": "7",
            "color": "Black"
        }
    }
"""
Then The add response status code should be 200
And The add response id should not be null
And I save the object ID
And The add response name should be "Tab Galaxy S10"
And The add response year should be "2025"
And The add response price should be 3000
And The add response cpu model should be "iOS 17"
And The add response hard disk size should be "1 TB"
And The add response capacity should be "255"
And The add response screen size should be "7"
And The add response color should be "Black"




