package analizador;
import java_cup.runtime.Symbol;

%%

%{
    //Código de usuario
%}

%class scanner  // definir como trabajara el scanner 
%cup            // trabajar con cup 
%public         // tipo publico
%line           // conteo de lineas - linea por linea
%char           // caracter por caracter
%unicode        // tipo de codigicacion para que acepte la  ñ u otro caracter
%ignorecase     // case insensitive 

// simbolos

LLA_IZQ = \{
LLA_DER = \} 
DOS_PTS = ":"
GUION = "-"
MAYOR= ">"
PUNTO_COMA = ";"
VIRGULILLA = "~"
PUNTO = "."
LIN_VERTICAL= "|"
ASTERISCO = "*"
SUMA ="+"
INTEROGACION = "?"
PORCENTAJE = "%"
COMA = ","
COMILLA = \"

// palabras reservadas
PRIV_CONJ = "CONJ"



// expresiones
ESCAPADOS = "\\n" | "\\\"" | "\\\'"
NO_ESCAPADOS = [^\'\"]
ESPACIOS = [ \t\r\n]+
CARACTER_COMENTARIO = [^\!]|("!"[^\>])
COMENT_MULTILINEA = "<!" {CARACTER_COMENTARIO} + "!>"
COMENT_SIMPLE = "//" .*
LETRA_MINUS = [a-z]
LETRA_MAYUS = [A-Z]
NUMEROS = [0-9]
IDENTIFICADOR = [a-zA-Z][a-zA-Z0-9_]+
CARACTER_ESP = [ -\/:-@\[-`{-}]
CARACTER = (\" {NO_ESCAPADOS} \")| {ESCAPADOS}
CADENA = \"([^\"]|"\\\"")+\"

%%

<YYINITIAL> {COMENT_MULTILINEA} {}
<YYINITIAL> {COMENT_SIMPLE} {}
<YYINITIAL> {ESPACIOS} { }
<YYINITIAL> {COMILLA} { }
<YYINITIAL> {PRIV_CONJ} { return new Symbol(sym.PRIV_CONJ, yyline, yycolumn,yytext());} 
<YYINITIAL> {DOS_PTS} { return new Symbol(sym.DOS_PTS, yyline, yycolumn,yytext());} 
<YYINITIAL> {GUION} { return new Symbol(sym.GUION, yyline, yycolumn,yytext());} 
<YYINITIAL> {MAYOR} { return new Symbol(sym.MAYOR, yyline, yycolumn,yytext());} 
<YYINITIAL> {COMA} { return new Symbol(sym.COMA, yyline, yycolumn,yytext());} 
<YYINITIAL> {VIRGULILLA} { return new Symbol(sym.VIRGULILLA, yyline, yycolumn,yytext());} 
<YYINITIAL> {LLA_IZQ} { return new Symbol(sym.LLA_IZQ, yyline, yycolumn,yytext());} 
<YYINITIAL> {LLA_DER} { return new Symbol(sym.LLA_DER, yyline, yycolumn,yytext());} 
<YYINITIAL> {PUNTO} { return new Symbol(sym.PUNTO, yyline, yycolumn,yytext());} 
<YYINITIAL> {LIN_VERTICAL} { return new Symbol(sym.LIN_VERTICAL, yyline, yycolumn,yytext());} 
<YYINITIAL> {ASTERISCO} { return new Symbol(sym.ASTERISCO, yyline, yycolumn,yytext());} 
<YYINITIAL> {SUMA} { return new Symbol(sym.SUMA, yyline, yycolumn,yytext());} 
<YYINITIAL> {INTEROGACION} { return new Symbol(sym.INTEROGACION, yyline, yycolumn,yytext());} 
<YYINITIAL> {PORCENTAJE} { return new Symbol(sym.PORCENTAJE, yyline, yycolumn,yytext());} 
<YYINITIAL> {PUNTO_COMA} { return new Symbol(sym.PUNTO_COMA, yyline, yycolumn,yytext());} 
<YYINITIAL> {IDENTIFICADOR} { return new Symbol(sym.IDENTIFICADOR, yyline, yycolumn,yytext());} 
<YYINITIAL> {LETRA_MAYUS} { return new Symbol(sym.LETRA_MAYUS, yyline, yycolumn,yytext());} 
<YYINITIAL> {CARACTER} { return new Symbol(sym.CARACTER, yyline, yycolumn,yytext());} 
<YYINITIAL> {CADENA} { return new Symbol(sym.CADENA, yyline, yycolumn,yytext());} 
<YYINITIAL> {LETRA_MINUS} { return new Symbol(sym.LETRA_MINUS, yyline, yycolumn,yytext());} 
<YYINITIAL> {NUMEROS} { return new Symbol(sym.NUMEROS, yyline, yycolumn,yytext());} 
<YYINITIAL> {CARACTER_ESP} { return new Symbol(sym.CARACTER_ESP, yyline, yycolumn,yytext());} 

<YYINITIAL> . {
        String errLex = "Error léxico : '"+yytext()+"' en la línea: "+(yyline+1)+" y columna: "+(yycolumn+1);
        System.out.println(errLex);
}

