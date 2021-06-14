//Esta es una prueba para verificar las estructuras de control

//switch, case, default, read, write


int x;

int funcion1(int x){

     //case 5: x++;  //se espera que de error por estar fuera de switch
     //switch () { }  //se espera error por no tener expresion

     switch () {

	case 5:     //se espera error por no tener instruccion
                               
	case 8: {break;}  // se espera que lo acepte
                
	case: {a= a+b;}       //se espera error por no tener expresion constante
                
	case x: {a= a+b;}   //error debe ser constante
                
	default 5: {} //error por el 5, default no lleva expresion_constante
                
	default: x++;  //se espera que lo acepte habiendo quitado el anterior              
 	default: x--;  //se espera que de error por definicion de multiples default sin quitar el anterior
}
}
        

// read;  //se espera error        
// read(); //se espera que lo acepte       
// read(X);  //se espera que lo acepte      
// read(123);  //se espera error, pues no se acepta expresion entre ( )    
// read(x++); //se espera error

        

// write(213+ 518- 345); //se espera que lo acepte       
// write(C);    //se espera que lo acepte       
// write(C- 123);  //se espera que lo acepte     
// write( );     //se espera error]      
// write ;       //se espera error



}// fin de la funcion

