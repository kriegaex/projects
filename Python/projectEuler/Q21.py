def amicable(number):
    total = 0 - number
    for i in range(1, round(number**0.5) + 1):
        if number % i == 0:
            total += i + number / i
    return (total)

total = 0
for i in range(2, 100000):
    if amicable(amicable(i)) == i and amicable(i) != i:
        print(i)
        total += i
print(total)
