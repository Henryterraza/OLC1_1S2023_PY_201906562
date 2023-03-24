- #### **Gramática Jflex**
    - **Alfabeto**
     
        **Simbolos terminales**
        ==

        Expresiones regulares
        =
        | Token         |         Patrón         |
        | ------------- | :--------------------: |
        | LLA_IZQ       |           \{           |
        | LLA_DER       |           \}           |
        | DOS_PTS       |           :            |
        | GUION         |           -            |
        | MAYOR         |           >            |
        | PUNTO_COMA    |           ;            |
        | VIRGULILLA    |           ~            |
        | PUNTO         |           .            |
        | LIN_VERTICAL  |           \|           |
        | ASTERISCO     |           *            |
        | INTEROGACION  |           ?            |
        | PORCENTAJE    |           %            |
        | COMA          |           ,            |
        | COMILLA       |           \"           |
        | coma          |           ,            |
        | entero        |           d+           |
        | decimal       |         d+.d+          |
        | cadena        |         ".*?"          |
        | char          |         '.*?'          |
        | identificador | [a-zA-Z_][a-zA-Z_0-9]* |

        Palabras Reservadas
        ==
        | Token                                 |             Patrón              |
        | ------------------------------------- | :-----------------------------: |
        | ESCAPADOS                             |    \\n     \| "\\\""\| \\\'     |
        | NO_ESCAPADOS                          |             [^\'\"]             |
        | ESPACIOS                              |           [ \t\r\n]+            |
        | CARACTER_COMENTARIO                   |     [^\!]        ("!"[^\>])     |
        | COMENT_MULTILINEA                     | <!" + {CARACTER_COMENTARIO} +!> |
        | COMENT_SIMPLE                         |              // .*              |
        | LETRA_MINUS                           |              [a-z]              |
        | LETRA_MAYUS                           |              [A-Z]              |
        | NUMEROS                               |              [0-9]              |
        | IDENTIFICADOR = [a-zA-Z][a-zA-Z0-9_]+ |      [a-zA-Z][a-zA-Z0-9_]+      |
        | CARACTER_ESP = [ -\/:-@\[-`{-}]       |        [ -\/:-@\[-`{-}]         |
        | \ADENA =                              |      \"([^\"]\|"\\\"")+\"       |
        
        Simbolos no terminales
        ==
   

        | Token            | Descripcion                    |
        | ---------------- | ------------------------------ |
        | ini              | Estado inicial de la sintáxis  |
        | parte_definicion | Estado parte de definicion     |
        | parte_pruebas;   | Estado de pruebas              |
        | instruccion;     | Cualquier instruccion          |
        | notacion;        | Defincion de notaciones        |
        | exp_regular;     | Estado de expresion de regular |
        | conjunto;        | Estado de conjunto             |
        | elemento;        | Estado de elementos            |
        | evaluar;         | Estado de evaluar              |
        | noreconocidos;   | Estado de noreconocidos        |
    - **Sintaxis**
```
        terminal String PRIV_CONJ, DOS_PTS, GUION, MAYOR, COMA, VIRGULILLA,LLA_IZQ, LLA_DER, PUNTO, LIN_VERTICAL, ASTERISCO, SUMA, INTEROGACION, PORCENTAJE, PUNTO_COMA, IDENTIFICADOR, LETRA_MAYUS, CARACTER, CADENA, LETRA_MINUS, NUMEROS, CARACTER_ESP; 

        non terminal ini;                                 
        non terminal parte_definicion;
        non terminal parte_pruebas;
        non terminal instruccion; 
        non terminal notacion; 
        non terminal exp_regular; 
        non terminal conjunto; 
        non terminal elemento;
        non terminal evaluar;                    
        non terminal noreconocidos;                    

        start with ini;

        ini::= LLA_IZQ parte_definicion PORCENTAJE PORCENTAJE PORCENTAJE PORCENTAJE parte_pruebas LLA_DER;                               

        parte_definicion ::= instruccion parte_definicion
                            | instruccion;

        instruccion ::= PRIV_CONJ DOS_PTS IDENTIFICADOR GUION MAYOR notacion PUNTO_COMA
                        | IDENTIFICADOR GUION MAYOR exp_regular PUNTO_COMA 
                        | error PUNTO_COMA;


        notacion ::= LETRA_MAYUS VIRGULILLA LETRA_MAYUS
                    | LETRA_MINUS VIRGULILLA LETRA_MINUS
                    | NUMEROS VIRGULILLA NUMEROS
                    | CARACTER_ESP VIRGULILLA CARACTER_ESP
                    | noreconocidos VIRGULILLA noreconocidos
                    | conjunto;

        conjunto ::= elemento COMA conjunto
                    |elemento;


        elemento ::= LETRA_MAYUS 
            | LETRA_MINUS
            | NUMEROS
            | noreconocidos
            | CARACTER_ESP;

        noreconocidos::= DOS_PTS
                    | GUION
                    | MAYOR
                    | LLA_IZQ
                    | LLA_DER
                    | PUNTO
                    | LIN_VERTICAL
                    | ASTERISCO
                    | SUMA
                    | INTEROGACION
                    | PORCENTAJE
                    | PUNTO_COMA;
                    



        exp_regular ::= PUNTO exp_regular exp_regular
                        | LIN_VERTICAL exp_regular
                        | ASTERISCO exp_regular 
                        | SUMA exp_regular
                        | INTEROGACION exp_regular
                        | CARACTER
                        | LLA_IZQ IDENTIFICADOR LLA_DER;

        parte_pruebas ::= evaluar  parte_pruebas
                        | evaluar; 

            
        evaluar ::= IDENTIFICADOR DOS_PTS CADENA PUNTO_COMA
                    | error PUNTO_COMA;
```

        
      