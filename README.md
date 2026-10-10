# Cinema Ticketing System

A client–server desktop application for selling cinema tickets, built as a course project for **Software Design** at the Faculty of Organizational Sciences (FON), University of Belgrade.

A cashier logs in, manages customers and screenings, and creates / searches / edits / cancels receipts, all over a three-tier client–server architecture communicating through sockets.

## Tech stack
- **Java** (Swing for the GUI)
- **Socket** communication (client ↔ server)
- **MySQL / MariaDB** + **JDBC** (MySQL Connector/J)
- **NetBeans** IDE

## Architecture
The system is split into three NetBeans projects:

| Project | Responsibility |
|---|---|
| **BioskopZajednicki** (Common) | Domain classes + transfer objects, shared by client and server |
| **BioskopServer** (Server) | Database broker, system operations (SO), controller, threads, server form |
| **BioskopKlijent** (Client) | Swing forms, table models, client controller |

Key patterns: `AbstractDomainObject` (each domain class provides its own SQL fragments, so the DB broker stays generic), `AbstractSO` (template method: validate → execute → commit/rollback), `DBBroker` (Singleton), and `Request`/`Response` objects serialized over sockets.

## Features
- Cashier authentication (username + password)
- **Customer** — create / search / update / delete
- **Screening** — create / search / update / delete
- **Receipt** — create (with line items) / search / update / cancel (storno)
- Database-driven search by criteria (the `WHERE` clause is built server-side)
- Age-group reference data (lookup list)

## Database
The schema is in [`bioskop_db.sql`](bioskop_db.sql). Import it into MySQL/MariaDB (e.g. via SQLyog, phpMyAdmin or the `mysql` CLI).

Tables: `kasir` (cashier), `musterija` (customer), `projekcija` (screening), `racun` (receipt), `stavkaracuna` (receipt item), `starosnodoba` (age group), `zadruga` (cooperative), `zaposlenje` (employment).

## Getting started
1. Start MySQL/MariaDB (e.g. XAMPP) and import `bioskop_db.sql`.
2. Insert at least one row into the `kasir` table (required for login).
3. Open all three projects in NetBeans.
4. In **BioskopServer** and **BioskopKlijent**: `Properties → Libraries → Add Project →` **BioskopZajednicki**.
5. In **BioskopServer**, also add the **MySQL Connector/J** `.jar` as a library.
6. Configure `BioskopServer/dbconfig.properties` (url / username / password).
7. Run the **server** (`forms.ServerForm` → Start server, port 9000).
8. Run the **client** (`forme.LoginForma`) and log in.

> **Note:** The MySQL Connector/J `.jar` is not included in the repository — download it from [dev.mysql.com](https://dev.mysql.com/downloads/connector/j/) and add it as a library.

## Documentation
Full project documentation (verbal description, use cases, system operations, sequence diagrams, contracts, conceptual and relational models, and design) is in `NaslovnaSadrzaj-DjordjePopovic-2021-0266.doc` (in Serbian).

## Author
**Đorđe Popović** — Software Design, FON
