# Hue REST API - Endpoint Documentation

## Light Group Themes Endpoint

### POST /light-groups/themes

Apply a theme to a light group with specified brightness level.

#### Request Format

**Content-Type:** `application/json`

**Request Body:**
```json
{
  "theme": "string (required)",
  "brightness": "number (required, 0-100)"
}
```

**Fields:**
- `theme`: Theme name to apply (must not be blank)
- `brightness`: Brightness level as a decimal between 0.0 and 100.0 (inclusive)

#### Success Response

**Status Code:** `200 OK`

**Response Body:**
```json
{
  "message": "Theme applied successfully to light group",
  "theme": "cool",
  "brightness": 75.0,
  "status": "applied"
}
```

#### Error Responses

**Status Code:** `400 Bad Request`

**Validation Errors:**

1. **Missing theme field:**
```json
{
  "error": "Validation Failed",
  "message": "Request validation failed",
  "status": 400,
  "violations": [
    "theme: Theme name is required"
  ]
}
```

2. **Invalid brightness (below 0):**
```json
{
  "error": "Validation Failed",
  "message": "Request validation failed",
  "status": 400,
  "violations": [
    "brightness: Brightness must be at least 0"
  ]
}
```

3. **Invalid brightness (above 100):**
```json
{
  "error": "Validation Failed",
  "message": "Request validation failed",
  "status": 400,
  "violations": [
    "brightness: Brightness must not exceed 100"
  ]
}
```

#### Example Requests

**cURL - Successful Request:**
```bash
curl -X POST http://localhost:8080/light-groups/themes \
  -H "Content-Type: application/json" \
  -d '{"theme": "cool", "brightness": 75.5}'
```

**cURL - Invalid Brightness:**
```bash
curl -X POST http://localhost:8080/light-groups/themes \
  -H "Content-Type: application/json" \
  -d '{"theme": "warm", "brightness": 150}'
```

#### Supported Themes

- `cool`: Cool color temperature theme
- `warm`: Warm color temperature theme
- `neutral`: Neutral color temperature theme
- (Additional themes can be added in future versions)

#### Notes

- Brightness accepts decimal values (e.g., 75.5 is valid)
- Boundary values 0.0 and 100.0 are valid
- All validation errors return HTTP 400 with detailed violation messages
