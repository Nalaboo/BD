El nostre objectiu és dissenyar i implementar una aplicació d'escriptori que permeti a un usuari jugar - i realitzar tasques relacionades amb - al joc de lògica anomenat Hidato. Considerarem vàlids només aquells Hidatos que tinguin solució única. L'Hidato el definim com un tauler quadrat [O NO? PREGUNTARLE AL BORJA] de n caselles de costat, amb m caselles prohibides (que representen els "forats"), amb x nombre de xifres col·locades a l'inici de la partida, així com la seva dificultat.

El programa haurà de tenir diferents usuaris. Distingirem entre usuaris normals, l'usuari "convidat", i l'usuari "administrador" (root). L'usuari convidat nomès pot fer "partida ràpida", mentre que l'usuari normal tindrà accés a crear perfil, carregar perfil, i opcionalment modificar perfil. També podrà crear Hidatos, crear (començar) partides, i a més tindrà accés als _rankings_ i les estadístiques. A més, l'usuari root podrà realitzar el que fa l'usuari normal i esborrar perfils, modificar perfils (el que no siguin d'ell), esborrar Hidatos, modificar Hidatos i reiniciar _rankings_ i estadístiques. 

El sistema tindrà una base de dades de Hidatos, que tothom podrà jugar. Cada usuari podrà crear nous taulers, que el sistema enmagaztemarà. S'implementarà de manera opcional, una funcionalitat que canviï aquest sistema: els taulers generats pels usuaris passaran a ser enmagatzemats de manera privada. Hi haurà una opció per a demanar la seva aprovació; un cop donada l'aprovació de l'administrador, s'afegiran aquests taulers a la llista pública.

El tauler del Hidato tindrà unes mides mínimes i màximes de 3 i 10 caselles de costat, respectivament. Tindrà un limit màxim de forats, que anirà en funció del número de caselles del tauler. . . 
[Com que en aquest paràgraf estem parlant de les propietats del Hidato, s'ha de mencionar la dificultat. Quedem en que hi haurà coses "objectives" com el número de Naked Singles, la diferència mitjana entre números donats consecutius, etc. Queda dit també que com que la "dificultat" és algo subjectiu que assignarem nosaltres manualment, tampoc ens hem de rallar molt en la seva definició precisa.]
[Les dimensions mínimes i màximes del tauler són modificables segons el que ens digui Borja de com es pot construir el tauler. Queda com sigui que la mida màxima en caselles ha de ser 100, i la mínima 9.]
Un cop finalitzada la creació del tauler, l'usuari haurà de decidir si el vol jugar o tornar al menú principal. [Mirar si no hi ha un lloc millor per a ficar això.]

Es defineix una partida com un Hidato, un jugador, i el progrés que es porta a la partida. Un usuari (jugador) ha de poder començar (crear), guardar, i carregar partides anteriors. [TODO: Acabar esto]

S'implementarà un sistema de puntuació basat en el temps emprat per a resoldre el puzzle. Opcionalment s'implementaran penalitzacions de temps per a determinades accions, com per exemple sobreescriure una cel·la on ja s'havia introduït un número.

El sistema ha d'enregistrar les estadístiques, tant com per els Hidatos guardats a la base de dades, com per a cada usuari. Els ítems que 
En base a aquestes estadístiques s'haurà de generar, per a cada Hidato, un _ranking_ dels usuaris que l'han resolt [més vegades|més ràpid|


S'accedirà a totes les funcionalitats de l'aplicació a través d'una interfície gràfica (robada de les oficines de Xerox a Palo Alto, CA). Aquesta interficie






[FAREM SERVIR EL TEMPS COM A SISTEMA DE PUNTUACIÓ. EL PODREM MODIFICAR SEGONS LES ACCIONS DE L'USUARI, E.G. PENALITZACIONS DE TEMPS AL CORREGIR CASELLES.]

L'aplicació tindrà diferents opcions on hi haurà gestió de perfils, gestiò de joc, estadístiques, ajuda, i opció a partida ràpida. L'usuari podrà crear un perfil, carregar un perfil; a part d'això, també pot tenir l'opció de modificar perfil.

