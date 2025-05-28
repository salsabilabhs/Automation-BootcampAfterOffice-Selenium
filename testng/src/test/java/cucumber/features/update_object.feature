Feature: Update Object via API with authentication
As an authenticated user
I want to update an object via API
So that the object is updated in the system

Scenario: Update an existing Object with valid data
    When I send "PUT" request to "/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/" with body:
    """
    {
        "name": "Samsung Flip 17",
        "data": {
            "year": 2029,
            "price": 20987.72,
            "cpu_model": "Exynos",
            "hard_disk_size": "2 GB",
            "capacity": "120",
            "screen_size": "7.6",
            "color": "Purple"
        }
    }
    """
    Then The update response status code should be 200
    And The update response id should not be null
    And The update response name should be "Samsung Flip 17"
    And The update response year should be "2029"
    And The update response price should be 20987.72
    And The update response cpu model should be "Exynos"
    And The update response hard disk size should be "2 GB"
    And The update response capacity should be "120"
    And The update response screen size should be "7.6"
    And The update response color should be "Purple"