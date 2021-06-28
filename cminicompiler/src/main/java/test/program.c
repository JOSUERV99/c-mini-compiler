
int x;
int y;
int a, b, c;

int main()
{
    x = 0;
    y = 1;

    while (x < 5)
    {
        x++;
        y = y * x;

        write(x);
        write(y);
    }

    write(x);
    write(y);
}