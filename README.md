# Currency exchanger API

### About
Currency exchange service written in Java using the Spring boot framework

### Usage

- **To get the current exchange rate:**
  ```
  GET  /api/exchange-rate?base=KZT&target=USD
  ```
  - Use ``base``, ``target`` parameters
  ```
  {
    "baseCurrency": "USD",
    "targetCurrency": "KZT",
    "exchangeRate": 451.474489
  }
  ```
  <hr>
- **To convert one currency to another:**
  ```
  GET  /api/currency-convert?base=USD&target=RUB&amount=10.9
  ```
  - Use ``base``, ``target``, ``amount`` parameters
  ```
  {
    "baseCurrency": "USD",
    "baseAmount": 10.9,
    "targetCurrency": "RUB",
    "targetAmount": 999.5473528000001
  }
  ```

### Run
- Clone repository:
    ```
    git clone git@github.com:mystpen/currency-exchanger.git
    ```
- Go to ```currency-exchanger``` directory
- Get api-key from [ExchangeRateAPI](https://app.exchangerate-api.com) and put it on ``currency-exchanger/src/resources/aplication.properties``:
    ```
    exchangerate.apikey=your-api-key
    ```
- <b>Run project</b>:
    ```
    ./mvnw spring-boot:run
    ```