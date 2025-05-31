Feature: Delete Object via API with authentication
As an authenticated user
I want to update an object via API
So that the object is updated in the system

@withLogin
Scenario: Delete an existing object
    Given I send "GET" request to "/api/objects/" with body:
    """
    {}
    """
    And I save the first id of existing Object
    When I send "DELETE" request to "/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/" with body:
    """
    {}
    """
    Then The delete response status code should be 200
    And The delete response status should be "deleted"
    And The delete response message should be "delete-message-positive"

@withLogin
Scenario: Delete a deleted object
    When I send "DELETE" request to "/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/" with body:
    """
    {}
    """
    Then The delete response status code should be 200
    And The delete response status should be "failed"
    And The delete response message should be "delete-message-negative"