# Cinema Ticketing System — Softverski sistem za praćenje rada bioskopa

Klijent-server aplikacija za prodaju bioskopskih karata, izrađena kao seminarski rad iz predmeta **Projektovanje softvera** na Fakultetu organizacionih nauka (FON), Univerzitet u Beogradu.

Sistem omogućava kasiru da se prijavi, upravlja mušterijama i projekcijama, i kreira / pretražuje / menja / stornira račune, kroz troslojnu (3-tier) klijent-server arhitekturu sa komunikacijom preko soketa.

## Tehnologije
- **Java** (Swing za grafički korisnički interfejs)
- **Socket** komunikacija (klijent ↔ server)
- **MySQL / MariaDB** (XAMPP) + **JDBC** (MySQL Connector/J)
- **NetBeans** razvojno okruženje

## Arhitektura
Sistem čine tri NetBeans projekta:

| Projekat | Uloga |
|---|---|
| **BioskopZajednicki** | Domenske klase + transfer objekti (dele ih klijent i server) |
| **BioskopServer** | Broker baze, sistemske operacije (SO), kontroler, niti, serverska forma |
| **BioskopKlijent** | Swing forme, table modeli, klijentski kontroler |

Ključni obrasci: `AbstractDomainObject` (generički SQL preko domenskih klasa), `AbstractSO` (template metoda validate → execute → commit/rollback), `DBBroker` (Singleton), `Request`/`Response` preko soketa.

## Funkcionalnosti
- Prijava kasira (autentifikacija preko korisničkog imena i šifre)
- **Mušterija** — Kreiraj / Pretraži / Promeni / Obriši
- **Projekcija** — Kreiraj / Pretraži / Promeni / Obriši
- **Račun** — Kreiraj (sa stavkama) / Pretraži / Promeni / Storniraj
- Pretraga iz baze po zadatim kriterijumima (`WHERE` se gradi na serverskoj strani)
- Šifarnik Starosno doba

## Baza podataka
Šema baze je u fajlu [`bioskop_db.sql`](bioskop_db.sql). Importuje se u MySQL/MariaDB (npr. preko SQLyog, phpMyAdmin ili `mysql` CLI).

Tabele: `kasir`, `musterija`, `projekcija`, `racun`, `stavkaracuna`, `starosnodoba`, `zadruga`, `zaposlenje`.

## Pokretanje
1. Pokreni MySQL/MariaDB (npr. XAMPP) i importuj `bioskop_db.sql`.
2. Dodaj bar jednog kasira u tabelu `kasir` (potrebno za prijavu).
3. Otvori sva tri projekta u NetBeans-u.
4. U projektima **BioskopServer** i **BioskopKlijent**: `Properties → Libraries → Add Project →` **BioskopZajednicki**.
5. U **BioskopServer** dodaj i **MySQL Connector/J** (`.jar`) kao biblioteku.
6. Podesi `BioskopServer/dbconfig.properties` (url / username / password tvoje baze).
7. Pokreni **server** (`forms.ServerForm` → Pokreni server, port 9000).
8. Pokreni **klijent** (`forme.LoginForma`) i prijavi se.

> **Napomena:** MySQL Connector/J (`.jar`) nije uključen u repozitorijum — preuzmi ga sa [dev.mysql.com](https://dev.mysql.com/downloads/connector/j/) i dodaj kao biblioteku.

## Dokumentacija
Kompletna projektna dokumentacija (verbalni opis, slučajevi korišćenja, sistemske operacije, sekvencni dijagrami, ugovori, konceptualni i relacioni model, projektovanje) nalazi se u fajlu `NaslovnaSadrzaj-DjordjePopovic-2021-0266.doc`.

## Autor
**Đorđe Popović** — 2021/0266
Projektovanje softvera, FON
