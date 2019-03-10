package sistema.analizador;
import java_cup.runtime.Symbol; 
import sistema.bean.Token;
import main.Main;
%%

%{
    StringBuffer string = new StringBuffer();

    public void addToken(String lexema, String tipo){
        main.Main.tokens.add(new Token(lexema, tipo, yyline, yycolumn));
    }

    public void addError(String lexema, String tipo){
        main.Main.errores.add(new Token(lexema, tipo, yyline, yycolumn));
    }
%}

%class Scanner
%public
%unicode
%full
%char
%line
%column
%ignorecase
%cup


finDeLinea = \r|\n|\r\n
espacioEnBlanco = {finDeLinea} | [ \t\f]
comilla = \" | “ | ”

%state STRING

%%

<YYINITIAL>{
    "imprimir"                 { addToken(yytext(), "Palabra Reservada imprimir"); return new Symbol(sym.imprimir , yyline , yychar , yytext()); }                     
    "("                        { addToken(yytext(), "parentesisabre"); return new Symbol(sym.parentesisabre , yyline , yychar , yytext()); }
    ")"                        { addToken(yytext(), "parentesiscierra"); return new Symbol(sym.parentesiscierra , yyline , yychar , yytext()); }   
    "."                        { addToken(yytext(), "concatenacion"); return new Symbol(sym.concatenacion , yyline , yychar , yytext()); }   
    ";"                        { addToken(yytext(), "puntoYComa"); return new Symbol(sym.puntoYComa , yyline , yychar , yytext()); }   
    "$"                        { addToken(yytext(), "aceptacion"); return new Symbol(sym.aceptacion , yyline , yychar , yytext()); }   
    {comilla}                  { addToken(yytext(), "comillaabre"); string.setLength(0); yybegin(STRING);}
    {finDeLinea}               { yychar=1; }
    {espacioEnBlanco}          { }
}

<STRING> {
    {comilla}                       { addToken(string.toString(), "cadena"); addToken(yytext(), "comillacierra"); yybegin(YYINITIAL); return new Symbol(sym.cadena , yyline , yychar , string.toString()); }
    [^\n\r\"\\]+                    { string.append( yytext() ); }
    \\t                             { string.append( '\t' ); }
    \\n                             { string.append( '\n' ); }
    \\r                             { string.append( '\r' ); }
    \\\"                            { string.append( '\"' ); }
    \\                              { string.append( '\\' ); }
}
    
.                                   { addError(yytext(), "NO RECONOCIDO");}

