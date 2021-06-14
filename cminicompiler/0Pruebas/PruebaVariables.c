//Esta es una prueba para las declaraciones de variables globales y locales

int g= 8;

void x; //deberia de dar error pues no se pueden declarar variables void

int z, t, y, y;
y;  // error     
int z, t, y, y;


void funcion2(int a, int b) {
  a= a*e;
}  //deberia de aceptarlo bien

int a; //declaración fuera de lugar

int funcion7(){  

  int k;  
  int g  //error por no tener ;
  int a;

  a++;
}
