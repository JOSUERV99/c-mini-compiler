//Esta es una prueba para probar las expresiones que son aceptadas por el parser


int funcion1(int x){

/////Todas las siguientes expresiones son aceptadas,

  x++;
  x();
  a = a * c;
  x=2;
  funcion1(x=3);      
  funcion1(3+x, z);  
  a =  2 + 3 / 345 + 8 * a + (5-3); //se aceptan combinaciones de expresiones
  a = a * c + 55 - funcion(8);

  a = ((a + b) * 5 ;  //parentesis desbalanceados

}

