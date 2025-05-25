Feature: Managing Objects via API with authentication
As an authenticated user
I want to add a new object via API
So that the object is stored in the system

Background:
    Given I have set the base url "https://whitesmokehouse.com/webhook"
    And Send a http "POST" request to login "/api/login" with body:
    """
    {
        "email": "sulistyo_2@gmail.com",
        "password": "t@st12345"
    }
    """
    And I save a valid authentication token

# Scenario Add New Object
Scenario: Add a new object
    When I send a http "POST" request to "/api/objects" with body:
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
    Then The response status code should be 200
    And The response id should not be null
    And I save an object id
    And The response name should be "Tab Galaxy S10"
    And The response year should be "2025"
    And The response price should be 3000
    And The response cpu model should be "iOS 17"
    And The response hard disk size should be "1 TB"
    And The response capacity should be "255"
    And The response screen size should be "7"
    And The response color should be "Black"

# Scenario: Update an existing object
Scenario: Update an existing object
    When I send a http "PUT" request to "update-object-url" with body:
    """
    {
        "name": "[PROMO] Tab Galaxy S10",
        "data": {
            "year": 2026,
            "price": 2000,
            "cpu_model": "Exynos 2100",
            "hard_disk_size": "2 TB",
            "capacity": "512",
            "screen_size": "7.5",
            "color": "Silver"
        }
    }
    """
    Then The update response status code should be 200
    And The update response id should not be null
    And The update response name should be "[PROMO] Tab Galaxy S10"
    And The update response year should be "2026"
    And The update response price should be 2000
    And The update response cpu model should be "Exynos 2100"
    And The update response hard disk size should be "2 TB"
    And The update response capacity should be "512"
    And The update response screen size should be "7.5"
    And The update response color should be "Silver"

# Scenario: Delete an existing object
Scenario: Delete an existing object
When I send a http "DELETE" request to "delete-object-url" with body:
"""
{}
"""
Then The delete response status code should be 200
And The delete response status should be "deleted"
And The delete response message should be "delete-message-positive"


# Scenario: Delete a deleted object
Scenario: Delete a deleted object
When I send a http "DELETE" request to "delete-object-url" with body:
"""
{}
"""
Then The delete response status code should be 200
And The delete response status should be "failed"
And The delete response message should be "delete-message-negative"

    