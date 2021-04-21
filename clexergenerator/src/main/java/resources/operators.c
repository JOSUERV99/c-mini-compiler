
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

    float l3 = .012123;
    float l4 = 0.;
    float l5 = 0.l;
    float l6 = 0.f;
    long long abc = 0.00L;

    char char1 = 'a';
    char char2 = 'B';
    char char3 = '0';
    char char4 = '!';
    char char4 = '\n';
    char char4 = '\r';
    char char4 = '\b';
    char char4 = '\t';
    
    
}