Ova REST API serverska aplikacija služi za upravljanje rezervacijama termina i resursa u poslovnom centru.

U odnosu na početne specifikacije, napravljene su izmene koje će odgovarati ovom specifičnom projektu.

**Termini (slots)**

Termini se dinamički generišu na osnovu izabranog resursa, datuma i radnog vremena resursa. Prilikom pregleda termina, sistem proverava da li termini već postoje u bazi; ako ne postoje, automatski ih generiše i čuva.

Termin je modelovan kao entitet Slot. Slotovi se čuvaju u tabeli slots i ponovo koriste pri sledećim zahtevima.

**Proces rezervacije**

Korisnik najpre pregleda dostupne termine za resurs.

Nakon izbora termina, rezervacija se kreira korišćenjem slotId.

Dostupnost termina proverava se pre svake rezervacije. Termin je zauzet ukoliko već postoji aktivna (ACTIVE) rezervacija za dati slotId. Sistem ne dozvoljava preklapanje rezervacija.

**Rezervaciona serija 
**
Implementirana je pomoću entiteta ReservationSeries, dok pojedinačne rezervacije sadrže opcioni series_id. Jedna serija može imati više rezervacija, dok rezervacija može, ali ne mora, pripadati seriji.
Prilikom kreiranja serije, sistem automatski generiše rezervacije za odgovarajuće datume (daily, weekly, monthly), uz proveru dostupnosti termina i korišćenje transakcija.

