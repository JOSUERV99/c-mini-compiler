int main() {
    int a = 12;
}

void testOperators() {
    int a = 0, b = 1, c = 2;

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

    long long abc = 0.00L;

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
