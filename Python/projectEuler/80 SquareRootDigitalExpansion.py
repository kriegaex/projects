from decimal import Decimal, localcontext

def square_root(n):
    result = []
    for i in range(2, n + 1):
        if i ** 0.5 % 1 != 0:
            lis = []

            with localcontext() as ctx:
                ctx.prec = 150
                ins = Decimal(i).sqrt()

            a = str(ins)
            for i in range(101):
                if a[i] != '.':
                    lis.append(int(a[i]))
            result.append(sum(lis))

    return sum(result)

print(square_root(100))