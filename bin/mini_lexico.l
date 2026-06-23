package alex;

import constructorAST.errors.GestionErroresTiny;

%%
%cup
%line
%column
%class AnalizadorLexicoTiny
%unicode
%public

%{
  private ALexOperations ops;
  private GestionErroresTiny errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
  public void fijaGestionErrores(GestionErroresTiny errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([A-E]|[G-Z]|[a-e]|[g-z])
barrabaja = \_
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
numeroEntero = {digitoPositivo}{digito}*|0
numeroReal = {numeroEntero}\.{numeroEntero}
separador = [ \t\r\b\n]
comentario = \#[^\#]*\# 
wdobles = ww(w)*
identificador = {letra}({letra}|{digito}|{barrabaja}{letra}|{barrabaja}{digito})*
operadorSuma = \+
operadorResta = \-
operadorMul = \*
operadorDiv = \/
operadorIgual = \=
operadorMayor = \>
operadorMenor = \<
operadorMayorIgual = \>\=
operadorMenorIgual = \<\=
operadorIgualIgual = \=\=
operadorTambienBool = \&\&
operadorOBool = \|\|
parentesisApertura = \(
parentesisCierre = \)
corcheteApertura = \[
corcheteCierre = \]
interApertura = \¿
interCierre = \?
true = verdadero
false = falso
if = si	
else = sino
while = mientras
for = para
int = ent
double = real
bool = bool
puntocoma = \;
dospuntos = \:

%%
{separador}               {}
{comentario}              {}
{wdobles}				  {}
{if}                      {return ops.unidadIf();}
{else}                    {return ops.unidadElse();}
{while}					  {return ops.unidadWhile();}
{for}					  {return ops.unidadFor();}
{int}                     {return ops.unidadTipoEnt();}
{double}				  {return ops.unidadTipoReal();}
{bool}                    {return ops.unidadTipoBool();}
{false}                   {return ops.unidadFalse();}
{true}                    {return ops.unidadTrue();}
{identificador}           {return ops.unidadId();}
{numeroReal}			  {return ops.unidadReal();}
{numeroEntero}            {return ops.unidadEnt();}
{operadorSuma}            {return ops.unidadSuma();}
{operadorResta}            {return ops.unidadResta();}
{operadorDiv}            {return ops.unidadDiv();}
{operadorMul}             {return ops.unidadMul();}
{operadorIgual}           {return ops.unidadIgual();}
{operadorIgualIgual}      {return ops.unidadIgualIgual();}
{operadorTambienBool}	  {return ops.unidadTambienBool();}
{operadorOBool}			  {return ops.unidadOBool();}
{operadorMayor}           {return ops.unidadMayor();}
{operadorMenor}           {return ops.unidadMenor();}
{operadorMayorIgual}      {return ops.unidadMayorIgual();}
{operadorMenorIgual}      {return ops.unidadMenorIgual();}
{parentesisApertura}      {return ops.unidadPAp();}
{parentesisCierre}        {return ops.unidadPCierre();} 
{corcheteApertura}        {return ops.unidadCAp();}
{corcheteCierre}          {return ops.unidadCCierre();} 
{interApertura}          {return ops.unidadinterApertura();} 
{interCierre}          {return ops.unidadinterCierre();} 
{dospuntos}				  {return ops.unidadDosPuntos();}
{barrabaja}				  {return ops.unidadBarrabaja();}
{puntocoma}               {return ops.unidadPuntoComa();}

[^]                       {errores.errorLexico(fila(),columna(),lexema());}  