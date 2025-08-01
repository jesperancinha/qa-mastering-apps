# Supermarket Application Test Coverage

This document provides an overview of the test coverage for the Supermarket application using Mockk.

## Test Structure

The test suite is organized to match the application's structure, with test classes for each component:

### Repository Layer
- `DeliveryRepositoryTest`: Tests the repository interface using Mockk to simulate database operations.
  - Tests finding deliveries between dates
  - Tests saving deliveries
  - Tests finding deliveries by ID
  - Tests edge cases like empty results

### Service Layer
- `DeliveryServiceTest`: Tests the service layer with mocked repository and RestTemplate.
  - Tests creating deliveries with different statuses
  - Tests validation logic for delivery status and finishedAt combinations
  - Tests getting invoices for deliveries
  - Tests error handling for missing deliveries or failed invoice service calls
  - Tests business summary calculations with various scenarios

### Controller Layer
- `DeliveryControllerTest`: Tests the REST API endpoints using SpringMockk.
  - Tests creating deliveries
  - Tests validation failures
  - Tests getting invoices
  - Tests getting business summaries
  - Tests error handling

### Validation
- `DeliveryStatusValidatorTest`: Tests the custom validator for delivery status.
  - Tests all combinations of status and finishedAt values
  - Tests the constraint annotation directly

### Mapper
- `DeliveryMapperTest`: Tests the mapping between DTOs and entities.
  - Tests mapping from DTO to entity
  - Tests mapping from entity to DTO
  - Tests handling of null values

### Configuration
- `DeliveryConfigurationTest`: Tests the configuration beans.
  - Tests RestTemplate bean creation

## Testing Approach

The testing approach follows these principles:

1. **Isolation**: Each component is tested in isolation using Mockk to mock dependencies.
2. **Comprehensive Coverage**: All methods and edge cases are covered.
3. **Behavior Verification**: Tests verify both the return values and the interactions between components.
4. **Readability**: Tests use descriptive names and follow the Given-When-Then pattern.

## Mockk Features Used

- `mockk<T>()`: Creates mock objects
- `every { ... } returns ...`: Stubs method calls
- `every { ... } answers { ... }`: Provides dynamic responses
- `verify { ... }`: Verifies method calls
- `verify(exactly = n) { ... }`: Verifies exact number of calls
- `any()`: Matches any argument of the appropriate type
- `@MockkBean`: Spring integration for controller tests

## Running the Tests

The tests can be run using Maven:

```bash
mvn test
```

Or individually in an IDE like IntelliJ IDEA.