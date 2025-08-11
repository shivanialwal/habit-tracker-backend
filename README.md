# Habit Tracker Backend (Spring Boot)

## Requirements
- Docker & docker-compose
- Java 17
- Maven

## Quick start (dev)
1. Build jar: `mvn clean package -DskipTests`
2. Start with docker-compose: `docker-compose up --build`
3. App will be available at `http://localhost:8080`

## Important notes
- Default DB credentials are in `docker-compose.yml` (postgres/postgres)
- JWT secret is set via environment variable `JWT_SECRET` (change in prod)
- Flyway will run the initial schema migration automatically

## Endpoints (MVP)
- `POST /api/auth/register` — register (name,email,password)
- `POST /api/auth/login` — login (email,password) -> returns JWT
- `GET /api/auth/verify?email=...` — verify account (MVP simple flow)
- `POST /api/habits` — create habit
- `GET /api/habits?userId=...` — get habits for user
- `POST /api/habits/{habitId}/log?date=YYYY-MM-DD&status=true` — log habit

## Next steps
- Implement proper email verification tokens and password reset flow (send emails)
- Secure endpoints to read userId from token rather than request param
- Add unit & integration tests
