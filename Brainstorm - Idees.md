[Link molon](http://www.mathrecreation.com/2011/06/hidato-or-kings-tour.html) on sembla que hi ha una explicacio de les mates i la logica darrere del hidato. Això ho podem fer servir per a implementar correctament les funcions que ens generin un hidato UNIC

Preguntar-li al Borja exactament quina és la topologia permesa del tauler. 

Idees:
  Mode multijugador: a part de tenir un mode en el que l'usuari resolgui un puzzle ell sol, es poden fer escenaris 1vX
    1v1: Classic, dos jugadors
    1vAI: Humà contra màquina
      La velocitat a la que resol el puzzle és variable segons el nivell de dificultat al que es jugui.
  
  Referent a la gestió d'usuaris: és viable implementar que els usuaris estiguin en un servidor remot?
    [json?]
  
  Intro music + toplevelb055 splash screen? get dat art, bitches. PERMITIDO CON BOTON DE SILENCIADOR
  
  Modo Arcade: super super opcional
  
  Dificultats:
    Easy, Normal, Dificil, ULTRA-VIOLENCE[desbloqueable]
    Que controlen?
      NO CUENTA : Tamany màxim del propi Hidato. [això entra en el enunciat]
      Ratio d'espais en blanc - queda veure com s'ajusta això. ¿?
      [Especific per a contrarellotge] Temps disponible per a resoldre el puzzle (cpt. obvious)
      Distància resolta d'una tirada.
  
  Estadístiques + ranking: opcional pero mola
    Incloure un avg. dels diferents modes de joc
      Temps utilitzat, vegades resolt vs. vegades abandonat, etc.

Es PERMISIBLE hacer persistencia con bases de datos SQL (SQLite o similares)

Lo de hacer un usuario "invitado" mola.


--- bloc de notas things
El enunciado debe incluir las definiciones de cosas como la importacion y reclamacion de las partidas

Criterios para hacer categorias de dificultad tienen que ir en el enunciado
	Naked singles?

Cada tablero de juego tiene que tener estadisticas como el tiempo medio de resolucion, las veces jugadas vs. las veces resueltas.

Manual de usuario = documentacion
Prompts interactivos y pistas = ayuda

Generacion de hidatos efímeros?
	Con opcion a guardarlos en la base de datos

Los creditos en la caratula o documentacion pl0x



Juegos de solucion unica
	¿?¿¿??¿?¿?¿

