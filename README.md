# Solidgate Test Task

This project uses **Java 11** and **Gradle**. It was created to complete the Solidgate test task.

## Getting Started

### Prerequisites

- **Java 11** installed
- **Gradle** installed
- **IntelliJ IDEA** (optional, but recommended)

### Running Tests

#### From IntelliJ IDEA

1. Go to `Run > Edit Configurations > Edit configuration templates`.
2. Add a new template with the following environment variables:
    - `PUBLIC_KEY={public_key}`
    - `SECRET_KEY={secret_key}`

#### From the Terminal

To run the tests from the terminal, use the following command with your credentials:

```sh
PUBLIC_KEY={public_key} SECRET_KEY={secret_key} gradle clean allTests
```

#### Viewing Allure Reports

To generate and view the Allure report after running the tests, use the following commands:
```sh
allure generate -c
allure serve ./build/allure-results/
```