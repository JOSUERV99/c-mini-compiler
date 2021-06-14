///Se quiere probar la estructura del programa

int x;                             
x++;                          //se espera error de sintaxis por expresion fuera de funcion
int y;                          


int f2(int a){                 

  int w;                   

  w = a++;                     

  int ww;                 //se espera que de error por declaracion de variable local posterior  a cuerpo de la funcion

  if (x==5){
  x++;
  }

return;             
                                                           
//se espera error por funcion sin parentesis de cierre     