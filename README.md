# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Alexander Paulsen, S364573, s364573@oslomet.no
* Dennis Johnsen, S364521 s364521@oslomet.no
# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Alexander har hatt hovedansvar for oppgave 1, 3, 4 og 8. 
* Dennis har hatt hovedansvar for oppgave 2,5,6 og 7.
* Vi har levert litt fra begge pcer siden vi har sittet sammen og jobbet for det meste. 
* Commits er derfor noen ganger fra en av oss som ikke hadde hovedansvar for oppgaven.

# Oppgavebeskrivelse

I oppgave 1 så gikk vi frem ved å lage to metoder:
int antall og boolean tom. Å finne antallet var bare å returnere antallet tilbake. 
Og å finne ut om listen var tom sjekker man bare om hodet er null. Hvis det er null har den ingen verdier videre.

I oppgave 2 så brukte vi en StringBuiler til å  lage 2 metoder:
toString og omvendtString. Disse ble lagd ved at vi lagde en tempnode som itererte gjennom lista og 
deretter brukte vi append til å legge verdiene til nodene inn på stringen.
Vi skulle også lage en leggInn-metode. Her brukte vi metoden tom(). Hvis den var tom kunne vi legge til den nye noden som hode.
Hvis ikke satt vi noden som neste node i lista.

I oppgave 3 skulle vi lage en hent-metode. Ved hjelp av en hjelpemetode finnNode.
finnNode ble brukt slik at hvis indeksen som skulle finnes var mindre enn antall/2 skulle metoden iterere igjennom den siden og motsatt. 
hent skulle bruke bruke finnNode til å finne posisjonen til noden. Vi brukte også indekskontroll til å sjekke at at indeksen ikke var OutOfBounds.
Deretter ville hent-metoden returnere nodeverdien. 

Vi skulle også lage en oppdater-metode. Denne metoden skulle ta inn en verdi og en indeks og oppdatere verdien til noden og deretter returnere den gamle verdien. 
Det ble gjort ved å bruke finnNode-metoden.
Vi skulle også lage en sublist-metode som tok en del av lista og returnerte den. Dette gjorde vi ved å bruke fratilKontroll-metoden for å sjekke om alle verdiene
ble satt inn var innenfor rekkevidde. Deretter lagde vi en ny liste og brukte leggInn-metoden for å legge til alle nodene.

I oppgave 4 skulle vi lage en indeksTil-metode. Denne skulle vise indeksen til en verdi som ble skrevet inn.
Der gikk vi gjennom en for-loop og sjekket om verdien var lik en av nodenes verdier.


I oppgave 5 skulle vi lage en legginn-metode der vi skulle legge til en verdi med indeks i en liste. 
Dette gjorde vi ved å bruke if og else if statements. Hvis lista er tom legges det bare til en ny verdi i hodet. Hvis indeks = 0 og lista inneholder andre
verdier må noden som legges inn bli satt som hode. Om det skulle legges inn på halen må det motsatte gjøres. Hvis noden skulle legges inn i midten av lista må
man loope gjennom til man finner indeksen og deretter legge den til bak indeksen. 

I oppgave 6 skulle vi lage 2 fjern-metoder. De ble skrevet veldig likt som leggInn i oppgave 5 der vi måtte dele det opp i førsteindeks, sisteindeks og i midten av lista.
Hvis det var første måtte hodenoden gjøres til null og den neste noden gjøres om til hodet. Det motsatte for halen. I midten iterer man gjennom en loop og der indeksen
er fjernes noden. Nodene ved siden peker så mot hverandre.

I oppgave 7 b skulle vi skrive 2 forskjellige metoder for samme metode. Der vi skulle bruke fjern-metoden for dene ene. Den første metoden brukte vi en while-loop og for
hver node gjorde vi alle verdiene til null. I andre metode gikk vi gjennom en for-loop og fjernet alle nodene med fjern. Vi skulle gjøre tidsmåling på disse, men vi
fikk en LinkageError som gjorde at vi ikke får sjekket det i main. Vi kom fram til at metoden med fjern er treigest siden den må gå gjennom hele fjern-metoden.
I fjern-metoden linkes de andre nodene sammen etter at en blir fjernet som er unødvendig når man nullstiller hele lista.

I oppgave 8