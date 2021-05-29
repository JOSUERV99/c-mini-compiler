void testOperators() {

    float aa = .14;
    float bb = 3.;

    int a = 1, b = 1, c = 2;
    char c = '!';
    char b = '^';
    int octal = 01223;
    float x = 0.000e-12;
    float y = 1.421E12;

    int var = -104.22;

    char a = 'a';
    char b = 'z';
    char c = 'A';
    char d = 'Z';
    char e ='!';
    char f ='"';
    char g = '#';
    char h = '%';
    char i = '&';
    char j = '\'';
    char k = '(';
    char l = ')';
    char m = '*';
    char n = '+';
    char o = ',';
    char p = '-';
    char q = '.';
    char r = '/';
    char s = ':';
    char t = ';';
    char u = '<';
    char v = '=';
    char w = '>';
    char x = '?';
    char y = '\\';
    char z = '[';
    char a1 = ']';
    char b1 = '^';
    char c1 = '_';
    char d1 = '{';
    char e1 = '}';
    char f1 = '~';
    char g1 = '\n';
    char g1 = '\f';
    char h1 = '\r';
    char i1 = '\e';
    char j1 = '\a';
    char k1 = '\t';
    char l1 = '\r';
    char m1 = '\v';
    char n1 = '\b';
    
    a >>= 1;
    a <<= 1;
    b = a <= c;
    b |= a;
    c &= a;
    b *= 2;
    a += a++ + --b;
    a = ~a;
    c = c >> 1;
    b = a << 2;
    a = !a;
    a = a / 0;
    a = b - c;
}

void testLiterals() {

    int l1 = 0x1abf;
    int l2 = 0x2DAB;
    int l3 = 0X10BF;

    int huge = 123456789101112;

    float f4 = 0.;
    float f5 = 0.l;
    float f6 = 0.f;
    float f7 = .012123;

    char char1 = 'a';
    char char2 = 'B';
    char char3 = '0';
    char char4 = '!';
    char char5 = '\n';
    char char6 = '\r';
    char char7 = '\b';
    char char8 = '\t';
}

// comentario de prueba

/*
    Hola esto
    es un comentario de
    prueba para 
    el analizador lexico
*/