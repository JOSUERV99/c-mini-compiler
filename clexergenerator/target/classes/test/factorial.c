int factorial(int n);

int main() {

    int f1 = factorial(10);
    int f2 = factorial(5);

    return 0;
}

int factorial(int n) {
    if ( n == 0 || n == 1 )
        return 1;
    else
        return n * factorial(n-1);
}

