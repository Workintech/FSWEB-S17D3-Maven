# Zoo REST API

Spring Boot ile geliştirilmiş, hayvanat bahçesi için basit bir REST API.

## Teknolojiler

- Java 17 · Spring Boot 3.2 · Maven
- Lombok · Spring Validation

## Çalıştırma

```bash
./mvnw spring-boot:run
```

Uygulama `http://localhost:9000/workintech` adresinde çalışır.

## Proje Yapısı

```
com.workintech.zoo
├── entity/       Koala, Kangaroo
├── controller/   CRUD endpoint'leri (bellekte Map)
└── exceptions/   ZooException, ZooErrorResponse, ZooGlobalExceptionHandler
```

## API Endpoint'leri

| Metot | Endpoint | Açıklama |
|-------|----------|----------|
| GET | `/kangaroos` | Tüm kangurular |
| GET | `/kangaroos/{id}` | ID ile kanguru |
| POST | `/kangaroos` | Yeni kanguru |
| PUT | `/kangaroos/{id}` | Kanguru güncelle |
| DELETE | `/kangaroos/{id}` | Kanguru sil |
| GET | `/koalas` | Tüm koalalar |
| GET | `/koalas/{id}` | ID ile koala |
| POST | `/koalas` | Yeni koala |
| PUT | `/koalas/{id}` | Koala güncelle |
| DELETE | `/koalas/{id}` | Koala sil |

## Modeller

**Koala:** `id`, `name`, `weight`, `sleepHour`, `gender`

**Kangaroo:** `id`, `name`, `height`, `weight`, `gender`, `isAggressive`

## Hata Yönetimi

`ZooGlobalExceptionHandler` tüm hataları merkezi olarak yakalar ve `ZooErrorResponse` (`message`, `status`, `timestamp`) döner. Geçersiz ID veya bulunamayan kayıt için `ZooException` fırlatılır.

## Test

```bash
./mvnw test
```
