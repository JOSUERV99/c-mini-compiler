int x,y;
 
int func(int a, int b){
	x = a + b;
	return; 
	
}
 
void main(){
 
 x = func(3, 4);
 x = func(3, 4, 7);  // error de paranmetros
 x = proc(3, 4);  // error funcion no definida

 break;
 continue; 
}